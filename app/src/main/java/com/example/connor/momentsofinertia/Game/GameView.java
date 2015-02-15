package com.example.connor.momentsofinertia.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.connor.momentsofinertia.Game.Entities.BackgroundStar;
import com.example.connor.momentsofinertia.Game.Entities.GameEntity;
import com.example.connor.momentsofinertia.Game.Entities.Player;
import com.example.connor.momentsofinertia.util.Vector2D;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by connor on 1/30/15.
 */
public class GameView extends View implements PlayerDeathListener {

    private Vector2D cameraPosition;
    private LinkedList<GameEntity> gameEntities;
    private LinkedList<GameEntity> newEntities;
    int xScroll = 0;
    private int scrollSpeed = 2;
    public Player player;

    private long lastUpdate = System.currentTimeMillis();
    final int BACKGROUND_COLOR = Color.BLACK;
    final boolean DRAW_FRAMERATE = false;

    public GameView(Context context) {
        super(context);
        initialize();
    }

    public GameView(Context context, AttributeSet attrs){
        super(context, attrs);
        initialize();
    }


    public GameView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        initialize();
    }

    public void initialize()
    {
        gameEntities = new LinkedList<GameEntity>();
        newEntities = new LinkedList<GameEntity>();
    }

    public void onDraw(Canvas canvas){
        double deltaTime = getDeltaTime();

        update(deltaTime);



        Paint paint = new Paint();
        paint.setColor(BACKGROUND_COLOR);
        canvas.drawRect(new Rect(this.getLeft(), this.getTop(),
                this.getRight(), this.getBottom()), paint);

        if(DRAW_FRAMERATE)
            drawFrameRate(canvas, deltaTime);

        for(GameEntity entity: gameEntities){
            entity.draw(xScroll, canvas, paint);
        }

        lastUpdate = System.currentTimeMillis();
        this.invalidate();
    }

    public void update(double deltaTime){
        xScroll -= scrollSpeed;
        for(GameEntity entity: gameEntities){
            entity.update(deltaTime);
            if(entity instanceof Obstacle){
                player.checkObstacle((Obstacle)entity);
            }
        }

        removeDeadEntities();

        createNewEntities();
    }

    public void drawFrameRate(Canvas canvas, double deltaTime){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30f);
        canvas.drawText(Integer.toString((int)(1d/deltaTime)), 300f, 300f, paint);
    }

    public void addEntity(GameEntity newEntity){

        newEntities.add(newEntity);
        newEntity.setParentView(this);
    }

    public double getDeltaTime(){
        double deltaTime = ((double)(System.currentTimeMillis() - lastUpdate))/1000d;

        if(deltaTime == 0d){
            deltaTime = 0.000001;
        }

        return deltaTime;
    }

    private void createNewEntities(){
        for(GameEntity entity: newEntities){
            gameEntities.add(entity);
        }
        Collections.sort(gameEntities);
        newEntities = new LinkedList<GameEntity>();
    }

    private void removeDeadEntities(){

        for(Iterator<GameEntity> iterator = gameEntities.iterator();iterator.hasNext();){
            GameEntity entity = iterator.next();
            if (entity.isRemoved)
                iterator.remove();
        }

    }

    public void removeEntity(GameEntity entity){
        gameEntities.remove(entity);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Vector2D location = new Vector2D(event.getX() - xScroll, event.getY());
            player.createRope(location);
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            player.rope = null;
        }
        return false;
    }

    @Override
    public void onPlayerDeath() {
        xScroll = 0;

        for(GameEntity entity: gameEntities){
            if (entity instanceof BackgroundStar){
                ((BackgroundStar)entity).rollParams();
                entity.position.x %=2000;
            }
        }
    }
}
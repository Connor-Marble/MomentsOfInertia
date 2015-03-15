package com.example.connor.momentsofinertia.Game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.connor.momentsofinertia.Game.Entities.GameEntity;
import com.example.connor.momentsofinertia.Game.Entities.GameStartListener;
import com.example.connor.momentsofinertia.Game.Entities.Player;
import com.example.connor.momentsofinertia.Game.Entities.ScoreLineTracker;
import com.example.connor.momentsofinertia.Game.Entities.Trail;
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

    double xScroll = 0;
    private double elapsedTime = 0d;

    private int scrollSpeed = 150;
    public Player player;


    public long lastUpdate = System.currentTimeMillis();
    final int BACKGROUND_COLOR = Color.BLACK;
    final boolean DRAW_FRAMERATE = false;
    private Bitmap drawBitmap;

    double deltaTime;

    private boolean gameStarted = false;
    public boolean isRunning = false;

    private TextView scoreView;

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

        drawBitmap = Bitmap.createBitmap(1920, 1080, Bitmap.Config.ARGB_8888);
    }

    public void onDraw(Canvas canvas){

        if(!isRunning)
            return;

        update(deltaTime);

        Canvas btmpCanvas = new Canvas(drawBitmap);

        Paint paint = new Paint();
        paint.setColor(BACKGROUND_COLOR);
        canvas.drawRect(new Rect(this.getLeft(), this.getTop(),
                this.getRight(), this.getBottom()), paint);

        if(DRAW_FRAMERATE)
            drawFrameRate(canvas, deltaTime);

        for(GameEntity entity: gameEntities){
            entity.draw((int)xScroll, canvas, paint);
        }

        paint.setAlpha(255);
        paint.setColor(Color.WHITE);

        if(checkCollision())
            player.death();


        deltaTime = getDeltaTime();

        lastUpdate = System.currentTimeMillis();
        this.invalidate();


    }

    public boolean checkCollision(){

       for(GameEntity entity: gameEntities){
           if(entity instanceof Collidable && player!=null){
               if(((Collidable)entity).checkRect(player.collisionRect))
                   return true;
           }
       }

       return false;
    }

    public void update(double deltaTime){
        xScroll -= scrollSpeed * deltaTime;
        for(GameEntity entity: gameEntities){
            entity.update(deltaTime);
        }

        removeDeadEntities();

        if(newEntities.size() > 0)
            createNewEntities();

        if(gameStarted)
            elapsedTime += deltaTime;
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

        if(gameStarted){
            if(newEntity instanceof GameStartListener){
                ((GameStartListener)newEntity).gameStarted();
            }
        }
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

            if(!gameStarted){
                startGame();
            }

            Vector2D location = new Vector2D(event.getX() - xScroll, event.getY());
            player.createRope(location);
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            player.rope = null;
        }
        return false;
    }

    private void startGame(){
        gameStarted = true;
        xScroll = 0;
        for(GameEntity entity: gameEntities){
            if(entity instanceof GameStartListener){
                ((GameStartListener)entity).gameStarted();
            }
        }

        player = new Player(new Vector2D(500d,100d));
        player.setScoreView(scoreView);
        addEntity(player);

        player.registerDeathListener(this);

        addEntity(new Trail(1,50, player, 5f));
        addEntity(new ScoreLineTracker(new Vector2D(0,getHeight()), player, new Vector2D(10,10)));
        addEntity(new ScoreLineTracker(new Vector2D(0,0), player, new Vector2D(10,-10)));
    }

    @Override
    public void onPlayerDeath() {
        ((Activity)this.getContext()).recreate();
        return;

    }

    public void setScoreView(TextView scoreView){
        this.scoreView = scoreView;
    }

    public double getTime(){
        return elapsedTime;
    }
}

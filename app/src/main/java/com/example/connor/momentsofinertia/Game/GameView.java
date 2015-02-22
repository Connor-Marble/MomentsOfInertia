package com.example.connor.momentsofinertia.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
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
    double xScroll = 0;

    private int scrollSpeed = 150;
    public Player player;


    private long lastUpdate = System.currentTimeMillis();
    final int BACKGROUND_COLOR = Color.BLACK;
    final boolean DRAW_FRAMERATE = true;
    private Bitmap drawBitmap;

    double deltaTime;

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


        update(deltaTime);

        Canvas btmpCanvas = new Canvas(drawBitmap);

        Paint paint = new Paint();
        paint.setColor(BACKGROUND_COLOR);
        btmpCanvas.drawRect(new Rect(this.getLeft(), this.getTop(),
                this.getRight(), this.getBottom()), paint);

        if(DRAW_FRAMERATE)
            drawFrameRate(btmpCanvas, deltaTime);

        for(GameEntity entity: gameEntities){
            entity.draw((int)xScroll, btmpCanvas, paint);
        }

        paint.setAlpha(255);
        paint.setColor(Color.WHITE);

        if(checkCollision())
            player.death();

        canvas.drawBitmap(drawBitmap, 0, 0, paint);

        deltaTime = getDeltaTime();

        lastUpdate = System.currentTimeMillis();
        this.invalidate();


    }

    public boolean checkCollision(){

        //Log.d("color", Integer.toHexString(drawBitmap.getPixel((int) (player.position.x+5 + xScroll), (int) player.position.y+5)));

        if(player.position.x + xScroll > drawBitmap.getWidth() || player.position.x + xScroll < 0)
            return true;

        if(player.position.y > drawBitmap.getHeight() || player.position.y < 0)
            return true;

        int colorAtPlayer = drawBitmap.getPixel((int) (player.position.x + xScroll), (int) player.position.y);

        return (Color.red(colorAtPlayer) > 150);
    }

    public void update(double deltaTime){
        xScroll -= scrollSpeed * deltaTime;
        for(GameEntity entity: gameEntities){
            entity.update(deltaTime);
        }

        removeDeadEntities();

        if(newEntities.size() > 0)
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

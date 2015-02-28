package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.connor.momentsofinertia.Game.Collidable;
import com.example.connor.momentsofinertia.Game.Entities.GameEntity;
import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 2/6/15.
 */
public class Obstacle extends GameEntity implements Collidable, GameStartListener {
    public Rect collisionRect;
    private double colorModifier = 0d;
    private double sizeMultiplier = 0d;
    private double inflateTime = 0.25d;
    private boolean hasGameStarted = false;

    public Obstacle(Vector2D position) {
        super(position, 0);
        setCollisionRect();
    }

    public Obstacle(Vector2D position, Rect collisionRect) {
        super(position);
        collisionRect = collisionRect;
    }

    @Override
    public boolean checkRect(Rect area) {
        if(area == null)
            return false;

        if(area.right < collisionRect.left)
            return false;

        if(area.left > collisionRect.right)
            return false;

        if(area.top > collisionRect.bottom)
            return  false;

        if(area.bottom < collisionRect.top)
            return false;

        return true;
    }

    private void setCollisionRect(){
        collisionRect = new Rect((int)(position.x-(75d*sizeMultiplier/2)), (int)(position.y-(75d*sizeMultiplier/2)),
                (int)(position.x + (75d*sizeMultiplier/2)), (int)(position.y+(75d*sizeMultiplier/2)));
    }

    @Override
    public boolean recieveRaycast(Vector2D origin, double angle, double distance) {
        return false;
    }

    @Override
    public void update(double deltaTime){
        if(sizeMultiplier<1 && hasGameStarted){
            sizeMultiplier+=deltaTime/inflateTime;
            setCollisionRect();
        }
    }

    @Override
    public void draw(int scroll, Canvas canvas, Paint paint){
        colorModifier -= 2d;
        int red = (int)(Math.sin(colorModifier/100d+position.x)*64d) + 192;
        paint.setARGB(255, 255, 255-red, 0);
        canvas.drawRect(new Rect(collisionRect.left + scroll, collisionRect.top, collisionRect.right + scroll,collisionRect.bottom), paint);

    }

    @Override
    public void gameStarted() {
        hasGameStarted = true;
    }
}

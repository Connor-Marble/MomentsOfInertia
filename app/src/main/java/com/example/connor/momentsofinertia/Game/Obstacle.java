package com.example.connor.momentsofinertia.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.connor.momentsofinertia.Game.Entities.GameEntity;
import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 2/6/15.
 */
public class Obstacle extends GameEntity implements Collidable {
    public Rect collisionRect;

    public Obstacle(Vector2D position) {
        super(position, 0);
        collisionRect = new Rect((int)position.x, (int)position.y,
                (int)position.x + 75, (int)position.y+75);


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

    @Override
    public boolean RecieveRaycast(Vector2D origin, double angle, double distance) {
        return false;
    }

    @Override
    public void draw(int scroll, Canvas canvas, Paint paint){

        paint.setColor(Color.RED);
        canvas.drawRect(new Rect(collisionRect.left + scroll, collisionRect.top, collisionRect.right + scroll,collisionRect.bottom), paint);

    }
}

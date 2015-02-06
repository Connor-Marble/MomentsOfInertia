package com.example.connor.momentsofinertia;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by connor on 1/31/15.
 */
public class PlayerTrail extends GameEntity {

    double opacity = 1d;
    double fadeTime = 1d;

    public PlayerTrail(Vector2D position) {
        super(position, 5);
    }

    @Override
    public void update(double deltaTime){
        opacity -= deltaTime/fadeTime;
        if(opacity <=0)
            remove();
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint){
        paint.setColor(Color.BLUE);
        paint.setAlpha((int)(opacity*255d));
        canvas.drawRect(new Rect((int)position.x-5 + xScroll, (int)position.y-5,
                (int)position.x+5 + xScroll, (int)position.y + 5), paint);
    }
}

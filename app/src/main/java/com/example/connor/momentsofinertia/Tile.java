package com.example.connor.momentsofinertia;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Created by connor on 1/31/15.
 */
public class Tile extends GameEntity {

    public Tile(Vector2D position) {
        super(position);
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint){
        paint.setColor(Color.WHITE);
        canvas.drawRect((float)position.x - 25f, (float)position.y -25f,
                (float)position.x + 25f, (float)position.y +25f, paint);
    }
}

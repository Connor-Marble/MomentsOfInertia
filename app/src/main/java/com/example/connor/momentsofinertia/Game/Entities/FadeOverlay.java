package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 3/1/15.
 */
public class FadeOverlay extends GameEntity{

    int width, height;
    double opacity;
    boolean fadein;
    double fadeTime;

    public FadeOverlay(int width, int height) {
        super(new Vector2D(0, 0), -5);
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double deltaTime) {
        if(fadein && opacity<1d){
            opacity+=deltaTime/fadeTime;
        }
        else if(opacity > 0d){
            opacity-=deltaTime/fadeTime;
        }
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint){

        paint.setColor(Color.BLACK);
        paint.setAlpha((int)(opacity*255d));
        canvas.drawRect(0, 0, width, height, paint);

    }
}

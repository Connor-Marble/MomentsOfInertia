package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.Game.PlayerDeathListener;
import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 3/1/15.
 */
public class FadeOverlay extends GameEntity implements PlayerDeathListener{

    private int width, height;
    private double opacity = 1d;
    private boolean fadein;
    private double fadeTime = 1d;

    public FadeOverlay(int width, int height) {
        super(new Vector2D(0, 0), -10);
        this.width = width;
        this.height = height;
        fadein = true;
    }

    @Override
    public void update(double deltaTime) {

        width = parentView.getWidth();
        height = parentView.getHeight();

        if(fadein && opacity>0d){
            opacity-=deltaTime/fadeTime;
        }
        else if(opacity < 1d){
            opacity+=deltaTime/fadeTime;
        }
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint){
        if(opacity < 0d)
            return;

        paint.setColor(Color.BLACK);
        paint.setAlpha((int)(Math.max(opacity, 0d)*255d));
        canvas.drawRect(0, 0, width, height, paint);

        if(opacity > 1d && !fadein){
            parentView.gameOver = true;
        }
    }

    @Override
    public void onPlayerDeath() {
        fadein = false;
    }

    public double getOpacity(){
        return opacity;
    }
}

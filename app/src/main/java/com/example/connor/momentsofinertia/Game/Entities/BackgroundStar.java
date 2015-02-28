package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.connor.momentsofinertia.Game.Entities.GameEntity;
import com.example.connor.momentsofinertia.util.Vector2D;

import java.util.Random;

/**
 * Created by connor on 2/5/15.
 */
public class BackgroundStar extends GameEntity implements GameStartListener {

    public double distance;
    public int size;
    public double opacity;
    public int drawnX;


    Random random;

    public BackgroundStar(Vector2D position){
        super(position, 5);
        rollParams();
    }

    public void rollParams(){
        random = new Random();
        distance = random.nextDouble();
        size = (int)(distance*10 + 5d);
        opacity = distance/2 + 0.5d;
        position.y = random.nextDouble()*1000;
    }

    @Override
    public void update(double deltaTime){

    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint){
        deathCheck(xScroll);

        int scroll = (int)((double)xScroll * (distance/2 + 0.5));
        paint.setColor(Color.WHITE);
        paint.setAlpha((int)(opacity * 255d));
        canvas.drawRect(new Rect(scroll + (int)position.x, (int)position.y, scroll + size + (int)position.x,(int)position.y + size ),paint);
        drawnX = scroll + (int)position.x;
    }

    public void deathCheck(int xScroll){
        int scroll = (int)((double)xScroll * (distance/2 + 0.5));
        if(scroll + (int)position.x + size < 0){
            rollParams();

            scroll = (int)((double)xScroll * (distance/2 + 0.5));
            position.x = 2000-scroll;
        }
    }

    @Override
    public void gameStarted() {
        position.x = drawnX;
    }
}

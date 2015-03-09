package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.connor.momentsofinertia.util.FixedRingQueue;
import com.example.connor.momentsofinertia.util.Vector2D;

import java.util.Vector;

/**
 * Created by connoc on 3/8/2015.
 */
public class Trail extends GameEntity {

    FixedRingQueue<Vector2D> previousLocations;
    GameEntity trailing;
    float trailWidth;

    public Trail(int depth, int trailLength, GameEntity trailing, float trailWidth) {
        super(new Vector2D(0, 0), depth);


        previousLocations = new FixedRingQueue<Vector2D>(trailLength);
        this.trailing = trailing;
        this.trailWidth = trailWidth;
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        paint.setAlpha(255);
        paint.setStrokeWidth(trailWidth);
        for(int i =1 ;i<previousLocations.size();i++){
            float t = (float)i/(float)previousLocations.size();
            paint.setAlpha(255-(int)(t*255f));
            Vector2D firstPos = previousLocations.get(i-1);
            Vector2D secondPos = previousLocations.get(i);
            canvas.drawLine((float)firstPos.x + xScroll,
                    (float)firstPos.y,
                    (float)secondPos.x + xScroll,
                    (float)secondPos.y,
                    paint);
        }
    }

    @Override
    public void update(double deltaTime) {
        previousLocations.add(trailing.position.clone());
    }
}

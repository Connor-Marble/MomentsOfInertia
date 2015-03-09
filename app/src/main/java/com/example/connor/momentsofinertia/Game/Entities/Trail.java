package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connoc on 3/8/2015.
 */
public class Trail extends GameEntity {

    Color startColor;
    Color endColor;
    Vector2D[] previousLocations;

    public Trail(int depth, Color startColor, Color endColor, int trailLength) {
        super(new Vector2D(0, 0), depth);
        this.startColor = startColor;
        this.endColor = endColor;

        previousLocations = new Vector2D[trailLength];
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint) {
        for(int i =0;i<previousLocations.length;i++){
            float[] points = new float[4];

        }
    }

    @Override
    public void update(double deltaTime) {

    }
}

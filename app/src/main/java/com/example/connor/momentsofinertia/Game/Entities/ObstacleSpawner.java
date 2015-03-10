package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.util.Vector2D;

import java.util.Random;

/**
 * Created by connor on 3/9/15.
 */
public class ObstacleSpawner extends GameEntity {

    int xLocation = 600;
    Random random;
    float spawnChance = 0.01f;
    int height = 1000;

    public ObstacleSpawner(Vector2D position) {
        super(new Vector2D(0d, 0d));
        random = new Random();
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint) {
        while((-xScroll)+2000 > xLocation){
            advance(1, spawnChance);
        }
    }

    @Override
    public void update(double deltaTime) {
        height = parentView.getHeight();
    }

    public void advance(int distance, float probability){
        xLocation += distance;
        if(random.nextFloat() < probability){
            parentView.addEntity(new Obstacle(new Vector2D(xLocation, random.nextInt(height))));
        }
    }
}

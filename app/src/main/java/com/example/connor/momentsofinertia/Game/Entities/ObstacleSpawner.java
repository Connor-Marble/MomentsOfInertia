/**
 * Copyright(C) 2015 Connor Marble
 *
 * This file is part of the android game Moments of Inertia
 *
 * Moments of Inertia is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Moments of Inertia is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Moments of Inertia.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.util.Vector2D;

import java.util.Random;

/**
 * Created by connor on 3/9/15.
 */
public class ObstacleSpawner extends GameEntity implements GameStartListener {

    int xLocation = 600;
    Random random;
    float spawnChance = 0.01f;
    int height = 1000;
    private boolean gameStarted = false;
    float spawnRateRamp = 0.0002f;

    public ObstacleSpawner(Vector2D position) {
        super(new Vector2D(0d, 0d));
        random = new Random();
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint) {
        while((-xScroll)+2000 > xLocation && gameStarted){
            advance(1, spawnChance);
        }
    }

    @Override
    public void update(double deltaTime) {
        height = parentView.getHeight();

        if(gameStarted) {
            spawnChance += spawnRateRamp * (float)deltaTime;
        }
    }

    public void advance(int distance, float probability){
        xLocation += distance;
        if(random.nextFloat() < probability){
            parentView.addEntity(new Obstacle(new Vector2D(xLocation, random.nextInt(height))));
        }

    }

    @Override
    public void gameStarted() {
        gameStarted = true;
    }
}

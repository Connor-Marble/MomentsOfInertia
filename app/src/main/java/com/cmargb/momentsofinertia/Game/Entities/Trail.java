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

package com.cmargb.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.cmargb.momentsofinertia.util.FixedRingQueue;
import com.cmargb.momentsofinertia.util.Vector2D;

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

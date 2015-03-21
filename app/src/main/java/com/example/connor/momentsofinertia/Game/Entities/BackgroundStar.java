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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.connor.momentsofinertia.Game.Entities.GameEntity;
import com.example.connor.momentsofinertia.util.Vector2D;

import java.util.Random;

/**
 * Created by connor on 2/5/15.
 *
 * A decorative game entity which draws a 'star' in the background
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

    /**
     * randomly resets the parameters and
     * resets the star to the right side
     */
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

    /**
     * draws the star as a white rectangle
     * the star is offset and scaled based
     * on distance to create a 3D parallax effect
     *
     * @param xScroll
     * @param canvas
     * @param paint
     */
    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint){
        deathCheck(xScroll);

        int scroll = (int)((double)xScroll * (distance/2 + 0.5));
        paint.setColor(Color.WHITE);
        paint.setAlpha((int)(opacity * 255d));
        canvas.drawRect(new Rect(scroll + (int)position.x, (int)position.y, scroll + size + (int)position.x,(int)position.y + size ),paint);
        drawnX = scroll + (int)position.x;
    }

    /**
     * resets the star if it has scrolled off screen
     * @param xScroll the horizontal scroll of the camera
     */
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

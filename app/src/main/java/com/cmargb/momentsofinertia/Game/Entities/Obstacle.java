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
import android.graphics.Paint;
import android.graphics.Rect;

import com.cmargb.momentsofinertia.Game.Collidable;
import com.cmargb.momentsofinertia.util.Vector2D;

/**
 * Game entity that the player must avoid
 *
 * Created by connor on 2/6/15.
 */
public class Obstacle extends GameEntity implements Collidable, GameStartListener {
    public Rect collisionRect;
    private double sizeMultiplier = 0d;
    private double inflateTime = 0.25d;
    private boolean hasGameStarted = false;
    int outlineWidth = 5;

    public Obstacle(Vector2D position) {
        super(position, 0);
        setCollisionRect();
        if(hasGameStarted)
            sizeMultiplier = 1;
    }

    public Obstacle(Vector2D position, Rect collisionRect) {
        super(position);
        collisionRect = collisionRect;
    }

    @Override
    public boolean checkRect(Rect area) {
        if(area == null)
            return false;

        if(area.right < collisionRect.left)
            return false;

        if(area.left > collisionRect.right)
            return false;

        if(area.top > collisionRect.bottom)
            return  false;

        if(area.bottom < collisionRect.top)
            return false;

        return true;
    }

    private void setCollisionRect(){
        collisionRect = new Rect((int)(position.x-(75d*sizeMultiplier/2)), (int)(position.y-(75d*sizeMultiplier/2)),
                (int)(position.x + (75d*sizeMultiplier/2)), (int)(position.y+(75d*sizeMultiplier/2)));
    }

    @Override
    public boolean recieveRaycast(Vector2D origin, double angle, double distance) {
        return false;
    }

    @Override
    public void update(double deltaTime){
        if(sizeMultiplier<1 && hasGameStarted){
            sizeMultiplier+=deltaTime/inflateTime;
            setCollisionRect();
        }
    }

    @Override
    public void draw(int scroll, Canvas canvas, Paint paint){

        int colorMod = (int)(Math.sin(parentView.getTime()*5+(position.x/200d))*64d) + 192;

        drawOutline(scroll, canvas, paint);

        paint.setARGB(255, 255, 255-colorMod, 0);
        canvas.drawRect(new Rect(collisionRect.left + scroll, collisionRect.top, collisionRect.right + scroll,collisionRect.bottom), paint);
    }

    public void drawOutline(int scroll, Canvas canvas, Paint paint){
        paint.setARGB(255, 64, 64, 64);
        canvas.drawRect(new Rect(collisionRect.left + scroll -outlineWidth,
                collisionRect.top - outlineWidth,
                collisionRect.right + scroll + outlineWidth,
                collisionRect.bottom + outlineWidth), paint);
    }

    public void deathCheck(int xScroll){
        if(collisionRect.right + xScroll < 0)
            remove();
    }

    @Override
    public void gameStarted() {
        hasGameStarted = true;
    }
}

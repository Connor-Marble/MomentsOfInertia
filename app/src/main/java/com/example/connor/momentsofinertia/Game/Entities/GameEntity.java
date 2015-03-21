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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.Game.GameView;
import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Base class for all game entities.
 * this class should be extended by
 * things that are drawn as part of the
 * game view.
 *
 * Created by connor on 1/30/15.
 */
public abstract class GameEntity implements Comparable{
    GameView parentView;
    public Vector2D position;
    private int depth;
    public boolean isRemoved = false;

    public GameEntity(Vector2D position){
        this.position = position;
        this.depth = 0;
    }

    public GameEntity(Vector2D position, int depth){
        this.position = position;
        this.depth = depth;
    }

    public abstract void draw(int xScroll, Canvas canvas, Paint paint);

    public abstract void update(double deltaTime);

    public void setParentView(GameView view){
        parentView = view;
    }

    public void remove(){
        isRemoved = true;
    }

    @Override
    public int compareTo(Object another) {
        GameEntity other = (GameEntity)another;
        if (this.depth == other.depth)
                return 0;

        return (this.depth<other.depth)?1:-1;
    }
}

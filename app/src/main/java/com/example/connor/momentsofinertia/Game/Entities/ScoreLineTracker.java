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

import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 3/15/15.
 */
public class ScoreLineTracker extends GameEntity {

    Player player;
    Vector2D size;

    public ScoreLineTracker(Vector2D position, Player player, Vector2D size) {
        super(position, -1);
        this.player = player;
        this.size = size;
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5f);
        canvas.drawLine((float)(position.x - size.x + xScroll),
                (float)position.y ,
                (float)position.x + xScroll,
                (float)(position.y - size.y), paint);

        canvas.drawLine((float)(position.x + size.x + xScroll),
                (float)position.y ,
                (float)position.x + xScroll,
                (float)(position.y - size.y), paint);
    }

    @Override
    public void update(double deltaTime) {
        position.x = Math.max(player.position.x, position.x);
    }
}

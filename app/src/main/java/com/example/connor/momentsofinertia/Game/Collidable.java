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

package com.example.connor.momentsofinertia.Game;

import android.graphics.Rect;

import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 2/2/15.
 */
public interface Collidable {
    public Rect collisionRect = null;

    public boolean checkRect(Rect area);

    public boolean recieveRaycast(Vector2D origin, double angle, double distance);
}
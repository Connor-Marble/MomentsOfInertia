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

import com.example.connor.momentsofinertia.util.Vector2D;

import java.util.ArrayList;

/**
 * Created by connor on 2/1/15.
 */
public class Rope {

    public ArrayList<Vector2D> ropePoints;
    Vector2D endPoint;
    double restLength;

    public Rope(Vector2D startAnchor, Vector2D endPoint){
        ropePoints = new ArrayList<Vector2D>();
        ropePoints.add(startAnchor);
        this.endPoint = endPoint;
        restLength = Vector2D.distance(startAnchor, endPoint);
    }

    public void update(){

    }

}

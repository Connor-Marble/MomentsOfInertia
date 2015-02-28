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
package com.example.connor.momentsofinertia;

import android.graphics.Path;
import android.graphics.Rect;

/**
 * Created by connor on 2/2/15.
 */
public interface Collidable {
    public Rect collisionRect = null;

    public boolean checkRect(Rect area);

    public boolean RecieveRaycast(Vector2D origin, double angle, double distance);
}
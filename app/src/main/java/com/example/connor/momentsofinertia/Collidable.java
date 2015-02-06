package com.example.connor.momentsofinertia;

import android.graphics.Path;
import android.graphics.Rect;

/**
 * Created by connor on 2/2/15.
 */
public interface Collidable {
    public void checkRect(Rect area);

    public void ReicieveRaycast(Vector2D origin, double angle, double distance);
}

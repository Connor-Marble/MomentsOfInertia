package com.example.connor.momentsofinertia;

/**
 * Created by connor on 2/2/15.
 */
public class Collision {

    private Vector2D position;
    private Collidable collider;

    public Collidable getCollider() {
        return collider;
    }

    public void setCollider(Collidable collider) {
        this.collider = collider;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }
}

package com.example.connor.momentsofinertia.util;

/**
 * Created by connor on 1/30/15.
 */
public class Vector2D {
    public double x, y;
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getMagnitude(){
        return Math.sqrt(getSqrMagnitude());
    }

    public double getSqrMagnitude(){
        return Math.pow(x, 2) + Math.pow(y, 2);
    }

    public double getAngle(){
        return Math.toDegrees(Math.atan2(y, x));
    }

    public Vector2D getNormalized(){
        double magnitude = getMagnitude();
        double normalX = x/magnitude;
        double normalY = y/magnitude;
        return new Vector2D(normalX, normalY);
    }

    public static double dotProduct(Vector2D v1, Vector2D v2){
        double angle = Math.abs(Math.toRadians(v1.getAngle() - v2.getAngle()));

        double result = (angle -Math.PI)/Math.PI;
        return result;
    }

    public static double distance(Vector2D v1, Vector2D v2){
        double xDist = Math.abs(v1.x - v2.x);
        double yDist = Math.abs(v1.y - v2.y);

        return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
    }

    public Vector2D clone(){
        return new Vector2D(this.x, this.y);
    }
}

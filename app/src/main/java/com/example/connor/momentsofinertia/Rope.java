package com.example.connor.momentsofinertia;

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

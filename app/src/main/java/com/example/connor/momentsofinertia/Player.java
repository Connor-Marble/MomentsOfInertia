package com.example.connor.momentsofinertia;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by connor on 1/31/15.
 */
public class Player extends GameEntity {

    public Vector2D velocity;

    public Rope rope;

    final double GRAVITY = 100d;

    private ArrayList<PlayerDeathListener> deathListeners;

    private Rect collisionRect;

    public Player(Vector2D position){
        super(position, 0);
        velocity = new Vector2D(0, 0);
        deathListeners = new ArrayList<PlayerDeathListener>();

    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint){
        paint.setColor(Color.BLUE);
        canvas.drawRect(new Rect((int)position.x-5 + xScroll, (int)position.y-5,
                (int)position.x+5 + xScroll, (int)position.y + 5), paint);
        if(rope != null)
            drawRope(xScroll, canvas, paint);

    }

    public void drawRope(int xScroll, Canvas canvas, Paint paint){
        paint.setColor(Color.GRAY);
        paint.setAlpha(150);
        canvas.drawLine((float)rope.endPoint.x + xScroll,
                (float)rope.endPoint.y,
                (float)rope.ropePoints.get(0).x + xScroll,
                (float)rope.ropePoints.get(0).y, paint);
    }

    @Override
    public void update(double deltaTime){
        velocity.y += GRAVITY * deltaTime;

        if(rope!=null)
            addTension(deltaTime);


        position.y += velocity.y * deltaTime;
        position.x += velocity.x * deltaTime;

        parentView.addEntity(new PlayerTrail(new Vector2D(position.x, position.y)));

        collisionRect = new Rect((int)position.x-5, (int)position.y-5,
                (int)position.x+5, (int)position.y + 5);

        if (position.y > 1000)
            death();
    }

    private void addTension(double deltaTime){
        Vector2D swingPoint = rope.ropePoints.get(rope.ropePoints.size()-1);
        Vector2D ropeVector = new Vector2D(swingPoint.x - position.x, swingPoint.y - position.y);
        double ropeLength = ropeVector.getMagnitude() - rope.restLength;
        double ropePull = (Vector2D.distance(position, swingPoint)) * deltaTime * 0.002;

        velocity = new Vector2D(velocity.x + (ropeVector.x * ropePull), velocity.y + (ropeVector.y*ropePull));
    }

    public void registerDeathListener(PlayerDeathListener listener){
        deathListeners.add(listener);
    }

    private void notifyDeathListeners(){
        for(PlayerDeathListener listener : deathListeners){
            listener.onPlayerDeath();
        }
    }

    public void death(){
        notifyDeathListeners();
        velocity = new Vector2D(0, 0);
        position.y = 0d;
        position.x = 400;
    }

    public void createRope(Vector2D touchPosition){
        rope = new Rope(touchPosition, position);
    }

    public void checkObstacle(Obstacle obstacle){
        if(obstacle.checkRect(collisionRect))
            death();
    }
}

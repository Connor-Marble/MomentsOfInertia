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

package com.cmargb.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.TextView;

import com.cmargb.momentsofinertia.Game.PlayerDeathListener;
import com.cmargb.momentsofinertia.util.ScalingUtils;
import com.cmargb.momentsofinertia.util.Vector2D;

import java.util.ArrayList;

/**
 * Created by connor on 1/31/15.
 */
public class Player extends GameEntity {

    public Vector2D velocity;

    public Rope rope;

    final double GRAVITY = 100d;

    private ArrayList<PlayerDeathListener> deathListeners;

    public Rect collisionRect;

    public int score;
    private TextView scoreView;
    private int drawnX;

    public Player(Vector2D position){
        super(position, 0);
        velocity = new Vector2D(0, 0);
        deathListeners = new ArrayList<PlayerDeathListener>();

    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint){
        paint.setColor(Color.BLUE);
        canvas.drawRect(new Rect(
                ScalingUtils.cPX( (int)position.x-5 + xScroll),
                ScalingUtils.cPX((int)position.y-5),
                ScalingUtils.cPX((int)position.x+5 + xScroll),
                ScalingUtils.cPX((int)position.y + 5)), paint);
        if(rope != null)
            drawRope(xScroll, canvas, paint);
        drawnX = xScroll + (int)position.x;
    }

    public void drawRope(int xScroll, Canvas canvas, Paint paint){
        paint.setColor(Color.GRAY);
        paint.setAlpha(150);
        canvas.drawLine(
                (float) ScalingUtils.cPX((int)rope.endPoint.x + xScroll),
                (float)ScalingUtils.cPX((int)rope.endPoint.y),
                (float)ScalingUtils.cPX((int)rope.ropePoints.get(0).x + xScroll),
                (float)ScalingUtils.cPX((int)rope.ropePoints.get(0).y), paint);
    }

    @Override
    public void update(double deltaTime){
        velocity.y += GRAVITY * deltaTime;

        if(rope!=null)
            addTension(deltaTime);


        position.y += velocity.y * deltaTime;
        position.x += velocity.x * deltaTime;

        collisionRect = new Rect((int)position.x-5, (int)position.y-5,
                (int)position.x+5, (int)position.y + 5);

        if (!isInBounds())
            death();
        if((int)position.x > score){
            updateScore();
        }
    }

    private boolean isInBounds(){
        return ScalingUtils.cPX((int)position.y) < parentView.getHeight()&&
                ScalingUtils.cPX((int)position.y) > 0&&
                ScalingUtils.cPX((int)drawnX) > 0&&
                ScalingUtils.cPX((int)drawnX) < parentView.getWidth();
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
    }

    public void createRope(Vector2D touchPosition){
        rope = new Rope(touchPosition, position);
    }

    public void checkObstacle(Obstacle obstacle){
        if(obstacle.checkRect(collisionRect))
            death();
    }

    private void updateScore(){
        score = (int)position.x;
        scoreView.setText("Score: " + Integer.toString((score- 500)/100));
    }

    public void setScoreView(TextView scoreView){
        this.scoreView = scoreView;
    }
}

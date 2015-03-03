package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.Game.GameView;
import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 1/30/15.
 */
public abstract class GameEntity implements Comparable{
    GameView parentView;
    public Vector2D position;
    private int depth;
    public boolean isRemoved = false;

    public GameEntity(Vector2D position){
        this.position = position;
        this.depth = 0;
    }

    public GameEntity(Vector2D position, int depth){
        this.position = position;
        this.depth = depth;
    }

    public abstract void draw(int xScroll, Canvas canvas, Paint paint);

    public abstract void update(double deltaTime);

    public void setParentView(GameView view){
        parentView = view;
    }

    public void remove(){
        isRemoved = true;
    }

    @Override
    public int compareTo(Object another) {
        GameEntity other = (GameEntity)another;
        if (this.depth == other.depth)
                return 0;

        return (this.depth<other.depth)?1:-1;
    }
}

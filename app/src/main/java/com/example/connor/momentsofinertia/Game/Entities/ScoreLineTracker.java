package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 3/15/15.
 */
public class ScoreLineTracker extends GameEntity {

    Player player;
    Vector2D size;

    public ScoreLineTracker(Vector2D position, Player player, Vector2D size) {
        super(position, -1);
        this.player = player;
        this.size = size;
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5f);
        canvas.drawLine((float)(position.x - size.x + xScroll),
                (float)position.y ,
                (float)position.x + xScroll,
                (float)(position.y - size.y), paint);

        canvas.drawLine((float)(position.x + size.x + xScroll),
                (float)position.y ,
                (float)position.x + xScroll,
                (float)(position.y - size.y), paint);
    }

    @Override
    public void update(double deltaTime) {
        position.x = Math.max(player.position.x, position.x);
    }
}

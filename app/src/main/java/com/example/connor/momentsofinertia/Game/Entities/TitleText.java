package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.Game.GameView;
import com.example.connor.momentsofinertia.R;
import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 3/4/15.
 */
public class TitleText extends GameEntity implements GameStartListener {
    GameView parentView;
    Bitmap titleTextImage;
    double opacity = 255d;
    double fadeLength = 1d;
    boolean gameStarted;

    public TitleText(Vector2D position, GameView parentView) {
        super(position, -5);
        this.parentView = parentView;
        titleTextImage = BitmapFactory.decodeResource(parentView.getResources(), R.drawable.titletext);
    }

    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint) {
        paint.setAlpha((int)opacity);
        canvas.drawBitmap(titleTextImage,
                (parentView.getWidth() - titleTextImage.getWidth())/2,
                (parentView.getHeight()-titleTextImage.getHeight())/2, paint);
    }

    @Override
    public void update(double deltaTime) {
        if(gameStarted)
            opacity -= (255d/fadeLength)*deltaTime;

        if(opacity <=0){
            remove();
        }
    }

    @Override
    public void gameStarted() {
        gameStarted = true;
    }
}

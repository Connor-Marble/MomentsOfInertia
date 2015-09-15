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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cmargb.momentsofinertia.Game.GameView;
import com.cmargb.momentsofinertia.R;
import com.cmargb.momentsofinertia.util.Vector2D;

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

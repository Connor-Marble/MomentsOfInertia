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

package com.example.connor.momentsofinertia.Game.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.connor.momentsofinertia.Game.PlayerDeathListener;
import com.example.connor.momentsofinertia.util.Vector2D;

/**
 * Created by connor on 3/1/15.
 *
 * Game entity that is drawn as a black rectangle over the rest of the gameview
 * such that the game fades in from black when starting and fades out then the player
 * dies
 */
public class FadeOverlay extends GameEntity implements PlayerDeathListener{

    private int width, height;
    private double opacity = 1d;
    private boolean fadein;
    private double fadeTime = 1d;

    /**
     * Overlay initialized at 0,0 and a depth of -10
     * fadein and opacity are initialli true and 1 respectively,
     * such that the screen is completely black but
     * fading in when the game starts
     *
     * @param width the initial screen width
     * @param height the initial screen height
     */
    public FadeOverlay(int width, int height) {
        super(new Vector2D(0, 0), -10);
        this.width = width;
        this.height = height;
        fadein = true;
    }

    /**
     * increase opacity if fading out, decrease opacity if fading in
     * @param deltaTime
     */
    @Override
    public void update(double deltaTime) {

        width = parentView.getWidth();
        height = parentView.getHeight();

        if(fadein && opacity>0d){
            opacity-=deltaTime/fadeTime;
        }
        else if(opacity < 1d){
            opacity+=deltaTime/fadeTime;
        }
    }

    /**
     * draws black rectangle over the entire screen.
     *
     * @param xScroll
     * @param canvas
     * @param paint
     */
    @Override
    public void draw(int xScroll, Canvas canvas, Paint paint){
        if(opacity < 0d)
            return;

        paint.setColor(Color.BLACK);
        paint.setAlpha((int)(Math.max(opacity, 0d)*255d));
        canvas.drawRect(0, 0, width, height, paint);

        if(opacity > 1d && !fadein){
            parentView.gameOver = true;
        }
    }

    /**
     * begin fadeout when the player dies
     */
    @Override
    public void onPlayerDeath() {
        fadein = false;
    }

    public double getOpacity(){
        return opacity;
    }
}

package com.example.connor.momentsofinertia.Activities;

import com.example.connor.momentsofinertia.Game.Entities.BackgroundStar;
import com.example.connor.momentsofinertia.Game.Entities.ObstacleSpawner;
import com.example.connor.momentsofinertia.Game.Entities.TitleText;
import com.example.connor.momentsofinertia.Game.GameView;
import com.example.connor.momentsofinertia.Game.Entities.Obstacle;
import com.example.connor.momentsofinertia.Game.Entities.Player;
import com.example.connor.momentsofinertia.R;
import com.example.connor.momentsofinertia.util.Vector2D;
import com.example.connor.momentsofinertia.util.SystemUiHider;


import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.Window;

import java.util.Random;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class GameActivity extends Activity{

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_fullscreen);

        this.gameView = (GameView)findViewById(R.id.game_view);

    }

    @Override
    protected void onPause(){
        super.onPause();
        gameView.isRunning = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameView.isRunning = true;
        gameView.lastUpdate = System.currentTimeMillis();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        gameView.addEntity(new TitleText(new Vector2D(0, 0), gameView));
        ObstacleSpawner spawner = new ObstacleSpawner(new Vector2D(0, 0));
        gameView.addEntity(spawner);

        for(int i =0; i<4000; i+=100){
            gameView.addEntity(new BackgroundStar(new Vector2D(i,0)));;
        }

    }


}

package com.example.connor.momentsofinertia.Activities;

import com.example.connor.momentsofinertia.Game.Entities.BackgroundStar;
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
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_fullscreen);



        final View gameView = findViewById(R.id.game_view);



        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        final View view = findViewById(R.id.game_view);
        GameView gameView = (GameView)view;


        for(int i =0; i<4000; i+=100){

            gameView.addEntity(new BackgroundStar(new Vector2D(i,0)));

            Random random = new Random();
            gameView.addEntity(new Obstacle(new Vector2D(i + 1000, random.nextDouble() * 1000)));
        }

    }


}

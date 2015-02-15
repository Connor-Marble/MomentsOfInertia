package com.example.connor.momentsofinertia.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.connor.momentsofinertia.R;

/**
 * Created by connor on 2/12/15.
 */
public class MainMenuActivity extends Activity {

    Button gameStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainmenu);
        gameStartButton = (Button)findViewById(R.id.startGame);

        gameStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(MainMenuActivity.this, FullscreenActivity.class);
                startActivity(gameIntent);
            }
        });

    }
}

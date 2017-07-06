package com.example.abhirajkumar.snake;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import	android.widget.ImageButton;

import com.example.abhirajkumar.snake.R;

public class PlayActivity extends AppCompatActivity {

    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getWindow().getDecorView().setBackgroundColor(Color.rgb(120,200,100));

        ImageButton button = (ImageButton) findViewById(R.id.buttonPlay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameStatus.isGameOver = false;
                finish();
            }
        });
    }

}

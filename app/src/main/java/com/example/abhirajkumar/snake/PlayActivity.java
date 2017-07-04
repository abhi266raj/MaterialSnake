package com.example.abhirajkumar.snake;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;

import com.example.abhirajkumar.snake.R;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
       // View mainView = findViewById(R.id.firstView);

        // Find the root view
        //View root = mainView.getRootView();

        // Set the color
        //root.setBackgroundColor(Color.rgb(210,105,30));
        //root.setBackgroundColor(getResources().getColor(android.R.color.red));
        getWindow().getDecorView().setBackgroundColor(Color.rgb(210,105,30));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(PlayActivity.this, MotionActivity.class);
                    startActivity(intent);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
            }
        });
    }

}

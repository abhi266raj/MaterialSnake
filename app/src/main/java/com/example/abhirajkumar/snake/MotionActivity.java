package com.example.abhirajkumar.snake;

import android.content.Context;
import android.app.Application;
import android.os.Bundle;


import android.app.Activity;


public class MotionActivity extends Activity {


    public static Context gameContext;

    // gameView will be the view of the game
    // It will also hold the logic of the game
    // and respond to screen touches as well
    GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameContext = getApplicationContext();

        // Initialize gameView and set it as the view

        GameView gameView = new GameView(this);
        gameEngine = new GameEngine(gameView);
        setContentView(gameView);
        //setContentView(gameEngine.gameView);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        gameEngine.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        gameEngine.pause();
    }

}

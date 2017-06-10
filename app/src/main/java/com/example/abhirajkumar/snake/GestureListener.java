package com.example.abhirajkumar.snake;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by abhiraj.kumar on 6/10/17.
 */

class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private GameView gameView;
    public GestureListener (GameView gameView){
        this.gameView = gameView;

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }
    // event when double tap occurs
    @Override
    public boolean onDoubleTap(MotionEvent e) {

        if (gameView.isGameOver){
            gameView.makeSnake();
            gameView.isGameOver = false;

        }
        return true;
    }
}

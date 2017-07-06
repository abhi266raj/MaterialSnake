package com.example.abhirajkumar.snake;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;

/**
 * Created by abhiraj.kumar on 6/12/17.
 */

public class GameEngine implements Runnable {

    Thread gameThread = null;
    long fps;
    volatile boolean playing;
    GameView gameView;
    GameData gameData = new GameData();
    private long timeThisFrame;



    public GameEngine(GameView gameView) {
        playing = true;
        this.gameView = gameView;
        gameData.makeSnake(gameView);
        //GestureListener listner = new GestureListener(gameView);
        //listner.gameData = gameData;
        //gameView.gestureDetector = new GestureDetector(gameView.getContext(),listner);
    }

    @Override
    public void run() {
        while (playing) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            gameData.update(gameView);
            gameView.draw(gameData);


            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            long desiredFrameRate = 10;
            long extraTime;
            extraTime = (1000 / desiredFrameRate) - timeThisFrame;
            if (extraTime > 0) {
                try {
                    gameThread.sleep((long) extraTime);
                } catch (InterruptedException e) {

                }
            }
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }

        }

    }


    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }
}

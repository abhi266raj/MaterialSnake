package com.example.abhirajkumar.snake;

import java.util.ArrayList;

/**
 * Created by abhiraj.kumar on 6/12/17.
 */

public class GameData {

    long score = 0;
    boolean isGameOver = false;

    public void updateScore() {
        score += 10;

    }

    public void resetGameData(){
        score  = 0;
    }

    public void willMoveToQuantisedPoint(QunatisedPosition point,GameView gameView){
        if (point.x == gameView.food.position.x && point.y == gameView.food.position.y){
            gameView.foodEaten();
            updateScore();
        }


        for (QunatisedPosition position: gameView.snakePoints){
            if (position.x == point.x && position.y == point.y){
                //removeSnake();
                gameView.isGameOver = true;
                this.isGameOver = true;
                break;
            }

        }
    }



    //To do refactor
    public void makeSnake(GameView gameView){

        int snakeSizeInitial = 15;
        gameView.snakePoints = new ArrayList<QunatisedPosition>(snakeSizeInitial);
        for (int i= 0;i<snakeSizeInitial;i++) {
            gameView.snakePoints.add(new QunatisedPosition(i,0));
        }


    }
}

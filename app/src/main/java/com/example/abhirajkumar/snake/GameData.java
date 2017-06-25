package com.example.abhirajkumar.snake;

import java.util.ArrayList;

/**
 * Created by abhiraj.kumar on 6/12/17.
 */

public class GameData {

    static long maximumFoodValue = 30;
    static long minimumFoodValue = 10;
    static long maximumStepsForBonus = 2* (QunatisedPosition.maximumXPoint + QunatisedPosition.maximumYPoint) ;
    long score = 0;
    long foodValue = 10;
    long stepsTakenLastFood = 0;
    boolean isGameOver = false;

    public void updateScore() {
        score += foodValue;

    }

    public void resetGameData(){
        score  = 0;
        stepsTakenLastFood = 0;
    }

    public void willMoveToQuantisedPoint(QunatisedPosition point,GameView gameView){
        stepsTakenLastFood++;
        if (stepsTakenLastFood < maximumStepsForBonus){
            foodValue = maximumFoodValue;
        }else{
            foodValue = minimumFoodValue;
        }
        if (point.x == gameView.food.position.x && point.y == gameView.food.position.y){
            gameView.foodEaten();
            stepsTakenLastFood = 0;



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

    public void update(GameView gameView) {

        // If bob is moving (the player is touching the screen)
        // then move him to the right based on his target speed and the current fps.
        // if(isMoving){
        if (gameView.isGameOver){
            return;
        }
        QunatisedPosition firstPoint = gameView.snakePoints.get(0);
        QunatisedPosition lastPoint = gameView.snakePoints.get(gameView.snakePoints.size()-1);
        int xPositionToChange = lastPoint.x;
        int yPositionToChange = lastPoint.y;





        QunatisedPosition newPoint = new QunatisedPosition(xPositionToChange + gameView.direction.xDirectionSign.value() ,yPositionToChange + gameView.direction.yDirectionSign.value());
        this.willMoveToQuantisedPoint(newPoint,gameView);
        gameView.snakePoints.remove(0);
        gameView.snakePoints.add(newPoint);

    }


}

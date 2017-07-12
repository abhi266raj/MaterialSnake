package com.example.abhirajkumar.snake;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.content.Context;
import 	java.util.concurrent.TimeUnit;

import com.example.abhirajkumar.snake.GameBasics.GameStatus;
import com.example.abhirajkumar.snake.Mission.GameMission;
import com.example.abhirajkumar.snake.Mission.MissionFactory;
import com.example.abhirajkumar.snake.Mission.MissionStaus;


/**
 * Created by abhiraj.kumar on 6/12/17.
 */

public class GameData {

    public long maxHighScore;
    static long maximumFoodValue = 30;
    static long minimumFoodValue = 10;
    boolean isFoodInBonusMode = true;
    public float normalisedTimeLeftForBonus = 1; //number between 0 to 1
    static long maximumStepsForBonus = (QunatisedPosition.maximumXPoint + QunatisedPosition.maximumYPoint)/QunatisedPosition.itemSize ;
    long score = 0;
    long foodValue = 10;
    public long stepsTakenLastFood = 0;

    public ArrayList<QunatisedPosition> snakePoints;
    public ArrayList<QunatisedPosition> dangerPoints = new ArrayList<QunatisedPosition>(0);
    GameMission currentMission = MissionFactory.getNewMission(this);



    public GameData(){
        //SharedPreferences sharedPref = getSharedPreferences("FileName",MODE_PRIVATE);
        //SharedPreferences.Editor prefEditor = sharedPref.edit();
        SharedPreferences preferences = MotionActivity.gameContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        //SharedPreferences.Editor editor = preferences.edit();
        //editor.putInt("highScore", myvar);
        //editor.commit();


//--READ data
        maxHighScore = preferences.getLong("highScore", 0);
    }
    public void updateScore() {
        score += foodValue;
        updateMaxScore();
    }

    public void updateMaxScore(){
        if (maxHighScore < score){
            maxHighScore = score;
            SharedPreferences preferences = MotionActivity.gameContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong("highScore", maxHighScore);
            editor.commit();

        }
    }

    public void resetGameData(){
        score  = 0;
        stepsTakenLastFood = 0;
        MissionFactory.resetData();
        currentMission = MissionFactory.getNewMission(this);
    }

    public void willMoveToQuantisedPoint(QunatisedPosition point,GameView gameView){
        stepsTakenLastFood++;
        if (stepsTakenLastFood < maximumStepsForBonus){
            foodValue = maximumFoodValue * currentMission.missionNumber;
            isFoodInBonusMode = true;
            normalisedTimeLeftForBonus = 1 - (float)stepsTakenLastFood/(float)maximumStepsForBonus;
        }else{
            foodValue = minimumFoodValue * currentMission.missionNumber;
            isFoodInBonusMode = false;
            normalisedTimeLeftForBonus = 0;
        }
        if (point.x == gameView.food.position.x && point.y == gameView.food.position.y){
            this.foodEaten(gameView);
            stepsTakenLastFood = 0;



            updateScore();
        }


        for (QunatisedPosition position: snakePoints){
            if (position.x == point.x && position.y == point.y){
                GameStatus.isGameOver = true;
                break;
            }

        }
    }




    private void foodEaten(GameView gameView){
        snakePoints.add(0,snakePoints.get(0));
        gameView.food.updateFood();
        currentMission.foodEaten();
        if (currentMission.currentMissionStatus == MissionStaus.Completed){

            currentMission = MissionFactory.getNewMission(this);
        }


    }
    //To do refactor
    public void makeSnake(GameView gameView){

        int snakeSizeInitial = 20;
        snakePoints = new ArrayList<QunatisedPosition>(snakeSizeInitial);
        for (int i= 0;i<snakeSizeInitial;i++) {
            snakePoints.add(new QunatisedPosition(i,0));
        }
        gameView.direction = new MotionDirection(DirectionSign.ZERO,DirectionSign.POSITIVE);



    }

    public void update(GameView gameView) {

        // If bob is moving (the player is touching the screen)
        // then move him to the right based on his target speed and the current fps.
        // if(isMoving){
        if (GameStatus.isGameOver){
            return;
        }
        QunatisedPosition firstPoint = snakePoints.get(0);
        QunatisedPosition lastPoint = snakePoints.get(snakePoints.size()-1);
        int xPositionToChange = lastPoint.x;
        int yPositionToChange = lastPoint.y;





        QunatisedPosition newPoint = new QunatisedPosition(xPositionToChange + gameView.direction.xDirectionSign.value() ,yPositionToChange + gameView.direction.yDirectionSign.value());
        this.willMoveToQuantisedPoint(newPoint,gameView);
       if (GameStatus.isGameOver){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){

            }
           //snakePoints.remove(0);
            makeSnake(gameView);
            resetGameData();

        }else {
            snakePoints.remove(0);
            snakePoints.add(newPoint);
        }

    }


}

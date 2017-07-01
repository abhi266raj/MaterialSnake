package com.example.abhirajkumar.snake;

import java.util.ArrayList;
import android.content.SharedPreferences;
import android.content.Context;
import android.app.Activity;
import android.preference.PreferenceManager;


/**
 * Created by abhiraj.kumar on 6/12/17.
 */

public class GameData {

    public long maxHighScore;
    static long maximumFoodValue = 30;
    static long minimumFoodValue = 10;
    boolean isFoodInBonusMode = true;
    static long maximumStepsForBonus = (QunatisedPosition.maximumXPoint + QunatisedPosition.maximumYPoint)/QunatisedPosition.itemSize ;
    long score = 0;
    long foodValue = 10;
    long stepsTakenLastFood = 0;
    boolean isGameOver = false;
    ArrayList<QunatisedPosition> snakePoints;
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
        }else{
            foodValue = minimumFoodValue * currentMission.missionNumber;
            isFoodInBonusMode = false;
        }
        if (point.x == gameView.food.position.x && point.y == gameView.food.position.y){
            this.foodEaten(gameView);
            stepsTakenLastFood = 0;



            updateScore();
        }


        for (QunatisedPosition position: snakePoints){
            if (position.x == point.x && position.y == point.y){
                //removeSnake();
                gameView.isGameOver = true;
                this.isGameOver = true;
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

        int snakeSizeInitial = 15;
        snakePoints = new ArrayList<QunatisedPosition>(snakeSizeInitial);
        for (int i= 0;i<snakeSizeInitial;i++) {
            snakePoints.add(new QunatisedPosition(i,0));
        }


    }

    public void update(GameView gameView) {

        // If bob is moving (the player is touching the screen)
        // then move him to the right based on his target speed and the current fps.
        // if(isMoving){
        if (gameView.isGameOver){
            return;
        }
        QunatisedPosition firstPoint = snakePoints.get(0);
        QunatisedPosition lastPoint = snakePoints.get(snakePoints.size()-1);
        int xPositionToChange = lastPoint.x;
        int yPositionToChange = lastPoint.y;





        QunatisedPosition newPoint = new QunatisedPosition(xPositionToChange + gameView.direction.xDirectionSign.value() ,yPositionToChange + gameView.direction.yDirectionSign.value());
        this.willMoveToQuantisedPoint(newPoint,gameView);
        snakePoints.remove(0);
        snakePoints.add(newPoint);

    }


}

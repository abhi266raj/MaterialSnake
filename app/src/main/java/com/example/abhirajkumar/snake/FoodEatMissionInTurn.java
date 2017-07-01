package com.example.abhirajkumar.snake;
import static java.lang.String.*;

/**
 * Created by abhiraj.kumar on 7/1/17.
 */
public class FoodEatMissionInTurn extends  GameMission{


    int turnUnderWhichFoodShouldBeEaten = 10;




    @Override
    public  void foodEaten(){
        if (data.stepsTakenLastFood <= turnUnderWhichFoodShouldBeEaten){
            this.currentMissionStatus = MissionStaus.Completed;
        }else{

        }
    }

    @Override
    public String objective() {
        String output =  "M:" + valueOf(this.missionNumber) +  "  Food before steps" + valueOf(this.turnUnderWhichFoodShouldBeEaten) ;
        return output;
    }
}

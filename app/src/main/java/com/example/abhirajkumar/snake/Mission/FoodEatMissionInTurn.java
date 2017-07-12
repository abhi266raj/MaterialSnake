package com.example.abhirajkumar.snake.Mission;

import com.example.abhirajkumar.snake.GameData;
import com.example.abhirajkumar.snake.QunatisedPosition;

import java.util.ArrayList;

import static java.lang.String.*;

/**
 * Created by abhiraj.kumar on 7/1/17.
 */
public class FoodEatMissionInTurn extends  GameMission{


    int turnUnderWhichFoodShouldBeEaten = 20;




    @Override
    public  void foodEaten(){
        if (getData().stepsTakenLastFood <= turnUnderWhichFoodShouldBeEaten){
            this.currentMissionStatus = MissionStaus.Completed;
        }else{

        }
    }

    @Override
    public String objective() {
        String output =  "M:" + valueOf(this.missionNumber) +  "  Food before steps" + valueOf(this.turnUnderWhichFoodShouldBeEaten) ;
        return output;
    }

   @Override
    public void setData(GameData data){
        super.setData (data);
//        data.dangerPoints = new ArrayList<QunatisedPosition> (0);
//        for (int i= 0;i<20;i++) {
//            data.dangerPoints.add(new QunatisedPosition (i,0));
//        }


    }


}

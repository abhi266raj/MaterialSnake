package com.example.abhirajkumar.snake;

/**
 * Created by abhiraj.kumar on 6/29/17.
 */

public class MissionFactory {


    static int missionNumber = 0;
    public  static GameMission getNewMission(GameData data){
        missionNumber++;
        GameMission mission =  new FoodEatMissionInTurn();
        mission.data = data;
        mission.missionNumber = missionNumber;
        return mission;
    }


    public static void resetData(){
       missionNumber = 0;
    }
}

package com.example.abhirajkumar.snake.Mission;

import com.example.abhirajkumar.snake.GameData;

/**
 * Created by abhiraj.kumar on 6/29/17.
 */


public abstract  class GameMission {

    private GameData data;
    public MissionStaus currentMissionStatus = MissionStaus.OnGoing;
    public int missionNumber = 0;
    public abstract void foodEaten();
    public abstract String objective();
    public void setData(GameData data){
        this.data = data;
    }

    public GameData getData()
    {
        //include validation, logic, logging or whatever you like here
        return this.data;
    }
}



package com.example.abhirajkumar.snake.Mission;

import com.example.abhirajkumar.snake.GameData;

/**
 * Created by abhiraj.kumar on 6/29/17.
 */


public abstract  class GameMission {

    public GameData data;
    public MissionStaus currentMissionStatus = MissionStaus.OnGoing;
    public int missionNumber = 0;
    public abstract void foodEaten();
    public abstract String objective();
}



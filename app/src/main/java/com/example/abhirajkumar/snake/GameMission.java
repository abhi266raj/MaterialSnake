package com.example.abhirajkumar.snake;

/**
 * Created by abhiraj.kumar on 6/29/17.
 */


public abstract  class GameMission {

    GameData data;
    MissionStaus currentMissionStatus = MissionStaus.OnGoing;
    int missionNumber = 0;
    public abstract void foodEaten();
    public abstract String objective();
}



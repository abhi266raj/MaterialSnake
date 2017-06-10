package com.example.abhirajkumar.snake;

/**
 * Created by abhiraj.kumar on 6/10/17.
 */

public class Food{



    QunatisedPosition position;
    FoodType type;

    public void updateFood(){
        int randomNumX =  (int)(Math.random() * 10000);
        int randomNumY =  (int)(Math.random() * 10000);
        position = new QunatisedPosition(randomNumX,randomNumY);

    }

    Food(){
        type = FoodType.Healthy;
        updateFood();
    }





}
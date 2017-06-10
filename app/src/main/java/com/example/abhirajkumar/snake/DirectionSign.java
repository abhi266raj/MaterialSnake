package com.example.abhirajkumar.snake;

/**
 * Created by abhiraj.kumar on 6/8/17.
 */

public enum DirectionSign {
    POSITIVE (1),
    NEGATIVE(-1),
    ZERO(0);

    int sign;

    DirectionSign(int i) {
        sign = i;
    }
    int value(){
            return sign;
    }
};



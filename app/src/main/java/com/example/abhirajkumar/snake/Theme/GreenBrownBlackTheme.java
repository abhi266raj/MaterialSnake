package com.example.abhirajkumar.snake.Theme;

import android.graphics.Color;

import static android.graphics.Color.*;

/**
 * Created by abhiraj.kumar on 7/6/17.
 */

public class GreenBrownBlackTheme implements ThemeInterFace {

    public int primaryColor(){
        return Color.rgb(40,180,70);

    }

    public int secondaryColor(){
        return Color.rgb(160,82,45);

    }
    public int tertiaryColor(){
        return Color.rgb(70, 70, 70);

    }

    public int quadnaryColor(){
        return Color.rgb(183,28,28);
    }

    public int primaryColorGradientVariant(){
        return Color.rgb(120, 200, 100);

    }

    public int secondaryColorGradientVariant(){
        return Color.rgb(210,105,30);

    }

    public int tertiaryColorGradientVariant(){
        return Color.rgb(210,105,30);

    }

    public int quadnaryColorGradientVariant(){
        return Color.rgb ( 70,70,70);
    }
}

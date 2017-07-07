package com.example.abhirajkumar.snake;

import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;

import com.example.abhirajkumar.snake.Theme.Theme;

/**
 * Created by abhiraj.kumar on 7/6/17.
 */

public class ThemeBasedGradientPaint {

    Paint primaryColorPaint;
    Paint secondaryColorPaint;
    Shader primaryShader;
    Shader secondaryShader;


    ThemeBasedGradientPaint(){

        // paint.setColor(Color.rgb(121, 85, 72));
        int x1 = QunatisedPosition.xOffset, y1 = QunatisedPosition.yOffset, x2 = QunatisedPosition.maximumXPoint,  y2 = QunatisedPosition.maximumYPoint;
        primaryShader = new RadialGradient (x2/2,y2/2,QunatisedPosition.itemSize*10, Theme.currentTheme.primaryColorGradientVariant (),Theme.currentTheme.primaryColor (), Shader.TileMode.CLAMP);
        primaryColorPaint = new Paint();
        primaryColorPaint.setColor(Theme.currentTheme.primaryColor ());
        primaryColorPaint.setShader(primaryShader);

        int gradientEndColor = Theme.currentTheme.secondaryColorGradientVariant();
        int gradinetStartColor = Theme.currentTheme.secondaryColor ();
        secondaryShader = new LinearGradient (0,0,0,QunatisedPosition.itemSize*5,gradinetStartColor,gradientEndColor, Shader.TileMode.MIRROR);
        secondaryColorPaint = new Paint();
        secondaryColorPaint.setShader(secondaryShader);

    }

}

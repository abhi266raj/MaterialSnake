package com.example.abhirajkumar.snake;

import android.content.res.Resources;
import android.graphics.Point;

/**
 * Created by abhiraj.kumar on 6/10/17.
 */

public class QunatisedPosition{
    int x;
    int y;

    int scoreBoardStartY;
    int scoreBoardEndY;

    private int maxX;
    private int maxY;
    int xOffset;
    int yOffset;


    int maximumXPoint;
    int maximumYPoint;
    int itemSize;
    QunatisedPosition(int x,int y){
        this.x = x;
        this.y = y;
        resetItemSize();
        quantizeValue();
        setMaximumXAndYPoint();
    }

    private void setMaximumXAndYPoint(){
        maximumXPoint = maxX*itemSize+xOffset;
        maximumYPoint = maxY*itemSize+yOffset;
    }


    public void quantizeValue(){
        x = (2*maxX+x)%maxX;
        y = (2*maxY+y)%maxY;

    }

    public Point convertToPoint(){
        quantizeValue();
        return new Point( x*itemSize + xOffset, y*itemSize + yOffset);


        // return new Point();
    }


    public void resetItemSize(){
        // DisplayMetrics displayMetrics = new DisplayMetrics();
        // getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int minimumItemCount = 20;
        int height = (80 * Resources.getSystem().getDisplayMetrics().heightPixels)/100;
        int width = (90 * Resources.getSystem().getDisplayMetrics().widthPixels)/100;

        scoreBoardStartY = (Resources.getSystem().getDisplayMetrics().heightPixels * 5)/100;
        scoreBoardEndY = (Resources.getSystem().getDisplayMetrics().heightPixels * 13)/100;
        yOffset = (Resources.getSystem().getDisplayMetrics().heightPixels * 15)/100;
        xOffset = (Resources.getSystem().getDisplayMetrics().widthPixels *5)/100;
        if (height >= width){
            maxX = minimumItemCount;
            itemSize = width/(minimumItemCount);
            maxY = height/itemSize;

        }else{
            maxX = minimumItemCount;
            itemSize = height/minimumItemCount;
            maxX = width/itemSize;
        }


    }



}

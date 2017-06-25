package com.example.abhirajkumar.snake;

import android.content.res.Resources;
import android.graphics.Point;

/**
 * Created by abhiraj.kumar on 6/10/17.
 */

public class QunatisedPosition{
    int x;
    int y;

    static int scoreBoardStartY;
    static int scoreBoardEndY;

    static int consoleStartY;
    static int consoleEndY;

    private static int maxX;
    private static int maxY;
    static int xOffset;
    static int yOffset;


    static int maximumXPoint;
    static int maximumYPoint;
    static int itemSize;


    static{
        scoreBoardStartY = (Resources.getSystem().getDisplayMetrics().heightPixels * 2)/100;
        scoreBoardEndY = (Resources.getSystem().getDisplayMetrics().heightPixels * 10)/100;


        consoleStartY = (Resources.getSystem().getDisplayMetrics().heightPixels * 87)/100;
        consoleEndY = (Resources.getSystem().getDisplayMetrics().heightPixels * 95)/100;

        int minimumItemCount = 20;
        int height = (74 * Resources.getSystem().getDisplayMetrics().heightPixels)/100;
        int width = (90 * Resources.getSystem().getDisplayMetrics().widthPixels)/100;


        yOffset = (Resources.getSystem().getDisplayMetrics().heightPixels * 12)/100;
        xOffset = (Resources.getSystem().getDisplayMetrics().widthPixels *5)/100;
        if (height >= width){
            maxX = minimumItemCount;
            itemSize = width/(minimumItemCount);
            maxY = height/itemSize;

        }else{
            maxY = minimumItemCount;
            itemSize = height/minimumItemCount;
            maxX = width/itemSize;
        }

        maximumXPoint = maxX*itemSize+xOffset;

        maximumYPoint = maxY*itemSize+yOffset;

    }


    QunatisedPosition(int x,int y){
        this.x = x;
        this.y = y;
       // resetItemSize();
        quantizeValue();
        //setMaximumXAndYPoint();
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

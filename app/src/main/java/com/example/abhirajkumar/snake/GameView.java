package com.example.abhirajkumar.snake;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Shader;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.content.Intent;


import com.example.abhirajkumar.snake.GameBasics.GameStatus;
import com.example.abhirajkumar.snake.Theme.Theme;

import static java.lang.String.*;


class GameView extends SurfaceView {




    MotionDirection direction = new MotionDirection(DirectionSign.ZERO,DirectionSign.POSITIVE);
    Point touchPointStart;
    SurfaceHolder ourHolder;
    // A Canvas and a Paint object
    Canvas canvas;
    Canvas backgroundCanvas;
    // This is our thread



    //Point foodPoint;
    Paint paint;

    // A boolean which we will set and unset
    // when the game is running- or not.
    //volatile boolean playing;
    Paint gradinetPaintGreen;
    Paint gradinetPaintBrown;

    //private boolean shouldShowFPS = false;
    public Food food = new Food();
    //Paint gradinetPaintBlack;

    // This variable tracks the game frame rate
    //long fps;
   // frameDelay = 30;

    // This is used to help calculate the fps
    //private long timeThisFrame;

    // Declare an object of type Bitmap


    // Bob starts off not moving


    // He can walk at 150 pixels per second


    // He starts 10 pixels from the left

    // When the we initialize (call new()) on gameView
    // This special constructor method runs
    public GameView(Context context) {
        // The next line of code asks the
        // SurfaceView class to set up our object.
        // How kind.
        super(context);

        // Initialize ourHolder and paint objects
        ourHolder = getHolder();
        paint = new Paint();


        // Load Bob from his .png file


        // Set our boolean to true - game on!
        //playing = true;
        //makeSnake();
        ThemeBasedGradientPaint paintCreator = new ThemeBasedGradientPaint ();
        gradinetPaintGreen = paintCreator.primaryColorPaint;
        gradinetPaintBrown = paintCreator.secondaryColorPaint;


    }




    // Draw the newly updated scene
    public void draw(GameData data) {
        if (GameStatus.isGameOver){
            Intent intent = new Intent(((Activity)this.getContext()), PlayActivity.class);

            ((Activity)this.getContext()).startActivity(intent);
            return;
        }

        if (ourHolder.getSurface().isValid()) {


            canvas = ourHolder.lockCanvas();



            canvas.drawColor(Theme.currentTheme.tertiaryColor ());
            this.drawBorder(canvas,gradinetPaintBrown);





            // Make the text a bit bigger
            paint.setTextSize(45);

            // Display the current fps on the screen
//            if (shouldShowFPS) {
//                canvas.drawText("FPS:" + fps, 20, 40, paint);
//
//            }


            this.drawRoundRect(QunatisedPosition.xOffset, QunatisedPosition.scoreBoardStartY, QunatisedPosition.maximumXPoint, QunatisedPosition.scoreBoardEndY,QunatisedPosition.itemSize/2,QunatisedPosition.itemSize/2, gradinetPaintGreen,canvas);
            //canvas.drawRect(0, QunatisedPosition.consoleStartY, QunatisedPosition.maximumXPoint + QunatisedPosition.xOffset, QunatisedPosition.consoleEndY,paint);

            this.drawRoundRect(food.position.xOffset, QunatisedPosition.consoleStartY, food.position.maximumXPoint, QunatisedPosition.consoleEndY,food.position.itemSize/2,food.position.itemSize/2, gradinetPaintGreen,canvas);

            float textHeight = QunatisedPosition.scoreBoardEndY - QunatisedPosition.scoreBoardStartY;

            this.drawRoundRect(food.position.xOffset, food.position.yOffset, food.position.maximumXPoint, food.position.maximumYPoint,food.position.itemSize/2,food.position.itemSize/2, gradinetPaintGreen,canvas);


            paint.setColor(Theme.currentTheme.tertiaryColor ());
            paint.setTextSize((textHeight)/3);

            paint.setStrokeWidth(10);

            int itemSize =  QunatisedPosition.itemSize;
                int fullSnakeSize = data.snakePoints.size();
                for (int i = 0; i < data.snakePoints.size(); i++) {
                    Point pixelPosition = data.snakePoints.get(i).convertToPoint();
                /* int itemSize = snakePoints.get(i).itemSize; */
                int roundXValue = itemSize/2;
                    int roundYValue = roundXValue;
                    int sizeReduction = 1;
                    if (i == fullSnakeSize-1){
                        roundXValue = itemSize/3;
                        roundYValue = itemSize/3;
                        sizeReduction = 0;
                        this.drawHead(pixelPosition.x,  pixelPosition.y , pixelPosition.x + itemSize, pixelPosition.y + itemSize,paint);


                    }else {
                        this.drawRoundRect(pixelPosition.x + sizeReduction, pixelPosition.y + sizeReduction, pixelPosition.x + itemSize - sizeReduction, pixelPosition.y + itemSize - sizeReduction, roundXValue, roundYValue, paint,canvas);
                    }
                }
                paint.setColor(Theme.currentTheme.tertiaryColor ());
                canvas.drawText("Score = "+ valueOf(data.score), QunatisedPosition.xOffset + QunatisedPosition.itemSize,  QunatisedPosition.scoreBoardEndY - textHeight/8 , paint);
                canvas.drawText("Hi-Score = " + valueOf(data.maxHighScore), QunatisedPosition.xOffset + QunatisedPosition.itemSize,  QunatisedPosition.scoreBoardEndY - (textHeight/8)*5 , paint);


            canvas.drawText("Steps= "+ valueOf(data.stepsTakenLastFood) + "  Score Bonus = "+valueOf(data.foodValue), QunatisedPosition.xOffset + QunatisedPosition.itemSize,  QunatisedPosition.consoleEndY - textHeight/8 , paint);
            canvas.drawText(data.currentMission.objective(), QunatisedPosition.xOffset + QunatisedPosition.itemSize,  QunatisedPosition.consoleEndY - (textHeight/8)*5 , paint);


            Point foodPoint = food.position.convertToPoint();
            this.drawFood(foodPoint.x, foodPoint.y,foodPoint.x + itemSize, foodPoint.y + itemSize, paint,data);


            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);
        }





    }



    public void drawBorder(Canvas canvas, Paint paint){
        //paint.setColor(Color.rgb(121, 85, 72));
        //canvas.drawRect(0,100,);
        for (int i = 0;i<10;i++) {
            int startY = QunatisedPosition.screenHeight*i/10;
            int height = QunatisedPosition.screenHeight/10 - QunatisedPosition.itemSize/5;
            this.drawTiles(0, startY, QunatisedPosition.xOffset, startY+height, paint,canvas);
            this.drawTiles(QunatisedPosition.maximumXPoint, startY, QunatisedPosition.screenWidth, startY+height, paint,canvas);

        }

        for (int i = 0;i<7;i++){
            int startX = QunatisedPosition.screenWidth*i/7;
            int width =QunatisedPosition.screenWidth/7 - QunatisedPosition.itemSize/5;
            this.drawTiles(startX, 0, startX+width, QunatisedPosition.yOffset, paint,canvas);
            this.drawTiles(startX, QunatisedPosition.maximumYPoint, startX+width, QunatisedPosition.screenHeight, paint,canvas);

            //this.drawTiles(QunatisedPosition.maximumXPoint, startY, QunatisedPosition.screenWidth, startY+height, paint,canvas);



        }

        //this.drawRoundRect(0,0,QunatisedPosition.xOffset,QunatisedPosition.screenHeight,QunatisedPosition.itemSize,QunatisedPosition.itemSize,paint,canvas);

    }


    public void drawTiles(float left,float top,float right,float bottom, Paint paint, Canvas canvasToDraw){
        canvas.drawRect(left, top, right, bottom, paint);
    }

    public void drawRoundRect(float left,float top,float right,float bottom, float rx,float ry,Paint paint, Canvas canvasToDraw){
        if(android.os.Build.VERSION.SDK_INT>=21) {
            canvasToDraw.drawRoundRect(left, top, right, bottom, rx, ry, paint);
        }else{
            canvasToDraw.drawRect(left, top, right, bottom, paint);
        }
    }



    public void drawHead(int x, int y, int x2, int y2, Paint paint){
        this.drawRoundRect(x,y,x2,y2, (x2-x)/3,(x2-x)/3,paint,canvas);
        paint.setColor(Theme.currentTheme.primaryColorGradientVariant ());
        int midX =  (x+x2)/2;
        int midY =  (y + y2)/2;
        canvas.drawCircle(midX,midY,(x2-x)/8,paint);

    }

    public void drawFood(int x, int y, int x2, int y2, Paint paint,GameData data){

        int midX =  (x+x2)/2;
        int midY =  (y + y2)/2;
        paint.setColor(Theme.currentTheme.tertiaryColor ());
        canvas.drawCircle(midX,midY,(x2-x)/2,paint);
        paint.setColor(Theme.currentTheme.quadnaryColor ());

        if (data.isFoodInBonusMode == true) {
            if(android.os.Build.VERSION.SDK_INT>=21) {
                float angleSweep = 360 * data.normalisedTimeLeftForBonus;
             canvas.drawArc(x,y,x2,y2,0,angleSweep,true,paint);
            }else{
                canvas.drawCircle(midX,midY,(x2-x)/2,paint);
            }
        }

    }



    // The SurfaceView class implements onTouchListener
    // So we can override this method and detect screen touches.
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {


        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {




            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                // Set isMoving so Bob is moved in the update method
               // direction.yDirectionSign = DirectionSign.POSITIVE;
               // direction.xDirectionSign = DirectionSign.ZERO;
                //isMoving = true;
                touchPointStart = new Point(Math.round(motionEvent.getX()),Math.round(motionEvent.getY()));

                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:

                // Set isMoving so Bob does not move
                //direction.xDirectionSign = DirectionSign.POSITIVE;
                //direction.yDirectionSign = DirectionSign.ZERO;
                //isMoving = false;
                Point touchPointEnd = new Point(Math.round(motionEvent.getX()),Math.round(motionEvent.getY()));
                int threshold = QunatisedPosition.itemSize;

                if (direction.xDirectionSign != DirectionSign.ZERO){
                    if (touchPointStart.y -touchPointEnd.y > threshold){
                        direction.xDirectionSign = DirectionSign.ZERO;
                        direction.yDirectionSign = DirectionSign.NEGATIVE;

                    }
                    if (- touchPointStart.y +touchPointEnd.y > threshold){
                        direction.xDirectionSign = DirectionSign.ZERO;
                        direction.yDirectionSign = DirectionSign.POSITIVE;

                    }

                }else {
                    if (direction.yDirectionSign != DirectionSign.ZERO) {
                        if (touchPointStart.x - touchPointEnd.x > threshold) {
                            direction.yDirectionSign = DirectionSign.ZERO;
                            direction.xDirectionSign = DirectionSign.NEGATIVE;

                        }
                        if (-touchPointStart.x + touchPointEnd.x > threshold) {
                            direction.yDirectionSign = DirectionSign.ZERO;
                            direction.xDirectionSign = DirectionSign.POSITIVE;

                        }

                    }
                }

                break;
        }
        return true;
    }







    /**
     * Created by abhiraj.kumar on 6/10/17.
     */


}
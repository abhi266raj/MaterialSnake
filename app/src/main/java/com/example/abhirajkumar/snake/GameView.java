package com.example.abhirajkumar.snake;



import android.content.Context;
import java.util.ArrayList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.GestureDetector;
import android.graphics.Shader;
import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;

import static java.lang.String.*;


class GameView extends SurfaceView {


    GestureDetector gestureDetector;
    boolean isGameOver = false;
    MotionDirection direction = new MotionDirection(DirectionSign.ZERO,DirectionSign.POSITIVE);
    Point touchPointStart;
    SurfaceHolder ourHolder;
    // A Canvas and a Paint object
    Canvas canvas;
    // This is our thread



    //Point foodPoint;
    Paint paint;

    // A boolean which we will set and unset
    // when the game is running- or not.
    //volatile boolean playing;
    Paint gradinetPaintGreen;
    int count = 0;
    //private boolean shouldShowFPS = false;
    public Food food = new Food();
    //Paint gradinetPaintBlack;

    // This variable tracks the game frame rate
    //long fps;
   // frameDelay = 30;
    ArrayList<QunatisedPosition> snakePoints;
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
        int x1 = food.position.xOffset, y1 = food.position.yOffset, x2 = food.position.maximumXPoint,  y2 = food.position.maximumYPoint;
        Shader shader = new LinearGradient(x1, y1, x2, y2,Color.rgb(40, 180, 70), Color.rgb(120, 200, 100), TileMode.REPEAT);
        gradinetPaintGreen = new Paint();
        gradinetPaintGreen.setShader(shader);

        //Bitmap.createBitmap(square,0,0,20,20);
        //mDrawable.getPaint().setColor(0xff74AC23);

    }




    // Draw the newly updated scene
    public void draw(GameData data) {

        if (ourHolder.getSurface().isValid()) {


            canvas = ourHolder.lockCanvas();



            canvas.drawColor(Color.argb(10,70, 70, 70));

            // Choose the brush color for drawing
            paint.setColor(Color.rgb(136, 196, 96));
            //paint.setShadowLayer(12, 0, 0, Color.YELLOW);

            // Important for certain APIs
            //setLayerType(LAYER_TYPE_SOFTWARE, paint);



            // Make the text a bit bigger
            paint.setTextSize(45);

            // Display the current fps on the screen
//            if (shouldShowFPS) {
//                canvas.drawText("FPS:" + fps, 20, 40, paint);
//
//            }


            canvas.drawRoundRect(food.position.xOffset, QunatisedPosition.scoreBoardStartY, food.position.maximumXPoint, QunatisedPosition.scoreBoardEndY,food.position.itemSize/2,food.position.itemSize/2, gradinetPaintGreen);
            //canvas.drawRect(0, QunatisedPosition.consoleStartY, QunatisedPosition.maximumXPoint + QunatisedPosition.xOffset, QunatisedPosition.consoleEndY,paint);

            canvas.drawRoundRect(food.position.xOffset, QunatisedPosition.consoleStartY, food.position.maximumXPoint, QunatisedPosition.consoleEndY,food.position.itemSize/2,food.position.itemSize/2, gradinetPaintGreen);

            float textHeight = QunatisedPosition.scoreBoardEndY - QunatisedPosition.scoreBoardStartY;

            canvas.drawRoundRect(food.position.xOffset, food.position.yOffset, food.position.maximumXPoint, food.position.maximumYPoint,food.position.itemSize/2,food.position.itemSize/2, gradinetPaintGreen);


            paint.setColor(Color.rgb(70, 70, 70));
            paint.setTextSize((textHeight)/3);

            paint.setStrokeWidth(10);

            int itemSize =  food.position.itemSize;
            if (!isGameOver) {
                int fullSnakeSize = snakePoints.size();
                for (int i = 0; i < snakePoints.size(); i++) {
                    Point pixelPosition = snakePoints.get(i).convertToPoint();
                /* int itemSize = snakePoints.get(i).itemSize; */
                int roundXValue = itemSize/2;
                    int roundYValue = roundXValue;
                    int sizeReduction = 1;
                    if (i == fullSnakeSize-1){
                        roundXValue = itemSize/3;
                        roundYValue = itemSize/3;
                        sizeReduction = 0;
                        this.drawHead(pixelPosition.x,  pixelPosition.y , pixelPosition.x + itemSize, pixelPosition.y + itemSize,paint);
                        //paint.setColor(Color.rgb(0, 0, 0));

                    }else {
                        canvas.drawRoundRect(pixelPosition.x + sizeReduction, pixelPosition.y + sizeReduction, pixelPosition.x + itemSize - sizeReduction, pixelPosition.y + itemSize - sizeReduction, roundXValue, roundYValue, paint);
                    }
                }
                paint.setColor(Color.rgb(70, 70, 70));
                canvas.drawText("Score = "+ valueOf(data.score), 2*food.position.xOffset,  QunatisedPosition.scoreBoardEndY - textHeight/4 , paint);
            }else{
                canvas.drawText("Game Over: Double Tap to restart"  , 2*food.position.xOffset, QunatisedPosition.scoreBoardEndY - textHeight/4, paint);
                data.resetGameData();
            }
            canvas.drawText("Steps Since last Food = "+ valueOf(data.stepsTakenLastFood), 2*QunatisedPosition.xOffset,  QunatisedPosition.consoleEndY - textHeight/4 , paint);

            paint.setColor(Color.rgb(230,60, 60));
            Point foodPoint = food.position.convertToPoint();
            this.drawFood(foodPoint.x, foodPoint.y,foodPoint.x + itemSize, foodPoint.y + itemSize, paint);


            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);
        }

    }



    public void drawHead(int x, int y, int x2, int y2, Paint paint){
        canvas.drawRoundRect(x,y,x2,y2, (x2-x)/3,(x2-x)/3,paint);
        paint.setColor(Color.rgb(136, 196, 96));
        int midX =  (x+x2)/2;
        int midY =  (y + y2)/2;
        canvas.drawCircle(midX,midY,(x2-x)/8,paint);

    }

    public void drawFood(int x, int y, int x2, int y2, Paint paint){

        int midX =  (x+x2)/2;
        int midY =  (y + y2)/2;
        paint.setColor(Color.rgb(136, 196, 96));
        canvas.drawRect(x,y,x2,y2,paint);
        paint.setColor(Color.rgb(0,0,0));
        //canvas.drawRect(midX ,y,x2,midY,paint);
        canvas.drawArc(midX,y,x2,midY,225,180,true,paint);
        canvas.drawArc(x,midY,midX,y2,45,180,true,paint);
        canvas.drawArc(x,y,midX,midY,135,180,true,paint);
        canvas.drawArc(midX,midY,x2,y2,315,180,true,paint);
        canvas.drawCircle(midX,midY,(x2-x)/6,paint);
    }



    // The SurfaceView class implements onTouchListener
    // So we can override this method and detect screen touches.
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        if (isGameOver){
            gestureDetector.onTouchEvent(motionEvent);
            return true;
        }
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
                int threshold = food.position.itemSize;

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




     void foodEaten(){
        snakePoints.add(0,snakePoints.get(0));
        food.updateFood();


    }


    /**
     * Created by abhiraj.kumar on 6/10/17.
     */


}
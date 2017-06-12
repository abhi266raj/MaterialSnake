package com.example.abhirajkumar.snake;



import android.content.Context;
import java.util.ArrayList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.GestureDetector;
import android.graphics.Shader;
import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;


class  GameView extends SurfaceView implements Runnable {


    GestureDetector gestureDetector;
    boolean isGameOver = false;




    private boolean shouldShowFPS = false;
    private Food food = new Food();
    private ArrayList<QunatisedPosition> snakePoints;
    MotionDirection direction = new MotionDirection(DirectionSign.ZERO,DirectionSign.POSITIVE);
    Point touchPointStart;
    // This is our thread
    Thread gameThread = null;


    //Point foodPoint;

    SurfaceHolder ourHolder;

    // A boolean which we will set and unset
    // when the game is running- or not.
    volatile boolean playing;

    // A Canvas and a Paint object
    Canvas canvas;
    Paint paint;
    Paint gradinetPaintGreen;
    //Paint gradinetPaintBlack;

    // This variable tracks the game frame rate
    long fps;
   // frameDelay = 30;

    int count = 0;
    // This is used to help calculate the fps
    private long timeThisFrame;

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
        gestureDetector = new GestureDetector(context, new GestureListener(this));
        // Initialize ourHolder and paint objects
        ourHolder = getHolder();
        paint = new Paint();


        // Load Bob from his .png file


        // Set our boolean to true - game on!
        playing = true;
        makeSnake();
        int x1 = food.position.xOffset, y1 = food.position.yOffset, x2 = food.position.maximumXPoint,  y2 = food.position.maximumYPoint;
        Shader shader = new LinearGradient(x1, y1, x2, y2,Color.rgb(40, 180, 70), Color.rgb(120, 200, 100), TileMode.REPEAT);
        gradinetPaintGreen = new Paint();
        gradinetPaintGreen.setShader(shader);

        //Bitmap.createBitmap(square,0,0,20,20);
        //mDrawable.getPaint().setColor(0xff74AC23);

    }


    public void makeSnake(){

        int snakeSizeInitial = 15;
        snakePoints = new ArrayList<QunatisedPosition>(snakeSizeInitial);
        for (int i= 0;i<snakeSizeInitial;i++) {
            snakePoints.add(new QunatisedPosition(i,0));
        }


    }



    @Override
    public void run() {
        while (playing) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            update();
            draw();


            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            long desiredFrameRate = 10;
            long extraTime;
            extraTime = (1000/desiredFrameRate) - timeThisFrame;
            if (extraTime >0) {
                try {
                    gameThread.sleep((long) extraTime);
                } catch (InterruptedException e) {

                }
            }
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }

        }

    }

    // Everything that needs to be updated goes in here
    // In later projects we will have dozens (arrays) of objects.
    // We will also do other things like collision detection.
    public void update() {

        // If bob is moving (the player is touching the screen)
        // then move him to the right based on his target speed and the current fps.
       // if(isMoving){
        if (isGameOver){
            return;
        }
        QunatisedPosition firstPoint = snakePoints.get(0);
        QunatisedPosition lastPoint = snakePoints.get(snakePoints.size()-1);
        int xPositionToChange = lastPoint.x;
        int yPositionToChange = lastPoint.y;





    QunatisedPosition newPoint = new QunatisedPosition(xPositionToChange + direction.xDirectionSign.value() ,yPositionToChange + direction.yDirectionSign.value());
        willMoveToQuantisedPoint(newPoint);
    snakePoints.remove(0);
        snakePoints.add(newPoint);

    }

    // Draw the newly updated scene
    public void draw() {

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
            if (shouldShowFPS) {
                canvas.drawText("FPS:" + fps, 20, 40, paint);

            }



            canvas.drawRoundRect(food.position.xOffset, food.position.yOffset, food.position.maximumXPoint, food.position.maximumYPoint,food.position.itemSize/2,food.position.itemSize/2, gradinetPaintGreen);


            paint.setColor(Color.rgb(70, 70, 70));
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
            }else{
                canvas.drawText("Game Over"  , 50, 120, paint);
                canvas.drawText("Double Tap to restart"  , 50, 220, paint);
            }
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

    // If SimpleGameEngine Activity is paused/stopped
    // shutdown our thread.
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    // If SimpleGameEngine Activity is started then
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
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


    private void willMoveToQuantisedPoint(QunatisedPosition point){
        if (point.x == food.position.x && point.y == food.position.y){
            foodEaten();
        }


        for (QunatisedPosition position: snakePoints){
            if (position.x == point.x && position.y == point.y){
                //removeSnake();
                isGameOver = true;
                break;
            }

        }
    }

    private void foodEaten(){
        snakePoints.add(0,snakePoints.get(0));
        food.updateFood();


    }


    /**
     * Created by abhiraj.kumar on 6/10/17.
     */


}
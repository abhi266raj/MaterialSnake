package com.example.abhirajkumar.snake;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import	android.widget.ImageButton;
import com.example.abhirajkumar.snake.Theme.Theme;
import android.graphics.drawable.GradientDrawable;

public class PlayActivity extends AppCompatActivity {

    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getWindow().getDecorView().setBackgroundColor( Theme.currentTheme.primaryColor ());

        ImageButton button = (ImageButton) findViewById(R.id.buttonPlay);
        GradientDrawable shape =  new GradientDrawable();
        shape.setCornerRadius( 16 );

        // add some color
        // You can add your random color generator here
        // and set color

            shape.setColor(Theme.currentTheme.secondaryColor ());
        if(android.os.Build.VERSION.SDK_INT>=16) {
            button.setBackground ( shape );
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameStatus.isGameOver = false;
                finish();
            }
        });
    }

}

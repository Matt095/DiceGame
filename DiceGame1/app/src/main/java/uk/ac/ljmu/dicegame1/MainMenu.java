package uk.ac.ljmu.dicegame1;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;

public class MainMenu extends AppCompatActivity {

    Button button1;
    Button button2;
    ImageButton imageButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu2);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);

		//Link to main game page
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this,MainGame.class);
                startActivity(myIntent);
            }
        });

		//Link to leaderboard
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this,Leaderboard.class);
                startActivity(myIntent);
            }
        });

		//Simple interactive image, plays animation everytime it is clicked.
        imageButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imageButton1.setClickable(false);
                Random random = new Random();
                int dice = random.nextInt(6)+ 1;

                switch (dice)
                {
                    case 1:
                    {
                        playAnimationDice();
                        imageButton1.setImageResource(R.drawable.dice1);
                        break;
                    }
                    case 2:
                    {
                        playAnimationDice();
                        imageButton1.setImageResource(R.drawable.dice2);
                        break;
                    }
                    case 3:
                    {
                        playAnimationDice();
                        imageButton1.setImageResource(R.drawable.dice3);
                        break;
                    }
                    case 4:
                    {
                        playAnimationDice();
                        imageButton1.setImageResource(R.drawable.dice4);
                        break;
                    }
                    case 5:
                    {
                        playAnimationDice();
                        imageButton1.setImageResource(R.drawable.dice5);
                        break;
                    }
                    case 6:
                    {
                        playAnimationDice();
                        imageButton1.setImageResource(R.drawable.dice6);
                        break;
                    }
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButton1.setClickable(true);
                    }
                }, 1500);
            }
        });
    }

    public void playAnimationDice(){
        AnimatorSet anim1 = new AnimatorSet();
        anim1.play(ObjectAnimator.ofFloat(imageButton1, "rotationX", 0, 360))
                .with(ObjectAnimator.ofFloat(imageButton1, "rotationY", 0, 360))
                .with(ObjectAnimator.ofFloat(imageButton1, "alpha", 0, 1))
                .with(ObjectAnimator.ofFloat(imageButton1, "scaleX", 0, 1))
                .with(ObjectAnimator.ofFloat(imageButton1, "scaleY", 0, 1));
        anim1.setDuration(1000);
        anim1.start();
    }
}

package uk.ac.ljmu.dicegame1;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import java.util.Random;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class MainGame extends AppCompatActivity {

    private TextView editText1, editText2, editText3;
    private ImageView imageView1, imageView2;
    private static int player = 1;
    public static int player1Score;
    public static int player2Score; //= 0;
    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        editText1 = (TextView)findViewById(R.id.editText1);
        editText2 = (TextView)findViewById(R.id.editText2);
        editText3 = (TextView)findViewById(R.id.editText3);

        editText1.setText("Player " + player + "'s turn!");

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        // Scores are loaded from storage
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int score1 = preferences.getInt("player1Score", 0);
        int score2 = preferences.getInt("player2Score", 0);
        editText2.setText("Player 1 Score: " + score1);
        editText3.setText("Player 2 Score: " + score2);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                button1.setClickable(false);

                Random random = new Random();

                int dice1;
                int dice2;

                Toast toast = Toast.makeText(getApplicationContext(), "Player " + player + " rolls!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.RIGHT, 10, 120);
                toast.show();

                dice1 = random.nextInt(6) + 1;
                dice2 = random.nextInt(6) + 1;

                switch (dice1)
                {
                    case 1:
                    {
                        playAnimationDice1();
                        imageView1.setImageResource(R.drawable.dice1);
                        break;
                    }
                    case 2:
                    {
                        playAnimationDice1();
                        imageView1.setImageResource(R.drawable.dice2);
                        break;
                    }
                    case 3:
                    {
                        playAnimationDice1();
                        imageView1.setImageResource(R.drawable.dice3);
                        break;
                    }
                    case 4:
                    {
                        playAnimationDice1();
                        imageView1.setImageResource(R.drawable.dice4);
                        break;
                    }
                    case 5:
                    {
                        playAnimationDice1();
                        imageView1.setImageResource(R.drawable.dice5);
                        break;
                    }
                    case 6:
                    {
                        playAnimationDice1();
                        imageView1.setImageResource(R.drawable.dice6);
                        break;
                    }
                }

                switch (dice2)
                {
                    case 1:
                    {
                        playAnimationDice2();
                        imageView2.setImageResource(R.drawable.dice1);
                        break;
                    }
                    case 2:
                    {
                        playAnimationDice2();
                        imageView2.setImageResource(R.drawable.dice2);
                        break;
                    }
                    case 3:
                    {
                        playAnimationDice2();
                        imageView2.setImageResource(R.drawable.dice3);
                        break;
                    }
                    case 4:
                    {
                        playAnimationDice2();
                        imageView2.setImageResource(R.drawable.dice4);
                        break;
                    }
                    case 5:
                    {
                        playAnimationDice2();
                        imageView2.setImageResource(R.drawable.dice5);
                        break;
                    }
                    case 6:
                    {
                        playAnimationDice2();
                        imageView2.setImageResource(R.drawable.dice6);
                        break;
                    }
                }

                if ((dice1 == dice2) && (player == 1))
                {
                    toast = Toast.makeText(getApplicationContext(), "Player " + player + " Scores!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.RIGHT, 10, 240);
                    toast.show();
                    ++player1Score;
                    editText2.setText("Player 1 Score: " + player1Score);
                }
                else if ((dice1 == dice2) && (player == 2))
                {
                    toast = Toast.makeText(getApplicationContext(), "Player " + player + " Scores!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.RIGHT, 10, 240);
                    toast.show();
                    ++player2Score;
                    editText3.setText("Player 2 Score: " + player2Score);
                }
                if (player == 1)
                {
                    player = 2;
                    editText1.setText("Player " + player + "'s Turn!");
                }
                else
                {
                    player = 1;
                    editText1.setText("Player " + player + "'s Turn!");
                }

                //Roll button is unclickable for 3 seconds. Score is also saved.
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button1.setClickable(true);
                    }
                }, 3000);

                save();
            }
        });

		//View leaderboard screen
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainGame.this,Leaderboard.class);
                startActivity(myIntent);
            }
        });

		// Reset score functionality
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player1Score = 0;
                player2Score = 0;
                editText2.setText("Player 1 Score: " + player1Score);
                editText3.setText("Player 2 Score: " + player2Score);

                save();
            }
        });
    }

    public void playAnimationDice1(){
        AnimatorSet anim1 = new AnimatorSet();
        anim1.play(ObjectAnimator.ofFloat(imageView1, "rotationX", 0, 360))
                .with(ObjectAnimator.ofFloat(imageView1, "rotationY", 0, 360))
                .with(ObjectAnimator.ofFloat(imageView1, "alpha", 0, 1))
                .with(ObjectAnimator.ofFloat(imageView1, "scaleX", 0, 1))
                .with(ObjectAnimator.ofFloat(imageView1, "scaleY", 0, 1));
        anim1.setDuration(2000);
        anim1.start();
    }

    public void playAnimationDice2(){
        AnimatorSet anim2 = new AnimatorSet();
        anim2.play(ObjectAnimator.ofFloat(imageView2, "rotationX", 0, 360))
                .with(ObjectAnimator.ofFloat(imageView2, "rotationY", 0, 360))
                .with(ObjectAnimator.ofFloat(imageView2, "alpha", 0, 1))
                .with(ObjectAnimator.ofFloat(imageView2, "scaleX", 0, 1))
                .with(ObjectAnimator.ofFloat(imageView2, "scaleY", 0, 1));
        anim2.setDuration(2000);
        anim2.start();
    }

    public void save(){
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("player1Score", player1Score);
        editor.putInt("player2Score", player2Score);
        editor.commit();
    }
}

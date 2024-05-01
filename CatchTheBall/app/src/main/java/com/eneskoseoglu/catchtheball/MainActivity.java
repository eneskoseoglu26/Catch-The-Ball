package com.eneskoseoglu.catchtheball;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView time;
    TextView score;
    int point;
    Button button;
    ImageView ball;
    int sec = 20000;
    Runnable runnable;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.textView);
        score = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        ball = findViewById(R.id.imageView7);
        point = 0;
        ball.setEnabled(false);
        time.setText("Time : " + (sec/1000) + " seconds");

    }

    public void addScore(View view) {

        point++;
        score.setText("Score : "+point);

    }

    public void start(View view) {

        button.setVisibility(View.GONE);
        ball.setEnabled(true);

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                int randomX = new Random().nextInt(800) + 0;
                int randomY = new Random().nextInt(1100) + 200;
                handler.postDelayed(this, 500);
                ball.setX(randomX);
                ball.setY(randomY);

            }
        };

        handler.post(runnable);


        new CountDownTimer(sec, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {

                if((millisUntilFinished/1000) == 1 || (millisUntilFinished/1000) == 0) {

                    time.setText("Time : " + millisUntilFinished / 1000 + " second");

                } else {

                    time.setText("Time : " + millisUntilFinished / 1000 + " seconds");

                }

            }

            @Override
            public void onFinish() {

                handler.removeCallbacks(runnable);
                time.setText("Game Over!");
                Toast.makeText(MainActivity.this, "Check your score!", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Do you want to restart the game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       /* Intent intent = getIntent();
                        finish();
                        startActivity(intent); */
                        recreate();

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ball.setEnabled(false);

                    }
                });

                alert.show();

            }
        }.start();




    }

}
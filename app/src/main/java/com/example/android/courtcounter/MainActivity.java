package com.example.android.courtcounter;

import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int tryTeamA = 0;
    int conversionTeamA = 0;
    int goalKickTeamA = 0;
    int tryTeamB = 0;
    int conversionTeamB = 0;
    int goalKickTeamB = 0;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;



    /**
     * This saves the variables
     */

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("scoreTeamA", scoreTeamA);
        savedInstanceState.putInt("scoreTeamB", scoreTeamB);
        savedInstanceState.putInt("tryTeamA", tryTeamA);
        savedInstanceState.putInt("conversionTeamA", conversionTeamA);
        savedInstanceState.putInt("goalKickTeamA", goalKickTeamA);
        savedInstanceState.putInt("tryTeamB", tryTeamB);
        savedInstanceState.putInt("conversionTeamB", conversionTeamB);
        savedInstanceState.putInt("goalKickTeamB", goalKickTeamB);
        savedInstanceState.putLong("timeInMilliseconds", timeInMilliseconds);
        savedInstanceState.putLong("timeSwapBuff", timeSwapBuff);
        savedInstanceState.putLong("updatedTime", updatedTime);
        savedInstanceState.putBoolean("isTimeEnabled" , isTimeEnabled);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreTeamA = savedInstanceState.getInt("scoreTeamA");
        scoreTeamB = savedInstanceState.getInt("scoreTeamB");
        tryTeamA = savedInstanceState.getInt("tryTeamA");
        conversionTeamA = savedInstanceState.getInt("conversionTeamA");
        goalKickTeamA = savedInstanceState.getInt("goalKickTeamA");
        conversionTeamB = savedInstanceState.getInt("conversionTeamB");
        goalKickTeamB = savedInstanceState.getInt("goalKickTeamB");
        timeInMilliseconds = savedInstanceState.getLong("timeInMilliseconds");
        timeSwapBuff = savedInstanceState.getLong("timeSwapBuff");
        updatedTime = savedInstanceState.getLong("updatedTime");
        isTimeEnabled = savedInstanceState.getBoolean("isTimeEnabled");

        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayTryTeamA(tryTeamA);
        displayTryTeamB(tryTeamB);
        displayConversionTeamA(conversionTeamA);
        displayConversionTeamB(conversionTeamB);
        displaykickTeamA(goalKickTeamA);
        displaykickTeamB(goalKickTeamB);
        if (isTimeEnabled) {
            timeSwapBuff += timeInMilliseconds;
            startTime = SystemClock.uptimeMillis();
            customHandler.postDelayed(updateTimerThread, 0);
        }
        else {
        }

    }
    /**
     * Timer
     */
    boolean isTimeEnabled = false;
    private Button startButton;
    private Button pauseButton;
    private TextView timerValue;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            if (mins < 10) {
                secs = secs % 60;
                int milliseconds = (int) (updatedTime % 100);
                timerValue.setText("" + String.format("%02d", mins) + ":"
                        + String.format("%02d", secs) + ":"
                        + String.format("%02d", milliseconds));
                customHandler.postDelayed(this, 0);
            } else {
                secs = 0;
                int milliseconds = 0;
                timerValue.setText("" + String.format("%02d", mins) + ":"
                        + String.format("%02d", secs) + ":"
                        + String.format("%02d", milliseconds));
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        }

    };

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayTryTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.try_team_a);
        scoreView.setText(String.valueOf(score));
    }

    public void displayConversionTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.conversion_team_a);
        scoreView.setText(String.valueOf(score));
    }

    public void displaykickTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.kick_team_a);
        scoreView.setText(String.valueOf(score));
    }

    public void displayTryTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.try_team_b);
        scoreView.setText(String.valueOf(score));
    }

    public void displayConversionTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.conversion_team_b);
        scoreView.setText(String.valueOf(score));
    }

    public void displaykickTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.kick_team_b);
        scoreView.setText(String.valueOf(score));
    }

    public void tryTeamA(View view) {

        scoreTeamA = scoreTeamA + 5;
        displayForTeamA(scoreTeamA);
        tryTeamA = tryTeamA + 5;
        displayTryTeamA(tryTeamA);
    }

    public void conversionTeamA(View view) {

        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
        conversionTeamA = conversionTeamA + 2;
        displayConversionTeamA(conversionTeamA);

    }

    public void goalKickTeamA(View view) {

        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
        goalKickTeamA = goalKickTeamA + 3;
        displaykickTeamA(goalKickTeamA);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void tryTeamB(View view) {

        scoreTeamB = scoreTeamB + 5;
        displayForTeamB(scoreTeamB);
        tryTeamB = tryTeamB + 5;
        displayTryTeamB(tryTeamB);
    }

    public void conversionTeamB(View view) {

        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
        conversionTeamB = conversionTeamB + 2;
        displayConversionTeamB(conversionTeamB);
    }

    public void goalKickTeamB(View view) {

        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
        goalKickTeamB = goalKickTeamB + 3;
        displaykickTeamB(goalKickTeamB);
    }

    public void reset(View view) {

        scoreTeamA = 0;
        displayForTeamA(scoreTeamA);
        scoreTeamB = 0;
        displayForTeamB(scoreTeamB);
        tryTeamA = 0;
        displayTryTeamA(tryTeamA);
        conversionTeamA = 0;
        displayConversionTeamA(conversionTeamA);
        goalKickTeamA = 0;
        displaykickTeamA(goalKickTeamA);
        tryTeamB = 0;
        displayTryTeamB(tryTeamB);
        conversionTeamB = 0;
        displayConversionTeamB(conversionTeamB);
        goalKickTeamB = 0;
        displaykickTeamB(goalKickTeamB);
        customHandler.removeCallbacks(updateTimerThread);
        if (isTimeEnabled)
            isTimeEnabled = false;
        timerValue.setText("00:00:00");
        startTime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerValue = (TextView) findViewById(R.id.timerValue);

        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (!isTimeEnabled){
                    isTimeEnabled = true;
                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);}
            }
        });

        pauseButton = (Button) findViewById(R.id.pauseButton);

        pauseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (isTimeEnabled)
                    isTimeEnabled = false;
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);

            }
        });
    }


}


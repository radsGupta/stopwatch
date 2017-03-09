package com.example.radsgupta.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {
    //number of seconds displayed on the stopwatch
    private int seconds = 0;
    //Is the stopwatch running?
    private boolean running;
    //to check whether the stopwatch was running before the activity was paused.
    private boolean wasRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        //Get the previous state of the stopwatch if the activity has been destroyed and recreated
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        wasRunning=savedInstanceState.getBoolean("wasRunning");}
        runTimer();
    }

@Override
protected void onPause(){
    super.onPause();
    wasRunning=running;
    running=false;
} @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    public void onClickStart(View view) {
        running = true;
        //starts the stopwatch activity
    }

    public void onClickStop(View view) { //stops the stopwatch runnning
        running = false;
    }

    public void onClickReset(View view) {
        //reset the stopwatch button when the reset button is clicked
        running = false;
        seconds = 0;
    }
//Sets the number of seconds on the timer.
    private void runTimer() {
        final TextView timeView=(TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }

        });
    }
}




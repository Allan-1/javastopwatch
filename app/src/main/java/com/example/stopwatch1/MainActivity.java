package com.example.stopwatch1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private boolean start;
    private boolean wasrunning;
    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            start = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }
        runTimer();
    }

    @Override
    public void onStart(){
        super.onStart();
        if(wasrunning){
            start = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", start);
        savedInstanceState.putBoolean("wasrunning", wasrunning);
    }

    @Override
    public void onStop(){
        super.onStop();
        wasrunning = start;
        start = false;
    }

    public void onClickStart(View view){
        start = true;
    }
    public void onClickStop(View view){
        start = false;
    }
    public void onClickReset(View view){
        start = false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView timeview = (TextView) findViewById(R.id.stopcounter);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeview.setText(time);

                if(start){
                    seconds ++;
                }
                handler.postDelayed(this, 1000);
            }
        });



    }


}
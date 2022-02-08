package com.mohit.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    static int width, height, bombCount = 20;
    static boolean isDTSC = true; //is digging type long press

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup diff = findViewById(R.id.difficulty_level);
        RadioGroup fT = findViewById(R.id.dig_type);

        diff.setOnCheckedChangeListener((radioGroup, i) -> {
            int id = diff.getCheckedRadioButtonId();

            if(id == R.id.radioButton){
                bombCount = 20;
            }
            else if(id == R.id.radioButton2){
                bombCount = 30;
            }
            else {
                bombCount = -1;
            }
        });

        fT.setOnCheckedChangeListener((radioGroup, i) -> {
            int id = fT.getCheckedRadioButtonId();

            if(id == R.id.radioButton4){
                isDTSC = true;
            }
            else{
                isDTSC = false;
            }
        });

        if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            height = metrics.heightPixels;
            width = metrics.widthPixels;
        }
        else {

            WindowMetrics metrics = getWindowManager().getMaximumWindowMetrics();

            height = metrics.getBounds().height();
            width = metrics.getBounds().width();
        }

        Button button = findViewById(R.id.play_button);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameWindow.class);
            startActivity(intent);
        });
    }
}
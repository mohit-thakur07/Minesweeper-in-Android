package com.mohit.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameWindow extends AppCompatActivity {

    MineButton[] mineButton;
    int h;
    ImageView gameState;
    TextView availableFlags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_window);

        int bomb = 0;

        gameState = findViewById(R.id.game_state);
        availableFlags = findViewById(R.id.available_flags);

        LinearLayout verLayout = findViewById(R.id.game_area_layout);
        
        h = (int) (MainActivity.height * 0.7) / (MainActivity.width / 10);
        
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(MainActivity.width / 10, MainActivity.width / 10);

        LinearLayout[] horLayout = new LinearLayout[h];
        mineButton = new MineButton[h * 10];

        System.out.println(horLayout.length);
        System.out.println(MainActivity.width);
        System.out.println(MainActivity.height);

        for(int i = 0; i < horLayout.length; i++){

            horLayout[i] = new LinearLayout(getApplicationContext());
            horLayout[i].setOrientation(LinearLayout.HORIZONTAL);

            for(int j = 0; j < 10; j++){
                mineButton[(10 * i) + j] = new MineButton(getApplicationContext());
                mineButton[(10 * i) + j].setButtonId((10 * i) + j);
                int finalI = i;
                int finalJ = j;

                mineButton[(10 * i) + j].setOnClickListener(view -> {

                    if(mineButton[(10 * finalI) + finalJ].getRealFlag() == 1){
                        return;
                    }

                    mineButton[(10 * finalI) + finalJ].setRealFlag(-1);
                    mineButton[(10 * finalI) + finalJ].setEnabled(false);

                    int val = mineButton[(10 * finalI) + finalJ].getVal();

                    if (mineButton[(10 * finalI) + finalJ].getSet() == 1) {
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.blast);
                        boom();
                        return;
                    }

                    if (val == 0) {

                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.pressed);

                        space(mineButton[(10 * finalI) + finalJ].getButtonId(), finalI, finalJ);
                    } else if (val == 1) {
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.pressed1);
                    } else if (val == 2) {
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.pressed2);
                    } else if (val == 3) {
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.pressed3);
                    } else if (val == 4) {
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.pressed4);
                    } else if (val == 5) {
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.pressed5);
                    } else if (val == 6) {
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.pressed6);
                    } else if (val == 7) {
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.pressed7);
                    } else if (val == 8) {
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.pressed8);
                    }

                    isGameFinished();
                });

                mineButton[(10 * i) + j].setOnLongClickListener(view -> {

                    if(mineButton[(10 * finalI) + finalJ].getRealFlag() == 0){

                        if(Integer.parseInt(availableFlags.getText().toString()) > 0){
                            availableFlags.setText(Integer.toString(Integer.parseInt(availableFlags.getText().toString()) - 1));
                        }
                        mineButton[(10 * finalI) + finalJ].setRealFlag(1);
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.flagged);
                    }
                    else if(mineButton[(10 * finalI) + finalJ].getRealFlag() == 1){
                        availableFlags.setText(Integer.toString(Integer.parseInt(availableFlags.getText().toString()) + 1));
                        mineButton[(10 * finalI) + finalJ].setRealFlag(0);
                        mineButton[(10 * finalI) + finalJ].setImageResource(R.drawable.normal);
                    }

                    return true;
                });

                horLayout[i].addView(mineButton[(10 * i) + j], lp);
            }

            verLayout.addView(horLayout[i]);
        }

        Random rnd = new Random();

        if(MainActivity.bombCount == 20) {

            mineButton[0].setSet(0);

            while (bomb != 10) {
                bomb = 0;
                for (int i = 1; i < (h * 10) / 2; i++) {
                    mineButton[i].setSet(rnd.nextInt(2) * rnd.nextInt(2) * rnd.nextInt(2));
                    bomb = bomb + mineButton[i].getSet();
                }
                //System.out.println(bomb);
            }
            while (bomb != 20) {
                bomb = 10;
                for (int i = (h * 10) / 2; i < (h * 10); i++) {
                    mineButton[i].setSet(rnd.nextInt(2) * rnd.nextInt(2) * rnd.nextInt(2));
                    bomb = bomb + mineButton[i].getSet();
                }
                //System.out.println(bomb);
            }
        }
        else if(MainActivity.bombCount == 30){

            mineButton[0].setSet(0);

            while (bomb != 10) {
                bomb = 0;
                for (int i = 1; i < (h * 10) / 3; i++) {
                    mineButton[i].setSet(rnd.nextInt(2) * rnd.nextInt(2) * rnd.nextInt(2));
                    bomb = bomb + mineButton[i].getSet();
                }
                //System.out.println(bomb);
            }
            while (bomb != 20) {
                bomb = 10;
                for (int i = (h * 10) / 3; i < 2 * (h * 10) / 3; i++) {
                    mineButton[i].setSet(rnd.nextInt(2) * rnd.nextInt(2) * rnd.nextInt(2));
                    bomb = bomb + mineButton[i].getSet();
                }
                //System.out.println(bomb);
            }
            while (bomb != 30) {
                bomb = 20;
                for (int i = 2 * (h * 10) / 3; i < h * 10; i++) {
                    mineButton[i].setSet(rnd.nextInt(2) * rnd.nextInt(2) * rnd.nextInt(2));
                    bomb = bomb + mineButton[i].getSet();
                }
                //System.out.println(bomb);
            }
        }
        else{
            //Hardest minesweeper possible
            //baith k tukke lagao
            for(int i = 0; i < h * 10; i++){
                mineButton[i].setSet(1);
            }
            
            int pos = (rnd.nextInt(h * 10) * rnd.nextInt(h * 10) * rnd.nextInt(h * 10)) % (h * 10);
            
            System.out.println(pos);
            
            mineButton[pos].setSet(0);
            bomb = h * 10 - 1;
        }

        availableFlags.setText(Integer.toString(bomb));

        mineButton[0].setVal(mineButton[1].getSet() + mineButton[10].getSet() + mineButton[11].getSet());
        mineButton[9].setVal(mineButton[8].getSet() + mineButton[18].getSet() + mineButton[19].getSet());
        mineButton[(h - 1) * 10].setVal(mineButton[(h - 2) * 10].getSet() + mineButton[(h - 2) * 10 + 1].getSet() + mineButton[(h - 1) * 10 + 1].getSet());
        mineButton[h * 10 - 1].setVal(mineButton[h * 10 - 11].getSet() + mineButton[h * 10 - 12].getSet() + mineButton[h * 10 - 2].getSet());

        for(int i = 1, j = (h - 1) * 10 + 1; i < 9 && j < h * 10 - 1; i++, j++) {

            mineButton[i].setVal(mineButton[i - 1].getSet() + mineButton[i + 1].getSet() +
                    mineButton[i + 10].getSet() +
                    mineButton[i + 11].getSet() +
                    mineButton[i + 9].getSet());

            mineButton[j].setVal(mineButton[j - 1].getSet() + mineButton[j + 1].getSet() +
                    mineButton[j - 10].getSet() +
                    mineButton[j - 11].getSet() +
                    mineButton[j - 9].getSet());
        }

        for(int i = 10, j = 19; i < (h - 1) * 10 && j < (h - 1) * 10; i += 10, j += 10) {

            mineButton[i].setVal(mineButton[i + 1].getSet() +
                    mineButton[i - 10].getSet() + mineButton[i + 10].getSet() +
                    mineButton[i + 11].getSet() +
                    mineButton[i - 9].getSet());

            mineButton[j].setVal(mineButton[j - 1].getSet() +
                    mineButton[j - 10].getSet() + mineButton[j + 10].getSet() +
                    mineButton[j - 11].getSet() +
                    mineButton[j + 9].getSet());
        }

        for(int i = 1; i < h - 1; i++){
            for(int j = 1; j < 9; j++) {

                mineButton[i * 10 + j].setVal(mineButton[i * 10 + j - 1].getSet() + mineButton[i * 10 + j + 1].getSet() +
                        mineButton[i * 10 + j - 10].getSet() + mineButton[i * 10 + j + 10].getSet() +
                        mineButton[i * 10 + j - 11].getSet() + mineButton[i * 10 + j + 11].getSet() +
                        mineButton[i * 10 + j - 9].getSet() + mineButton[i * 10 + j + 9].getSet());
            }
        }
    }

    private void space(int id, int i, int j){
        try{

            if(mineButton[id - 1].isEnabled() && j > 0){
                mineButton[id - 1].performClick();
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

        try{

            if(mineButton[id + 1].isEnabled() && j < 9){
                mineButton[id + 1].performClick();
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

        try{

            if(mineButton[id - 11].isEnabled() && i > 0 && j > 0){
                mineButton[id - 11].performClick();
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

        try{

            if(mineButton[id + 11].isEnabled() && i < h - 1 && j < 9){
                mineButton[id + 11].performClick();
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

        try{

            if(mineButton[id - 9].isEnabled() && i > 0 && j < 9){
                mineButton[id - 9].performClick();
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

        try{

            if(mineButton[id + 9].isEnabled() && i < h - 1 && j > 0){
                mineButton[id + 9].performClick();
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

        try{

            if(mineButton[id - 10].isEnabled() && i > 0){
                mineButton[id - 10].performClick();
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

        try{

            if(mineButton[id + 10].isEnabled() && i < h - 1){
                mineButton[id + 10].performClick();
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }
    }

    private void boom(){

        gameState.setImageResource(R.drawable.sad);
        System.out.println("Bad luck!!!");

        Toast.makeText(GameWindow.this,
                "Bad luck!!!", Toast.LENGTH_SHORT).show();

        for(int i = 0; i < h * 10; i++){
            if(mineButton[i].isEnabled()){

                int val = mineButton[i].getVal();

                if(mineButton[i].getSet() == 1 && mineButton[i].getRealFlag() == 0){
                    mineButton[i].setImageResource(R.drawable.mine);
                    continue;
                }
                else if(mineButton[i].getSet() == 0 && mineButton[i].getRealFlag() == 1){
                    mineButton[i].setImageResource(R.drawable.crossed_flag);
                    continue;
                }
                else if(mineButton[i].getSet() == 1 && mineButton[i].getRealFlag() == 1){
                    continue;
                }

                if (val == 0) {
                    mineButton[i].setImageResource(R.drawable.pressed);
                } else if (val == 1) {
                    mineButton[i].setImageResource(R.drawable.pressed1);
                } else if (val == 2) {
                    mineButton[i].setImageResource(R.drawable.pressed2);
                } else if (val == 3) {
                    mineButton[i].setImageResource(R.drawable.pressed3);
                } else if (val == 4) {
                    mineButton[i].setImageResource(R.drawable.pressed4);
                } else if (val == 5) {
                    mineButton[i].setImageResource(R.drawable.pressed5);
                } else if (val == 6) {
                    mineButton[i].setImageResource(R.drawable.pressed6);
                } else if (val == 7) {
                    mineButton[i].setImageResource(R.drawable.pressed7);
                } else if (val == 8) {
                    mineButton[i].setImageResource(R.drawable.pressed8);
                }

                mineButton[i].setRealFlag(-1);
                mineButton[i].setEnabled(false);
            }
        }
    }

    private void isGameFinished(){

        boolean flag = true;

        for(int i = 0; i < h * 10; i++){
            if(mineButton[i].isEnabled() && mineButton[i].getSet() == 0){
                flag = false;
                break;
            }
        }

        if(flag){
            for(int i = 0; i < h * 10; i++){
                if(mineButton[i].getSet() == 1){
                    mineButton[i].setImageResource(R.drawable.flagged);
                    mineButton[i].setRealFlag(-1);
                }
            }

            System.out.println("Well done...");

            Toast.makeText(GameWindow.this,
                    "Well done...", Toast.LENGTH_SHORT).show();
            availableFlags.setText("0");
        }
    }
}
package com.mohit.minesweeper;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;

public class MineButton extends AppCompatImageButton {

    private int val, set, realFlag, id;
    long prevClick;

    public MineButton(@NonNull Context context) {
        super(context);

        setImageResource(R.drawable.normal);

        val = set = realFlag = 0;
        prevClick = 0;
        id = -1;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getRealFlag() {
        return realFlag;
    }

    public void setRealFlag(int realFlag) {
        this.realFlag = realFlag;
    }

    public int getButtonId() {
        return id;
    }

    public void setButtonId(int id) {
        this.id = id;
    }
}

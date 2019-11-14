package com.example.running;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MainCanvas extends View {
    boolean isDone = false;
    DrawbleObject jumpButton;
    MyObject rabbit;

    Bitmap JumpButtonBm;

    public MainCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        jumpButton = new DrawbleObject(1500, 700, 200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return true;
    }
}

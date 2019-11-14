package com.example.running;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MainCanvas extends View {
    boolean isDone = false;
    DrawbleObject jumpButton;
    MyObject rabbit;
    Map map;

    Rect jumpButtonRect;

    Bitmap rabbitBm, rabbitJumpBm, rabbitRunBm;

    public MainCanvas(Context context) {
        super(context);
    }

    public MainCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        jumpButton = new DrawbleObject(50, Map.GROUND - 150, 150, 150);
        jumpButtonRect = new Rect(jumpButton.posX, jumpButton.posY, jumpButton.posX + jumpButton.width, jumpButton.posY + jumpButton.height);

        rabbit = new MyObject(350, Map.GROUND - 150, 170, 150);

        rabbitBm = BitmapFactory.decodeResource(getResources(), R.drawable.rabbit);
        rabbitBm = Bitmap.createScaledBitmap(rabbitBm, rabbit.width, rabbit.height, true);
        rabbitJumpBm = BitmapFactory.decodeResource(getResources(), R.drawable.rabbitjump);
        rabbitJumpBm = Bitmap.createScaledBitmap(rabbitJumpBm, rabbit.width, rabbit.height, true);
        rabbitRunBm = BitmapFactory.decodeResource(getResources(), R.drawable.rabbitrun);
        rabbitRunBm = Bitmap.createScaledBitmap(rabbitRunBm, rabbit.width, rabbit.height, true);

        map = new Map(0, 0, 2000, 1000);

        map.makeHurdle(400);

        MyThread th = new MyThread();
        th.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();

        p.setColor(Color.WHITE);
        canvas.drawRect(map.posX, map.posY, map.posX + map.width, map.posY + map.height, p);
        p.setColor(Color.BLUE);

        for (int i = 0; i < map.hurdles.size(); i++){
            canvas.drawRect(map.hurdles.get(i).posX, map.hurdles.get(i).posY, map.hurdles.get(i).posX + map.hurdles.get(i).width, map.hurdles.get(i).posY + map.hurdles.get(i).height, p);
        }

        switch (rabbit.state){
            case 0:
                canvas.drawBitmap(rabbitBm, rabbit.posX, rabbit.posY, p);
                break;
            case 1:
                canvas.drawBitmap(rabbitRunBm, rabbit.posX, rabbit.posY, p);
                break;
            case 2:
                canvas.drawBitmap(rabbitJumpBm, rabbit.posX, rabbit.posY, p);
                break;
        }

        canvas.drawRect(jumpButtonRect, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (jumpButtonRect.contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                rabbit.jumping();
            }
        }

        return true;
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (!isDone) {
                rabbit.moving();
                map.moving();

                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }

        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };
}

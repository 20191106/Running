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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MainCanvas extends View {
    boolean isDone = false;
    MyObject rabbit;
    Map map;
    DrawbleObject jumpButton;
    DrawbleObject[] lifeSpace = new DrawbleObject[5];

    Rect jumpButtonRect;

    Bitmap rabbitBm, rabbitJumpBm, rabbitRunBm;
    Bitmap lifeOnBm, lifeOffBm, goldBm;
    Bitmap mapBm;

    TextView scoreTv;

    public MainCanvas(Context context) {
        super(context);
    }

    public MainCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // object
        rabbit = new MyObject(350, Map.GROUND - 150, 170, 150);

        rabbitBm = BitmapFactory.decodeResource(getResources(), R.drawable.rabbit);
        rabbitBm = Bitmap.createScaledBitmap(rabbitBm, (int)rabbit.width, (int)rabbit.height, true);
        rabbitJumpBm = BitmapFactory.decodeResource(getResources(), R.drawable.rabbitjump);
        rabbitJumpBm = Bitmap.createScaledBitmap(rabbitJumpBm, (int)rabbit.width, (int)rabbit.height, true);
        rabbitRunBm = BitmapFactory.decodeResource(getResources(), R.drawable.rabbitrun);
        rabbitRunBm = Bitmap.createScaledBitmap(rabbitRunBm, (int)rabbit.width, (int)rabbit.height, true);

        map = new Map(-960, 0, 5760, 1080);
        mapBm = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        mapBm = Bitmap.createScaledBitmap(mapBm, (int)map.width, (int)map.height, true);

        goldBm = BitmapFactory.decodeResource(getResources(), R.drawable.gold);

        // ui
        jumpButton = new DrawbleObject(50, Map.GROUND - 150, 150, 150);
        jumpButtonRect = new Rect((int)jumpButton.posX, (int)jumpButton.posY,
                (int)jumpButton.posX + (int)jumpButton.width, (int)jumpButton.posY + (int)jumpButton.height);

        for (int i = 0; i < 5; i ++){
            lifeSpace[i] = new DrawbleObject(0 + (i*150),0,150,150);
        }
        lifeOnBm = BitmapFactory.decodeResource(getResources(), R.drawable.lifeon);
        lifeOnBm = Bitmap.createScaledBitmap(lifeOnBm, (int)lifeSpace[0].width, (int)lifeSpace[0].height, true);
        lifeOffBm = BitmapFactory.decodeResource(getResources(), R.drawable.lifeoff);
        lifeOffBm = Bitmap.createScaledBitmap(lifeOffBm, (int)lifeSpace[0].width, (int)lifeSpace[0].height, true);

        scoreTv = findViewById(R.id.main_scoreTv);


        //init
        MyThread th = new MyThread();
        th.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.GREEN);

        // object
        canvas.drawBitmap(mapBm, (int)map.posX, (int)map.posY, p);

        for (int i = 0; i < map.hurdles.size(); i++){
            canvas.drawRect((int)map.hurdles.get(i).posX, (int)map.hurdles.get(i).posY,
                    (int)map.hurdles.get(i).posX + (int)map.hurdles.get(i).width, (int)map.hurdles.get(i).posY + (int)map.hurdles.get(i).height, p);
        }

        for (int i = 0; i < map.items.size(); i++){
            switch (map.items.get(i).type){
                case 1:
                    canvas.drawBitmap(lifeOnBm, (int)map.items.get(i).posX, (int)map.items.get(i).posY, p);
                    break;
                case 2:
                    canvas.drawBitmap(goldBm, (int)map.items.get(i).posX, (int)map.items.get(i).posY, p);
                    break;
            }
        }

        switch (rabbit.state){
            case 0:
                canvas.drawBitmap(rabbitBm, (int)rabbit.posX, (int)rabbit.posY, p);
                break;
            case 1:
                canvas.drawBitmap(rabbitRunBm, (int)rabbit.posX, (int)rabbit.posY, p);
                break;
            case 2:
                canvas.drawBitmap(rabbitJumpBm, (int)rabbit.posX, (int)rabbit.posY, p);
                break;
        }


        // ui
        p.setColor(Color.GRAY);
        canvas.drawRect(jumpButtonRect, p);

        for (int i = 0; i < rabbit.life; i++){
            canvas.drawBitmap(lifeOnBm, (int)lifeSpace[i].posX, (int)lifeSpace[i].posY, p);
        }
        for (int i = rabbit.life; i < 5; i++){
            canvas.drawBitmap(lifeOffBm, (int)lifeSpace[i].posX, (int)lifeSpace[i].posY, p);
        }
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
            int time = 0;
            while (!isDone) {
                map.timeLine(time);
                time++;

                rabbit.moving();
                map.moving();
                map.checkCrash(rabbit);
                isDone = map.checkEnd(rabbit);
                map.checkGetItem(rabbit);
                //scoreTv.setText("SCORE : " + rabbit.gold);
                //TODO
                //1.settext 2.가로고정

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

package com.example.running;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MainCanvas can;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        can.findViewById(R.id.mainCanvas);
    }

    @Override
    protected void onDestroy() {
        can.isDone = true;
        super.onDestroy();
    }
}

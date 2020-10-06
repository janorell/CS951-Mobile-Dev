package com.example.lifecyclelogger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = findViewById(R.id.test;
        Log.i("bruh", "onCreate Called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("bruh", "onStart Called");    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("bruh", "onResume Called");    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("bruh", "onPause Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("bruh", "onStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("bruh", "onDestroy Called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("bruh", "onRestart Called");
    }
}
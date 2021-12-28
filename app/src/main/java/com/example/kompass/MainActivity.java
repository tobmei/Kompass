package com.example.kompass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MainActivityListener mainActivityListener;
    ImageView ivNeedle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivNeedle = findViewById(R.id.ivNeedle);

        mainActivityListener = new MainActivityListener(this);
    }
}
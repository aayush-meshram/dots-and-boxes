package com.myapp.secondtrial;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView mText;
    private EditText mInt;
    private Button mButton;
    private TextView scoreMario;
    private TextView scoreLuigi;
    public Context context;
    public customView mCustom;

    public float x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = findViewById(R.id.mText);
        mInt = findViewById(R.id.mInt);
        mButton = findViewById(R.id.mButton);
        scoreMario = findViewById(R.id.scoreM);
        scoreLuigi = findViewById(R.id.scoreL);
        mCustom = findViewById(R.id.custom);
        context = this;

        scoreLuigi.setVisibility(View.GONE);
        scoreMario.setVisibility(View.GONE);
        mCustom.setVisibility(View.GONE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonVisibility();
                createdots();
            }
        });
    }

    public void createdots()    {
        int m = Integer.parseInt(mInt.getText().toString());
        mCustom.setMVal(m);
    }

    public void buttonVisibility()  {
        mText.setVisibility(View.GONE);
        mInt.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        scoreLuigi.setVisibility(View.VISIBLE);
        scoreMario.setVisibility(View.VISIBLE);
        mCustom.setVisibility(View.VISIBLE);
        mCustom.setEnable(true);

    }


}
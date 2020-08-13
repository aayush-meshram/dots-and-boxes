package com.myapp.secondtrial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
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
    private customView theImage;

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
        theImage = findViewById(R.id.theImage);

        scoreLuigi.setVisibility(View.INVISIBLE);
        scoreMario.setVisibility(View.INVISIBLE);

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
        customView customView = new customView(this, m);
    }

    public void buttonVisibility()  {
        mText.setVisibility(View.INVISIBLE);
        mInt.setVisibility(View.INVISIBLE);
        mButton.setVisibility(View.INVISIBLE);
        scoreLuigi.setVisibility(View.VISIBLE);
        scoreMario.setVisibility(View.VISIBLE);
    }

}
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
    private customView theImage;
    public Context context;

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
        context = this;

        scoreLuigi.setVisibility(View.INVISIBLE);
        scoreMario.setVisibility(View.INVISIBLE);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonVisibility();
                createdots();
            }
        });
        if(mInt.getText().toString().isEmpty())
            return;
        else {
            int m = Integer.parseInt(mInt.getText().toString());
            final customView customView = new customView(this, m);


            customView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    if (mInt.getText().toString().isEmpty())
                        return false;

                    int m = Integer.parseInt(mInt.getText().toString());

                    //String coords[][] = new String[c][c];

                    float x = motionEvent.getX();
                    float y = motionEvent.getY();

                    float x1 = (1 / (float) (m + 10)) * x;
                    float y1 = (1 / (float) (m + 10)) * y;

                    //float list[] = new float[100];
                    //int count = 0;

                    float d = 0;

                    float tempx = x1;
                    float tempy = y1;

                    for (int i = 1; i <= m; i++) {


                        float dist = 0;
                        x1 = ((1 / (float) (m)) * x) / 2.0F;
                        for (int j = 1; j <= m; j++) {
                            //coords[i][j] = String.valueOf(i + "," + j);
                            x1 += ((1 / (float) (m)) * x);
                            dist = (Math.abs(tempx - x1));
                            if (dist < d)
                                d = dist;
                        }
                        y1 += ((1 / (float) (m)) * x);
                        dist = Math.abs(tempy - y1);
                        if (dist < d)
                            d = dist;
                        tempx = x1;
                        tempy = y1;
                        Log.d("ENTERED:", "OnTouch");
                    }
                    return true;
                }
            });
        }

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
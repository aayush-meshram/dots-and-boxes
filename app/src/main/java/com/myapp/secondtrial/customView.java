package com.myapp.secondtrial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import java.nio.file.Path;
import java.util.regex.Pattern;

public class customView extends View {
    public static int m;
    public Bitmap mBitmap;
    public Paint mPaint = new Paint();
    public int mBitmapX;
    public int mBitmapY;
    public RectF mRect;
    public Boolean b = true;
    public Boolean enableView = false;
    public String co_ords[][];
    public Boolean updateView = false;
    public float maxDist;
    String d;
    String d1;

    public customView(Context context)  {
        super(context);
        b = true;
    }

    public void setMVal(int n) {
        m = n;
    }

    public void setEnable(boolean e){
        enableView = e;
    }

    public void setUpdate(boolean u)    {
        updateView = u;
    }

    public customView(Context context, AttributeSet attrs)  {
        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setAntiAlias(true);
        mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));

        co_ords = new String[m][m];

        super.onDraw(canvas);

        float x = getWidth();
        float y = getHeight();

        float x1 = (1 / (float) (m + 10)) * x;
        float y1 = (1 / (float) (m + 10)) * y;

        maxDist = ((1 / (float) (m)) * x) / 2.0F;

        for (int i = 1; i <= m; i++) {
            x1 = ((1 / (float) (m)) * x) / 2.0F;
            for (int j = 1; j <= m; j++) {
                canvas.drawCircle(x1, y1, 20.0F, mPaint);
                co_ords[i-1][j-1] = x1+","+y1;
                x1 += ((1 / (float) (m)) * x);
            }
            y1 += ((1 / (float) (m)) * x);
        }

        if(m != 0) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
            setUpBitmap();
            int left = (int) (0.05 * getWidth());
            int top = (int) ((getHeight() - mBitmap.getHeight()) - (0.05 * getWidth()));
            canvas.drawBitmap(mBitmap, left, top, mPaint);

            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.luigi);
            setUpBitmap();
            left = (int) ((getWidth() - mBitmap.getWidth()) - (0.05 * getWidth()));
            top = (int) ((getHeight() - mBitmap.getHeight()) - (0.05 * getWidth()));
            canvas.drawBitmap(mBitmap, left, top, mPaint);
        }

        //MAKING LINES HERE
        if(updateView)  {
            Pattern pattern = Pattern.compile(",");
            int startI = Integer.parseInt(pattern.split(d)[0]);
            int startJ = Integer.parseInt(pattern.split(d)[1]);
            int stopI = Integer.parseInt(pattern.split(d1)[0]);
            int stopJ = Integer.parseInt(pattern.split(d1)[1]);
            float startX = Float.parseFloat(pattern.split(co_ords[startI][startJ])[0]);
            float startY = Float.parseFloat(pattern.split(co_ords[startI][startJ])[1]);
            float stopX = Float.parseFloat(pattern.split(co_ords[stopI][stopJ])[0]);
            float stopY = Float.parseFloat(pattern.split(co_ords[stopI][stopJ])[1]);
            canvas.drawLine(startX, startY, stopX, stopY, new Paint(Color.BLACK));
            //Toast.makeText(this.getContext(),"updateView kaam kr gaya", Toast.LENGTH_SHORT).show();
            //invalidate();
        }

        invalidate();

    }
    public void setUpBitmap()   {
        mBitmapX = (int) Math.floor(mBitmap.getWidth());
        mBitmapY = (int) Math.floor(mBitmap.getHeight());
        mRect = new RectF(mBitmapX, mBitmapY,
                mBitmapX + mBitmap.getWidth(),
                mBitmapY + mBitmap.getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(enableView){
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                if(isOutside(event.getX(), event.getY()))   {
                    d = mindistance(event.getX(), event.getY());
                    Toast.makeText(this.getContext(), "Touch detected: "+d, Toast.LENGTH_SHORT).show();
                    d1 = secondmindist(event.getX(), event.getY());
                    Toast.makeText(this.getContext(), "Touch (second) detected: "+d1, Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this.getContext(), "NOW IT'S AT THE OUTSIDE",Toast.LENGTH_SHORT).show();
                    // Add the code to be executed on Touch event HERE

            }
            return true;
        }
        return false;
    }

    public String mindistance(float x, float y) {
        float dist = Float.MAX_VALUE;
        String s1 = new String();
        Pattern pattern = Pattern.compile(",");
        for(int i = 0; i < m; i++)  {
            for(int j = 0; j < m; j++)  {
                float X = Float.parseFloat(pattern.split(co_ords[i][j])[0]);
                float Y = Float.parseFloat(pattern.split(co_ords[i][j])[1]);
                float d = (float)Math.sqrt(Math.pow((X - x),2) + Math.pow((Y - y),2));
                if(d < dist)    {
                    dist = d;
                    s1 = i+","+j;
                }
            }
        }
        return s1;
    }

    public boolean isOutside(float x, float y)  {
        Pattern pattern = Pattern.compile(",");
        float minx = Float.parseFloat(pattern.split(co_ords[0][0])[0]);
        float miny = Float.parseFloat(pattern.split(co_ords[0][0])[1]);
        float maxx = Float.parseFloat(pattern.split(co_ords[m-1][m-1])[0]);
        float maxy = Float.parseFloat(pattern.split(co_ords[m-1][m-1])[0]);
        if(x >= minx-20 && x <= maxx+20 && y >= miny-20 && y <= maxy+20)
            return true;
        else
            return false;
    }

    public String secondmindist(float x, float y)   {
        String s = new String();
        String s1 = mindistance(x,y);
        float dist = Float.MAX_VALUE;
        Pattern pattern = Pattern.compile(",");
        float tempx = Integer.parseInt(pattern.split(s1)[0]);
        float tempy = Integer.parseInt(pattern.split(s1)[1]);

        /*float zeroX = Float.parseFloat(pattern.split(co_ords[0][0])[0]);
        float zeroY = Float.parseFloat(pattern.split(co_ords[0][0])[1]);
        float oneX = Float.parseFloat(pattern.split(co_ords[1][1])[0]);
        float oneY = Float.parseFloat(pattern.split(co_ords[1][1])[1]);
        //float maxDist = (float)Math.sqrt(Math.pow((zeroX - oneX),2) + Math.pow((zeroY - oneY),2));*/

        for(int i = 0; i < m; i++)  {
            for(int j = 0; j < m; j++)  {
                float X = Float.parseFloat(pattern.split(co_ords[i][j])[0]);
                float Y = Float.parseFloat(pattern.split(co_ords[i][j])[1]);
                float d = (float)Math.sqrt(Math.pow((X - x),2) + Math.pow((Y - y),2));
                if(d < dist &&  i!=tempx && j!=tempy)    {
                    if(d > 0 && d <= maxDist) {
                        dist = d;
                        s = i + "," + j;
                    }
                }
            }
        }
        updateView = true;
        return s;
    }

}

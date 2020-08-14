package com.myapp.secondtrial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

public class customView extends View {
    public static int m;
    public Bitmap mBitmap;
    public Paint mPaint = new Paint();
    public int mBitmapX;
    public int mBitmapY;
    public RectF mRect;
    public Boolean b = true;
    public Boolean enableView = false;

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

    public customView(Context context, AttributeSet attrs)  {
        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setAntiAlias(true);
        mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));


        super.onDraw(canvas);

        float x = getWidth();
        float y = getHeight();

        float x1 = (1 / (float) (m + 10)) * x;
        float y1 = (1 / (float) (m + 10)) * y;
        for (int i = 1; i <= m; i++) {
            x1 = ((1 / (float) (m)) * x) / 2.0F;
            for (int j = 1; j <= m; j++) {
                canvas.drawCircle(x1, y1, 20.0F, mPaint);
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
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                    Toast.makeText(this.getContext(), "Touch detected", Toast.LENGTH_SHORT).show();
                    // Add the code to be executed on Touch event HERE
            }

            return true;
        }

        return false;
    }
}

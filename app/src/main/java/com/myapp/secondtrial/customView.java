package com.myapp.secondtrial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

public class customView extends View {
    public static int m;
    public Bitmap mBitmap;
    public Paint mPaint;
    public int mBitmapX;
    public int mBitmapY;
    public RectF mRect;
    public Boolean b;


    public customView(Context context, int n)  {
        super(context);
        m = n;
        b = true;
    }

    public customView(Context context, AttributeSet attrs)  {
        super(context,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setAntiAlias(true);
        mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));

        if (b) {
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
    }
    public void setUpBitmap()   {
        mBitmapX = (int) Math.floor(mBitmap.getWidth());
        mBitmapY = (int) Math.floor(mBitmap.getHeight());
        mRect = new RectF(mBitmapX, mBitmapY,
                mBitmapX + mBitmap.getWidth(),
                mBitmapY + mBitmap.getHeight());
    }
}

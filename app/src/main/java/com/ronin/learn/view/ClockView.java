package com.ronin.learn.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ronin.eventbus.kotlin.R;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ClockView extends View {

    float mWidth = 2f;
    int mColor = Color.parseColor("#0fd897");
    Paint mPaint;

    RectF rectF;
    RectF roundRect;
    float width, height, radius, smallLen, largeLen;


    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ClockView);
        mWidth = typedArray.getDimension(R.styleable.ClockView_c_width, mWidth);
        mColor = typedArray.getColor(R.styleable.ClockView_c_color, mColor);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(mWidth);
        new MyThead().start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xff000000);
        mPaint.setColor(0x88555555);

        canvas.drawRoundRect(roundRect, 10, 10, mPaint);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), radius, mPaint);
        mPaint.setColor(mColor);

        float sx, sy, ex, ey;
        for (int i = 0; i < 60; ++i) {
            sx = radius * (float) Math.cos(Math.PI / 180 * i * 6);
            sy = radius * (float) Math.sin(Math.PI / 180 * i * 6);
            if (i % 5 == 0) {
                ex = sx + largeLen * (float) Math.cos(Math.PI / 180 * i * 6);
                ey = sy + largeLen * (float) Math.sin(Math.PI / 180 * i * 6);
            } else {
                ex = sx + smallLen * (float) Math.cos(Math.PI / 180 * i * 6);
                ey = sy + smallLen * (float) Math.sin(Math.PI / 180 * i * 6);
            }
            sx += rectF.centerX();
            ex += rectF.centerX();
            sy += rectF.centerY();
            ey += rectF.centerY();
            canvas.drawLine(sx, sy, ex, ey, mPaint);
        }
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), 20, mPaint);
        canvas.rotate(60f, rectF.centerX(), rectF.centerY());
        canvas.drawLine(rectF.centerX(), rectF.centerY(),
                rectF.centerX(), rectF.centerY() - radius, mPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectF = new RectF(getLeft(), getTop(), getRight(), getBottom());
        width = rectF.right - rectF.left;
        height = rectF.bottom - rectF.top;
        if (width < height) {
            radius = width / 4;
        } else {
            radius = height / 4;
        }

        smallLen = 10;
        largeLen = 20;
        roundRect = new RectF(rectF.centerX() - (float) 0.9 * rectF.width() / 2,
                rectF.centerY() - (float) 0.9 * rectF.height() / 2,
                rectF.centerX() + (float) 0.9 * rectF.width() / 2,
                rectF.centerY() + (float) 0.9 * rectF.height() / 2);
    }

    public static class MyThead extends Thread {
        private Handler handler = null;

        public MyThead() {
            handler = new Handler();
        }

        @Override
        public void run() {
            super.run();
            handler = new Handler();
        }
    }


}

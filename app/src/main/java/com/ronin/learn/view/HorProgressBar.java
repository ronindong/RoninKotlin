package com.ronin.learn.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ronin.eventbus.kotlin.R;

/**
 * Created by Administrator on 2017/3/14.
 */

public class HorProgressBar extends View {

    float h_width = 180f, h_height = 10f;
    float startX = 0f, delta = 5f;
    int h_color = Color.parseColor("#1E88E5");
    int space = 30;
    Paint mPaint;
    int index = 0;

    public HorProgressBar(Context context) {
        this(context, null);
    }

    public HorProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.HorProgressBar);
        h_width = typedArray.getDimension(R.styleable.HorProgressBar_h_width, h_width);
        h_height = typedArray.getDimension(R.styleable.HorProgressBar_h_height, h_height);
        h_color = typedArray.getColor(R.styleable.HorProgressBar_h_color, h_color);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(h_color);
        mPaint.setStrokeWidth(h_height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float sw = getMeasuredWidth();
        if (startX > sw + (h_width + space) - (sw % (h_width + space))) {
            startX = 0;
        } else {
            startX += delta;
        }
        float start = startX;
        // draw latter parse
        while (start < sw) {
            canvas.drawLine(start, 5, start + h_width, 5, mPaint);
            start += (h_width + space);
        }

        start = startX - space - h_width;

        // draw front parse
        while (start >= -h_width) {
            canvas.drawLine(start, 5, start + h_width, 16, mPaint);
            start -= (h_width + space);
        }
        if (index >= 700000) {
            index = 0;
        }
        invalidate();

    }
}

package com.cclean.mover.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.cclean.mover.R;

public class LinedEditText extends androidx.appcompat.widget.AppCompatEditText
{
    private Rect mRect;
    private Paint mPaint;

    // we need this constructor for LayoutInflater
    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = getLineCount();
        Rect r = mRect;
        Paint paint = mPaint;

        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, r);

            canvas.drawLine(r.left, baseline + 50, r.right, baseline + 50, paint);
        }

        super.onDraw(canvas);
    }
}
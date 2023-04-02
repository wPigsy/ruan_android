package com.ruan.mydialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CicleView extends View {

    private Paint defaultPaint;
    private RectF mRectF;

    public CicleView(Context context) {
        super(context);
        initialize();
    }

    public CicleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CicleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public CicleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    /**
     * 初始化
     */
    private void initialize() {
        // 底环画笔
        defaultPaint = new Paint();
        defaultPaint.setColor(Color.RED);
        defaultPaint.setStyle(Paint.Style.STROKE);
        defaultPaint.setStrokeCap(Paint.Cap.ROUND);
        defaultPaint.setStrokeWidth(20);
        defaultPaint.setAntiAlias(true);

        mRectF = new RectF(300, 300, 300, 300);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆心坐标
        int mXCenter = getWidth() / 2;
        int mYCenter = getHeight() / 2;
        int radius = (int) (mXCenter - 20 / 2); //圆环的半径
        //绘制圆环
        canvas.drawCircle(mXCenter, mYCenter, radius, defaultPaint);
//        drawCicle(canvas);
    }

    private void drawCicle(Canvas canvas) {
        canvas.save();
        canvas.drawArc(mRectF, 40, 40, false, defaultPaint);
        canvas.restore();
    }
}

package com.ruan.mydialog.uikit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ruan.mydialog.R;

public class UikitProgress extends View {

    private float mProgressWidth;
    private float mProgressBgWidth;
    private int mProgressBgColor;
    private int mProgressStartColor;
    private int mProgressEndColor;
    private float mProgressRadius;
    private Paint mBgPaint;
    private Paint mProgressPaint;
    private float mProgressValue;
    private int mDrawWidth;


    public UikitProgress(Context context) {
        this(context, null);
    }

    public UikitProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UikitProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.uikit_progress_default);
    }

    public UikitProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UikitProgress,
                defStyleAttr, defStyleRes);
        mProgressWidth = typedArray.getDimension(R.styleable.UikitProgress_uikit_progress_width,
                context.getResources().getDimension(R.dimen.default_uikit_progress_width));
        mProgressBgWidth = typedArray.getDimension(R.styleable.UikitProgress_uikit_progress_bg_width,
                context.getResources().getDimension(R.dimen.default_uikit_progress_bg_width));
        mProgressBgColor = typedArray.getColor(R.styleable.UikitProgress_uikit_progress_bg_color,
                context.getColor(R.color.default_uikit_progress_bg_color));
        mProgressStartColor = typedArray.getColor(R.styleable.UikitProgress_uikit_progress_start_color,
                context.getColor(R.color.default_uikit_progress_start_color));
        mProgressEndColor = typedArray.getColor(R.styleable.UikitProgress_uikit_progress_end_color,
                context.getColor(R.color.default_uikit_progress_end_color));
        mProgressRadius = typedArray.getDimension(R.styleable.UikitProgress_uikit_progress_radius,
                context.getResources().getDimension(R.dimen.default_uikit_progress_width));
        mProgressValue = typedArray.getFloat(R.styleable.UikitProgress_uikit_progress_value,
                0F);
        typedArray.recycle();

        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        // 设置画笔为圆角
        mBgPaint.setStrokeCap(Paint.Cap.ROUND);
        // 设置画笔为空心
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setStrokeWidth(mProgressBgWidth);
        mBgPaint.setColor(mProgressBgColor);

        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        // 设置画笔为圆角
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        // 设置画笔为空心
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(mProgressWidth);
        mProgressPaint.setColor(mProgressStartColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 原形的宽
        mDrawWidth = Math.min(getWidth(), getHeight());
        // 若半径
        float tempDiameter = mDrawWidth - Math.max(mProgressBgWidth, mProgressWidth);
        mProgressRadius = Math.min(tempDiameter / 2, mProgressRadius);
        drawBg(canvas);
        drawProgress(canvas);
    }

    private void drawBg(Canvas canvas) {
        canvas.save();
        canvas.translate(mDrawWidth / 2, mDrawWidth / 2);
        RectF rectF = new RectF(-mProgressRadius, -mProgressRadius, mProgressRadius, mProgressRadius);
        //oval :指定圆弧的外轮廓矩形区域。
        //startAngle: 圆弧起始角度，单位为度。
        //sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度,从右中间开始为零度。
        //useCenter:为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。是否连接圆心
        //paint: 绘制圆弧的画板属性。
        canvas.drawArc(rectF, 0, 360, false, mBgPaint);
        canvas.restore();
    }

    private void drawProgress(Canvas canvas) {
        float sweepAngle = mProgressValue / 100 * 360;
        canvas.save();
        canvas.translate(mDrawWidth / 2, mDrawWidth / 2);
        RectF rectF = new RectF(-mProgressRadius, -mProgressRadius, mProgressRadius, mProgressRadius);
        canvas.drawArc(rectF, -90, sweepAngle, false, mProgressPaint);
        canvas.restore();
    }

    public void setProgress(float progress) {
        mProgressValue = progress;
        invalidate();
    }
}

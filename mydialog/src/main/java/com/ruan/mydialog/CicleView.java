package com.ruan.mydialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CicleView extends View {

    private Paint defaultPaint;

    public CicleView(Context context) {
        super(context);
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
        defaultPaint.setColor(Color.argb(0xEE, 0x8E, 0x8E, 0x8E));
        defaultPaint.setStyle(Paint.Style.STROKE);
        defaultPaint.setStrokeWidth(20);
        defaultPaint.setAntiAlias(true);

    }

}

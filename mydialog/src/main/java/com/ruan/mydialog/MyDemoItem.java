package com.ruan.mydialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MyDemoItem extends ConstraintLayout {
    int[] styleable = R.styleable.MyDemoItem;

    public MyDemoItem(@NonNull Context context) {
        super(context);
        initLayout(context);

    }

    private View initLayout(Context context) {

        return LayoutInflater.from(context).inflate(R.layout.item_add_icon, this);
    }

    public MyDemoItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyDemoItem);
        String customerText = typedArray.getString(R.styleable.MyDemoItem_centerText);
        typedArray.recycle();//注意通过TypedArray得到数据后要调用其recycle方法。
        View inflate = initLayout(context);
        TextView viewById = inflate.findViewById(R.id.center_text);
        viewById.setText(customerText);
    }

    public MyDemoItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyDemoItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}

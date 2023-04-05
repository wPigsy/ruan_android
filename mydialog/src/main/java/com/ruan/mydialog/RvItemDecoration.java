package com.ruan.mydialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RvItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;

    public RvItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        //自定义分割线颜色值
        mDivider = AppCompatResources.getDrawable(context, R.color.purple_700);
        a.recycle();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent);
    }

    /**
     * 是否是最后一行
     */
    private boolean isLastRow(int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //有多少列
        if (layoutManager instanceof GridLayoutManager) {
            int childCount = parent.getAdapter().getItemCount();

            double count = Math.ceil((double) childCount / (double) spanCount);//总行数
            double currentCount = Math.ceil((double) (itemPosition + 1) / spanCount);//当前行数

            //最后当前数量小于总的
            if (currentCount < count) {
                return false;
            }
        }
        return true;
    }

    /***
     * 绘制水平分割线
     * @param c
     * @param parent
     */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 绘制垂直线
     *
     * @param c
     * @param parent
     */
    public void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        //这里固定了，最后一个不用线条
        if (childCount <= 0) {
            return;
        }
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, 100, right + 2, bottom - 100);
            mDivider.draw(c);
        }
    }

    /**
     * 判断是否是最后一列
     */
    private boolean isLastColum(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //有多少列
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = getSpanCount(parent);
            //因为是从0可以所以要将ItemPosition先加1
            if ((itemPosition + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }
}

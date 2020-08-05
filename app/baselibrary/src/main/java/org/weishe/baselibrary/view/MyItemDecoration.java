package org.weishe.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Tmp on 2019/3/5.
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private int mDividerHeight = 2;

    public MyItemDecoration() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ffffff"));
    }



    public MyItemDecoration(Context context,int dividerHeight,  int color) {
        mDividerHeight = (int) (context.getResources().getDisplayMetrics().density * dividerHeight + 0.5f);
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(color));
    }

    //设置ItemView的内嵌偏移长度（inset）
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    //    绘制在子view的下层
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }


    //    绘制在子view的上层
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            // 画水平线
            int left = child.getLeft();
            int top = child.getBottom();
            int right = child.getRight();
            int bottom = top + mDividerHeight;
            c.drawRect(left, top, right, bottom, mPaint);

            //        画竖直线
            int top2 = child.getTop();
            int right2 = child.getRight() + mDividerHeight;
            c.drawRect(right, top2, right2, top, mPaint);
        }
    }
}



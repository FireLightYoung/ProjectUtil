package com.util.ming.projectutil.demo.recycermanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

class MyLayoutManager extends RecyclerView.LayoutManager {

    Context mContext;
    int screenWidth;
    int screenHeight;

    public MyLayoutManager(Context context) {
        mContext = context;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();
    }


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int offsetX = 0;
        int offsetY = 0;

        for (int i = 0; i < getItemCount(); i++) {

            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            if (i == 0) {
                //  public void layoutDecorated(View child, int left, int top, int right, int bottom)
                layoutDecorated(view, screenWidth / 2 - width / 2, offsetY, screenWidth / 2 - width / 2 + width, offsetY + height);
                offsetY += height;
            } else {
                layoutDecorated(view, offsetX, offsetY, offsetX + width, offsetY + height);
                offsetX += width;
            }
        }
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        
        View view = recycler.getViewForPosition(0);
        view.scrollBy(-dx, 0);
        offsetChildrenHorizontal(-dx);
        return dx;
    }

    /**
     * 允许左右滑动
     *
     * @return
     */
    @Override
    public boolean canScrollHorizontally() {
        return true;
    }
}

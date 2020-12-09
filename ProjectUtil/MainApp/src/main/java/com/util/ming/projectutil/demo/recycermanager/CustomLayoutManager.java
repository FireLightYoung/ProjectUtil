package com.util.ming.projectutil.demo.recycermanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 基本复使用逻辑，认识LayoutManager
 */
public class CustomLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //定义竖直方向的偏移量
        int offsetY = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);//测量view
            int width = getDecoratedMeasuredWidth(view);//得到测量出来的宽度，得到的是item+decoration的总宽度
            int height = getDecoratedMeasuredHeight(view);//得到测量出来的高度，得到的是item+decoration的总高度
            //不获取item+decoration的总宽度，而只获取view的高和宽，那么调用下面的这个方法
            //  int width = view.getMeasuredWidth()；
            //  int height =view.getMeasuredHeigh()
            layoutDecorated(view, 0, offsetY, width, offsetY + height);//摆放位置
            offsetY += height;
        }
        //如果所有子View的高度和没有填满RecyclerView的高度，
        // 则将高度设置为RecyclerView的高度
        mTotalHeight = Math.max(offsetY, getVerticalSpace());
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }


//    /**
//     * 在scrollVerticallyBy中,dy表示手指在屏幕上每次滑动的位移.
//     * <p>
//     * 当手指由下往上滑时,dy>0
//     * 当手指由上往下滑时,dy<0
//     *
//     * @param dy
//     * @param recycler
//     * @param state
//     * @return
//     */
//    @Override
//    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
//        // 平移容器内的item
//        offsetChildrenVertical(-dy);
//        return dy;
//    }


    private int mSumDy = 0;
    private int mTotalHeight = 0;

    /**
     * 在scrollVerticallyBy中,dy表示手指在屏幕上每次滑动的位移.
     * <p>
     * 当手指由下往上滑时,dy>0
     * 当手指由上往下滑时,dy<0
     *
     * @param dy
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int travel = dy;
        //如果滑动到最顶部
        //判断到顶相对比较容易，我们只需要把所有的dy相加，如果小于0，就表示已经到顶了
        if (mSumDy + dy < 0) {
            travel = -mSumDy;
        } else if (mSumDy + dy > mTotalHeight - getVerticalSpace()) {
            travel = mTotalHeight - getVerticalSpace() - mSumDy;
        }

        mSumDy += travel;
        // 平移容器内的item
        offsetChildrenVertical(-travel);
        return dy;
    }


    /**
     * 允许竖直滑动
     *
     * @return
     */
    @Override
    public boolean canScrollVertically() {
        return true;
    }
}

package com.util.ming.projectutil.demo.recycermanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;

/**
 * 加上复用逻辑，使用三级缓存，进行处理
 * 参考 https://qijian.blog.csdn.net/article/details/84866486?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
 * 但实际和有点博客出入，博客是只有一种ViewHolder
 */
public class Custom2LayoutManager extends RecyclerView.LayoutManager {

    HashMap<Integer, Rect> mIterms = new HashMap<>();

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    //定义竖直方向的偏移量
    int offsetY = 0;

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //现将所有的item从屏幕上剥离清空
        detachAndScrapAttachedViews(recycler);
        //没有数据，不处理
        if (getItemCount() == 0) {
            return;
        }


        int count = 0;
        for (int i = 0; i < getItemCount(); i++) {
            // public Rect(int left, int top, int right, int bottom)
            Rect rect = new Rect();
            View view = recycler.getViewForPosition(i);
            measureChildWithMargins(view, 0, 0);//测量view
            int width = getDecoratedMeasuredWidth(view);//得到测量出来的宽度，得到的是item+decoration的总宽度
            int height = getDecoratedMeasuredHeight(view);//得到测量出来的高度，得到的是item+decoration的总高度
            rect.left = 0;
            rect.right = width;
            rect.top = offsetY;
            rect.bottom = offsetY + height;
            mIterms.put(i, rect);
            offsetY = offsetY + height;
            if (offsetY <= getVerticalSpace()) {
                count++;
                addView(view);
                //设置位置前一定要先进行测量，否则摆放位置不对
                measureChildWithMargins(view, 0, 0);
                //public void layoutDecorated(View child, int left, int top, int right, int bottom)
                layoutDecorated(view, rect.left, rect.top, rect.right, rect.bottom);//摆放位置
            }
        }
        mTotalHeight = Math.max(offsetY, getVerticalSpace());
    }

    /**
     * recyclerView用于显示item的高度
     *
     * @return
     */
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
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() <= 0) {
            return dy;
        }

        int travel = dy;
        //如果滑动到最顶部
        if (mSumDy + dy < 0) {
            travel = -mSumDy;
        } else if (mSumDy + dy > mTotalHeight - getVerticalSpace()) {
            //如果滑动到最底部
            travel = mTotalHeight - getVerticalSpace() - mSumDy;
        }

        //回收越界子View
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (travel > 0) {//需要回收当前屏幕，上越界的View
                if (getDecoratedBottom(child) - travel < 0) {
                    removeAndRecycleView(child, recycler);
                    continue;
                }
            } else if (travel < 0) {//回收当前屏幕，下越界的View
                if (getDecoratedTop(child) - travel > getHeight() - getPaddingBottom()) {
                    removeAndRecycleView(child, recycler);
                    continue;
                }
            }
        }


        //进行view的添加
        Rect visibleRect = getVisibleArea(travel);
        //布局子View阶段，由travel判断来判断是上滑还是下滑，是向上添加还是向下添加。
        if (travel >= 0) {
            View lastView = getChildAt(getChildCount() - 1);
            int minPos = getPosition(lastView) + 1;//从最后一个View+1开始吧

            //顺序addChildView
            for (int i = minPos; i <= getItemCount() - 1; i++) {
                Rect rect = mIterms.get(i);
                if (Rect.intersects(visibleRect, rect)) {
                    View child = recycler.getViewForPosition(i);
                    addView(child);
                    measureChildWithMargins(child, 0, 0);
                    layoutDecorated(child, rect.left, rect.top - mSumDy, rect.right, rect.bottom - mSumDy);
                } else {
                    break;
                }
            }
        } else {
            View firstView = getChildAt(0);
            int maxPos = getPosition(firstView) - 1;

            for (int i = maxPos; i >= 0; i--) {
                Rect rect = mIterms.get(i);
                if (Rect.intersects(visibleRect, rect)) {
                    View child = recycler.getViewForPosition(i);
                    addView(child, 0);//将View添加至RecyclerView中，childIndex为1，但是View的位置还是由layout的位置决定
                    measureChildWithMargins(child, 0, 0);
                    layoutDecoratedWithMargins(child, rect.left, rect.top - mSumDy, rect.right, rect.bottom - mSumDy);
                } else {
                    break;
                }
            }
        }

        mSumDy += travel;
        // 平移容器内的item
        offsetChildrenVertical(-travel);
        return travel;
    }


    private Rect getVisibleArea(int travel) {
        Rect result = new Rect(getPaddingLeft(), getPaddingTop() + mSumDy + travel, getWidth() + getPaddingRight(), getVerticalSpace() + mSumDy + travel);
        return result;
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

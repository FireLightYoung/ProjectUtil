package com.util.ming.projectutil.demo.drawdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ming on 17/10/26.
 */
public class DrawUI extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder holder;


    private boolean isDrawable = false;

    private Context mContext;

    public DrawUI(Context context) {
        super(context);
        holder = this.getHolder();
        holder.addCallback(this);
        mContext = context;
    }

    public DrawUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = this.getHolder();
        holder.addCallback(this);
        mContext = context;
    }

    public DrawUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        holder = this.getHolder();
        holder.addCallback(this);
        mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawUI(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        holder = this.getHolder();
        holder.addCallback(this);
        mContext = context;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawable = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawable = false;
    }

    @Override
    public void run() {
        int position = 0;
        while (isDrawable) {
            Log.i("yang", "绘制=" + position);
            drawUI(position);
            if (position <= getWidth() && position <= getHeight()) {
                position++;
            } else {
                isDrawable = false;
                clearDraw2();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isDrawable = true;
                position = 0;
            }
        }

    }

    public void drawUI(int position) {

        Canvas canvas = holder.lockCanvas();
        try {
            drawCanvas(canvas, position);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void drawCanvas(Canvas canvas, int position) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.GREEN);// 画笔为绿色
        mPaint.setStrokeWidth(2);// 设置画笔粗细
//        canvas.drawPoint(position, getHeight() / 2, mPaint);
        String s = String.valueOf(position);
        canvas.drawText(s, getWidth() / 2, getHeight() / 2, mPaint);
    }

    /**
     * 错误的清屏方法
     */
    void clearDraw() {
        Canvas canvas = holder.lockCanvas(null);
        canvas.drawColor(Color.BLACK);// 清除画布
        holder.unlockCanvasAndPost(canvas);

    }

    public void clearDraw2() {

        Canvas canvas = null;
        try {

            canvas = holder.lockCanvas(null);
            canvas.drawColor(Color.WHITE);
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);

        } catch (Exception e) {


        } finally {

            if (canvas != null) {

                holder.unlockCanvasAndPost(canvas);

            }
        }
    }
}

package com.lightwind.guolu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * 功能：自定义锅炉压力控件
 * 作者：刘洋
 * 时间：2017/10/10
 */

public class PressureView extends View {

    // 锅炉压力值
    private int pressure;
    private Paint paint;

    public PressureView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
//        invalidate();// 在主线程中调用
        postInvalidate();// 在子线程中调用，导致onDraw()执行
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pressure > 220) {
            // 1、如果压力值大于220，就绘制文本，显示锅炉爆炸
            paint.setColor(Color.RED);
            paint.setTextSize(30);
            canvas.drawText("要爆炸了", 10, getHeight() / 2, paint);
        } else {
            // 2、正常和提示的情况（设置背景颜色为灰色）
            paint.setColor(Color.GRAY);
            canvas.drawRect(10, 10, 60, 260, paint);
            // 2.1、如果小于200正常显示，并且显示为绿色
            if (pressure < 200) {
                paint.setColor(Color.GREEN);
                canvas.drawRect(10, 260 - pressure, 60, 260, paint);
            } else if (pressure >= 200) {
                // 2.2、如果大于200，显示警示，显示为红色
                paint.setColor(Color.RED);
                canvas.drawRect(10, 260 - pressure, 60, 260, paint);
            }
        }
    }
}

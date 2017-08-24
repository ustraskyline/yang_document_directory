package com.example.administrator.mydifineview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/8/24.
 */

public class DifineView extends View {

    public float currentX = 40;
    public float currentY = 50;
    private Paint paint;

    // 这两个构造方法是必须的，因为需要向父类View传递参数
    public DifineView(Context context) {
        super(context);
    }

    public DifineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.i("yang", "---> onDraw： 开始绘制");

        paint = new Paint();
        paint.setColor(Color.RED);

        // 传入画笔paint作为参数，调用画布canvas的drawXXX()方法进行绘制
        canvas.drawCircle(currentX, currentY, 15, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.i("yang", "---> onTouchEvent： 触摸事件");
        currentX = event.getX();
        currentY = event.getY();

        invalidate();
        return true;
    }


//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        Log.i("yang", "---> onFinishInflate： 绘制完毕");
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.i("yang", "---> onMeasure： 测量完毕");
//    }
//
//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        Log.i("yang", "---> onAttachedToWindow： 放入窗口完毕");
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        Log.i("yang", "---> onDetachedFromWindow： 剥离窗口完毕");
//    }
}

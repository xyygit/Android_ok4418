package yann.uppermonitor.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yayun.xia on 2018/1/30.
 */

public class ChartView extends View {

    private int viewSize;//获取空间的尺寸，也就是我们布局的尺寸大小（不知道理解的是否正确）
    private Paint linePaint;// 线条画笔和点画笔

    private Path mPath;// 路径对象
    private TextPaint mTextPaint;// 文字画笔

    float lift ;
    float top ;
    float right ;
    float buttom ;


    float PathY_X ;
    float PathY_Y ;

    float PathX_X ;
    float PathX_Y ;

    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //第一步，初始化对象
        linePaint = new Paint();
        linePaint.setColor(Color.YELLOW);//线条的颜色
        linePaint.setStrokeWidth(4);//线条的宽度
        linePaint.setAntiAlias(true);//取消锯齿
        linePaint.setStyle(Paint.Style.STROKE);//粗线


        //初始化Path
        mPath = new Path();

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        mTextPaint.setColor(Color.WHITE);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 在我们没学习测量控件之前强制宽高一致
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //第二步骤，我们在这里获取每个用到的坐标点和尺寸

        viewSize = w;//获取空间的尺寸，
        Log.i("Text","viewSize:"+viewSize);

        //这个是我们上下左右需要用到的坐标点
        lift = viewSize*(1/16f);
        top = viewSize*(2/16f);
        right = viewSize*(15/16f);
        buttom = viewSize*(15/16f);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 锁定画布
        canvas.save();
        //定义一个绘制X,Y轴的方法
        drawXY(canvas);

        //绘制X和Y轴上的提示文字
        drawXYelement(canvas);

    }

    private void drawXY(Canvas canvas) {
        /*
        * 第三步，我们来通过viewSize尺寸来获取三个坐标点
        * 第一个（X,Y）--(lift,top)
        * 第二个（X,Y）--(lift,button)
        * 第三个个（X,Y）--(right,buttom)
        * */
        mPath.moveTo(lift, top);
        mPath.lineTo(lift, buttom);
        mPath.lineTo(right,buttom);

        //使用Path链接这三个坐标
        canvas.drawPath(mPath,linePaint);

        // 释放画布
        canvas.restore();
    }

    private void drawXYelement(Canvas canvas) {
        // 锁定画布
        canvas.save();
        mTextPaint.setTextSize(36);//文字大小

        /*
        * Y轴文字提示
        * drawText(String ,x,y,TextPaint)
        * (lift,top)
        * */
        mTextPaint.setTextAlign(Paint.Align.LEFT);//左对齐
        canvas.drawText("Y",lift+20,top,mTextPaint);


        /*
        * X轴文字提示
        * drawText(String ,right,buttom,TextPaint)
        * */
        mTextPaint.setTextAlign(Paint.Align.RIGHT);//右对齐
        canvas.drawText("X",right,buttom+50,mTextPaint);
        // 释放画布
        canvas.restore();
    }

}

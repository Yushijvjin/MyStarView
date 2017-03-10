package com.example.mystarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/10 15:14
 */

public class MyStarView extends View {
    private float radius;
    private Paint paint;
    private MyPoint cPoint;

    private MyPoint p1;
    private MyPoint p2;
    private MyPoint p3;
    private MyPoint p4;
    private MyPoint p5;

    public MyStarView(Context context) {
        this(context, null);
    }

    public MyStarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyStarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyStarView);
        radius = typedArray.getDimension(R.styleable.MyStarView_radius, 200);
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        circle(canvas);
        makeP5();
        drawStar(canvas);


    }

    private void drawStar(Canvas canvas) {

        canvas.drawLine(p1.x, p1.y, p5.x, p5.y, paint);
        canvas.drawLine(p1.x, p1.y, p4.x, p4.y, paint);
        canvas.drawLine(p2.x, p2.y, p3.x, p3.y, paint);
        canvas.drawLine(p2.x, p2.y, p5.x, p5.y, paint);
        canvas.drawLine(p4.x, p4.y, p3.x, p3.y, paint);


    }

    private void makeP5() {
        //上方
        p1 = new MyPoint();
        p1.x = cPoint.x;
        p1.y = cPoint.y - radius;

        double gen5 = Math.sqrt(5);
        double du36 = Math.toRadians(36);
//        Math.pow();
        float cf = (float) Math.sqrt((gen5 - 1) * radius * 0.5 * (gen5 - 1) * radius * 0.5 + radius * radius);
        float cfy = (float) (cf * Math.sin(du36));
        float cfx = (float) (cf * Math.cos(du36));
        //左上
        p2 = new MyPoint();
        p2.x = p1.x - cfx;
        p2.y = p1.y + cfy;
        //右上
        p3 = new MyPoint();
        p3.x = p1.x + cfx;
        p3.y = p1.y + cfy;

        double du18 = Math.toRadians(18);
        double cm = 2 * radius * Math.cos(du18);

        float cmy = (float) (cm * Math.cos(du18));
        float cmx = (float) (cm * Math.sin(du18));
        //左下
        p4 = new MyPoint();
        p4.x = p1.x - cmx;
        p4.y = p1.y + cmy;
        //右下
        p5 = new MyPoint();
        p5.x = p1.x + cmx;
        p5.y = p1.y + cmy;

    }

    //画圆
    private void circle(Canvas canvas) {
        paint.setColor(Color.RED);
        cPoint = new MyPoint();
        cPoint.x = getWidth() / 2;
        cPoint.y = getHeight() / 2;
        canvas.drawCircle(cPoint.x, cPoint.y, radius, paint);
    }
}

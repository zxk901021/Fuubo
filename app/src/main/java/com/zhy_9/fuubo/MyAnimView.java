package com.zhy_9.fuubo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ZHY_9 on 2015/6/4.
 */
public class MyAnimView extends View {

    public static final float RADIUS = 50f;
    private Point currentPoint;
    private Paint paint;
    private String color;


    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(R.color.material_color));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        }else {
            drawCircle(canvas);
        }
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    private void drawCircle(Canvas canvas){
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, paint);
    }

    private void startAnimation(){
        Point startPoint = new Point(RADIUS, RADIUS);
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        ObjectAnimator anim2 = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(),
                "#ff0babf3", "#fff36be3");
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(valueAnimator).with(anim2);
        animSet.setDuration(5000);
        animSet.start();
    }

}

package com.hfantin.hac.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hamilton on 18/09/17.
 */

public class GraficoMeiaLua extends View {


    public GraficoMeiaLua(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 100;
        int desiredHeight = 100;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int largura, int altura, int larguraAntigo, int alturaAntigo) {
        super.onSizeChanged(largura, altura, larguraAntigo, alturaAntigo);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        super.onDraw(canvas);
        float width = (float) getWidth();
        float height = (float) getHeight();
        float radius;

        if (width > height) {
            radius = height / 4;
        } else {
            radius = width / 4;
        }
//
//        Path path = new Path();
//        path.addCircle(width / 2,
//                height / 2, radius,
//                Path.Direction.CW);


        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(50);
        paint.setStyle(Paint.Style.FILL);

        float center_x, center_y;
        final RectF oval = new RectF();
        paint.setStyle(Paint.Style.STROKE);

        center_x = width / 2;
        center_y = height / 2;


        oval.set(center_x - radius,
                center_y - radius,
                center_x + radius,
                center_y + radius);

        int cores [] = {Color.parseColor("#ea043a"), Color.parseColor("#d0011b")};

        Shader shader = new SweepGradient(width / 2, height / 2, cores, null);
        paint.setShader(shader);
        canvas.drawCircle(width / 2, height / 2, radius, paint);
//        canvas.drawArc(oval, 180, 180, false, paint);

    }
}

package jogos2d.alura.com.br.jogos2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hamilton on 20/10/16.
 */

public class EstudoView extends View {


    public EstudoView(Context context) {
        super(context);
    }

    public EstudoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void desenhaRetangulo(Canvas canvas){
        Paint paint = new Paint();
        // canvas.drawRect(x1, y1, x2, y2, paint); // pontos da hipotenusa
        canvas.drawRect(300, 300, 400, 400 , paint);
        paint.setColor(Color.WHITE);
        canvas.drawLine(300, 300, 400, 400 , paint);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE); // desenha somente a linha
        for(int i=0; i<=20;i++){
            canvas.drawRect(300+(i*20*-1),
                    300+(i*20*-1),
                    400+(i*20),
                    400+(i*20),
                    paint);
        }


    }
    private void desenhaLinha(Canvas canvas){
        //        canvas.drawLine(x1, y1, x2, y2, new Paint());
        Paint paint = new Paint();
        paint.setStrokeWidth(0); // largura da linha
        paint.setColor(Color.RED);
        for(int i = 1; i <=20; i++){
            paint.setStrokeWidth(i);
            canvas.drawLine(50, i * 20, 10, i * 20 , paint);
        }

    }

    private void desenhaCirculo(Canvas canvas){
        //        canvas.drawLine(x1, y1, x2, y2, new Paint());
        Paint paint = new Paint();
        paint.setStrokeWidth(2); // largura da linha
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(100, 100, 100, paint);

        // desenha linhas
        paint.setColor(Color.YELLOW);
        canvas.drawLine(0, 100, 100, 100, paint);
        canvas.drawLine(100, 100, 100, 0 , paint);
    }

    private void desenhaAlvo(Canvas canvas){
        //        canvas.drawLine(x1, y1, x2, y2, new Paint());
        Paint paint = new Paint();
        paint.setStrokeWidth(2); // largura da linha
        paint.setColor(Color.rgb(100, 0, 0));
        for(int i = 5; i > 0; i--){
            paint.setColor(Color.rgb(i*30, 50, 50));
            canvas.drawCircle(200, 200, i*30, paint);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        desenhaLinha(canvas);
//        desenhaRetangulo(canvas);
//        desenhaCirculo(canvas);
        desenhaAlvo(canvas);

    }
}

package jogodaforca.alura.com.br.jogodaforca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hamilton on 25/10/16.
 */

public class PlanoCartesiano extends View {

    // armazena o menor lado do nosso display
    // caso o display seja maior na vertical, a variavel guardara este valor,
    // caso contrario sera guardado o valor da dimensão da nosssa tela na horizontal
    private int menorLadoDisplay = 0;
    private int unidade = 0;
    private boolean desenhaPlanoCartesiano = false;

    public PlanoCartesiano(Context context) {
        super(context);
    }

    public PlanoCartesiano(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(getHeight() > getWidth()){
            setMenorLadoDisplay(getWidth());
        }else{
            setMenorLadoDisplay(getHeight());
        }
        setUnidade(getMenorLadoDisplay() / 10);

        if(isDesenhaPlanoCartesiano()){
            drawPlanoCartesiano(canvas);
        }
    }

    private void drawPlanoCartesiano(Canvas canvas) {

        Path path = new Path();
        int max = toPixel(10);
        for(int i = 0; i<=10; i++){
            // desenha linhas verticais
            path.moveTo(toPixel(i), 1);
            path.lineTo(toPixel(i), max);
            // desenha linhas horizontais
            path.moveTo(1, toPixel(i));
            path.lineTo(max, toPixel(i));
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true); // propriedade que define a suavidade da linha
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawPath(path, paint);

    }

    protected int toPixel(int vezes) {
        return vezes * getUnidade();
    }

    public int getMenorLadoDisplay() {
        return menorLadoDisplay;
    }
    public void setMenorLadoDisplay(int menorLadoDisplay) {
        this.menorLadoDisplay = menorLadoDisplay;
    }
    public int getUnidade() {
        return unidade;
    }
    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    public boolean isDesenhaPlanoCartesiano() {
        return desenhaPlanoCartesiano;
    }

    public void setDesenhaPlanoCartesiano(boolean desenhaPlanoCartesiano) {
        this.desenhaPlanoCartesiano = desenhaPlanoCartesiano;
    }
}

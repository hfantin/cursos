package br.com.jumper.element;

import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.jumper.engine.Som;
import br.com.jumper.util.Cores;

/**
 * Created by hamilton on 17/11/16.
 */

public class Pontuacao {

    private static final Paint BRANCO = Cores.getCorDaPontuacao() ;
    private int pontos = 0;
    private Som som;

    public Pontuacao(Som som) {
        this.som = som;
    }

    public void aumenta() {
        som.play(Som.PONTUACAO);
        pontos ++;
    }

    public void desenhaNo(Canvas canvas){

        canvas.drawText(String.valueOf(pontos), 100, 100, BRANCO);

    }
}

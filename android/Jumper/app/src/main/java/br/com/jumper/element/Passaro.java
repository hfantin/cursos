package br.com.jumper.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.jumper.activity.R;
import br.com.jumper.engine.Som;
import br.com.jumper.graficos.Tela;
import br.com.jumper.util.Cores;

/**
 * Created by hamilton on 10/11/16.
 */

public class Passaro {

    private static final Paint vermelho = Cores.getCorDoPassaro();
    public static final  int X = 100;
    public static final  int RAIO = 50;
    private final Tela tela;
    private Som som;
    private final Bitmap passaro;
    private int altura;

    public Passaro(Tela tela, Context context, Som som) {
        this.tela = tela;
        this.som = som;
        this.altura = 100;
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.shit);
        this.passaro = Bitmap.createScaledBitmap(bp, RAIO*2, RAIO*2 , false);

    }

    public void desenhaNo(Canvas canvas){
//        canvas.drawCircle(X, altura, RAIO, vermelho);
        canvas.drawBitmap(passaro, X - RAIO, altura - RAIO, null);
    }

    public void cai() {
        // Define base
        boolean chegouNoChao = altura + RAIO > tela.getAltura();
        // deve incrementar para mover o passaro para baixo pois o eixo y = 0 esta no canto superior da tela
        if(!chegouNoChao){
            this.altura += 5;
        }
    }

    public void pula(){
        //verifica topo
        if(altura > RAIO){
            som.play(Som.PULO);
            this.altura -=150;
        }
    }

    public int getAltura() {
        return altura;
    }
}

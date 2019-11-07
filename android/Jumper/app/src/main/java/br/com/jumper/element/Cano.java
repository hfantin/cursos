package br.com.jumper.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import br.com.jumper.activity.R;
import br.com.jumper.graficos.Tela;
import br.com.jumper.util.Cores;

/**
 * Created by hamilton on 16/11/16.
 */

public class Cano {


    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;
    private final Tela tela;
    private final int alturaDoCanoInferior;
    private final Bitmap canoInferior;
    private final Bitmap canoSuperior;
    private Context context;
    private final int alturaDoCanoSuperior;
    private int posicao;

    public Cano(Tela tela, int posicao, Context context) {
        this.tela = tela;
        this.posicao = posicao;
        this.context = context;
        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior = TAMANHO_DO_CANO + valorAleatorio();
        Log.i("Cano", "Cano - alturaDoCanoInferior=" + alturaDoCanoInferior + ", alturaDoCanoSuperior=" + alturaDoCanoSuperior + ", valorAleatorio=" +  valorAleatorio());
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        this.canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaDoCanoInferior, false);
        this.canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaDoCanoSuperior, false);
    }

    private int valorAleatorio() {
        return (int) (Math.random() * 150);
    }

    public void desenhaNo(Canvas canvas) {
        desenhaCanoInferiorNo(canvas);
        desenhaCanoSuperiorrNo(canvas);
    }

    private void desenhaCanoInferiorNo(Canvas canvas) {
//        canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), Cores.getCorDoCano());
        canvas.drawBitmap(canoInferior, posicao, alturaDoCanoInferior, null);
    }

    private void desenhaCanoSuperiorrNo(Canvas canvas) {
//        canvas.drawRect(posicao, 0, posicao + LARGURA_DO_CANO, alturaDoCanoSuperior, Cores.getCorDoCano());
        canvas.drawBitmap(canoSuperior, posicao, 0, null);
    }

    public void move() {
        this.posicao -= 5;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean saiuDaTela() {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public boolean temColisaoHorizontalCom(Passaro passaro) {
        return this.posicao - Passaro.X < Passaro.RAIO;
    }

    public boolean temColisaoVerticalCom(Passaro passaro) {
        return passaro.getAltura() - Passaro.RAIO < this.alturaDoCanoSuperior
            || passaro.getAltura() + Passaro.RAIO > this.alturaDoCanoInferior;

    }
}

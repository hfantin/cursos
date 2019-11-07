package br.com.jumper.element;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import br.com.jumper.graficos.Tela;

/**
 * Created by hamilton on 16/11/16.
 */

public class Canos {

    private static final int QUANTIDADE_DE_CANOS = 5;
    private static final int DISTANCIA_ENTRE_CANOS = 250;
    private final Tela tela;
    private final Pontuacao pontuacao;
    private List<Cano> canos = new ArrayList<Cano>();
    private Context context;

    public Canos(Tela tela, Pontuacao pontuacao, Context context) {
        this.context = context;
        int posicaoInicial = tela.getLargura();
        this.tela = tela;
        this.pontuacao = pontuacao;
        for(int i=0; i<QUANTIDADE_DE_CANOS; i++) {
            posicaoInicial += DISTANCIA_ENTRE_CANOS;
            canos.add(new Cano(tela, posicaoInicial, context));
        }
    }

    public void move() {
        // é necessário usar iterator para evitar o java.util.ConcurrentModificationException, pois a lista é alterada dentro do loop
        ListIterator<Cano> iterator = canos.listIterator();
        while(iterator.hasNext()){
            Cano cano = (Cano) iterator.next();
            cano.move();
            if(cano.saiuDaTela()){
                pontuacao.aumenta();
                iterator.remove();
                Cano outroCano = new Cano(tela, getMaximo() + DISTANCIA_ENTRE_CANOS, context);
                iterator.add(outroCano);
            }
        }
    }


    public void desenhaNo(Canvas canvas) {
        for(Cano cano : canos)
            cano.desenhaNo(canvas);
    }

    public int getMaximo() {
        int maximo = 0;
        for(Cano cano : canos){
            maximo = Math.max(cano.getPosicao(), maximo);
        }
        return maximo;
    }

    public boolean temColisaoCom(Passaro passaro) {
        for (Cano cano: canos) {
            if(cano.temColisaoHorizontalCom(passaro) && cano.temColisaoVerticalCom(passaro)){
                return true;
            }
        }
        return false;
    }
}

package br.com.jumper.engine;

import br.com.jumper.element.Canos;
import br.com.jumper.element.Passaro;

/**
 * Created by hamilton on 17/11/16.
 */
public class VerificadorDeColisao {

    private Passaro passaro;
    private Canos canos;

    public VerificadorDeColisao(Passaro passaro, Canos canos) {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao() {
        return canos.temColisaoCom(passaro);
    }
}

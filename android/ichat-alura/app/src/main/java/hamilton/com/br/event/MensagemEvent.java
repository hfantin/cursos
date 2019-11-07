package hamilton.com.br.event;

import hamilton.com.br.model.Mensagem;

/**
 * Created by hamilton on 22/02/17.
 */

public class MensagemEvent {

    public Mensagem mensagem;

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }


}

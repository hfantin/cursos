package hamilton.com.br.interruptor.event;

import hamilton.com.br.interruptor.model.Rele;
import hamilton.com.br.interruptor.model.ReleDTO;

/**
 * Created by hamilton on 22/02/17.
 */

public class AlterarReleEvent {

    public Rele rele;


    public AlterarReleEvent(Rele rele) {
        this.rele = rele;
    }


}

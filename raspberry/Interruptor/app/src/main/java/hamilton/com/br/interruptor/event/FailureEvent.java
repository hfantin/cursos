package hamilton.com.br.interruptor.event;

/**
 * Created by hamilton on 22/02/17.
 */
public class FailureEvent {

    public String mensagem;
    public FailureEvent(String mensagem) {
        this.mensagem = mensagem;
    }
}

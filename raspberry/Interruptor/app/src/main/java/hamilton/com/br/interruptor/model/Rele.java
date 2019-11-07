package hamilton.com.br.interruptor.model;

import java.io.Serializable;

/**
 * Created by hamilton on 27/03/17.
 */

public class Rele implements Serializable{

    private int id;
    private boolean ligado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    @Override
    public String toString() {
        return "Rele{" +
                "id=" + id +
                ", ligado=" + ligado +
                '}';
    }
}

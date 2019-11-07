package hamilton.com.br.interruptor.model;

import java.util.List;

/**
 * Created by hamilton on 27/03/17.
 */

public class ReleDTO {

    private List<Rele> reles;

    public List<Rele> getReles() {
        return reles;
    }

    public void setReles(List<Rele> reles) {
        this.reles = reles;
    }

    @Override
    public String toString() {
        return "ReleDTO{" +
                "reles=" + reles +
                '}';
    }
}

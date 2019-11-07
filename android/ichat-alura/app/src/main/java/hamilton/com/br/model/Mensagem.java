package hamilton.com.br.model;


import java.io.Serializable;

/**
 * Created by hamilton on 03/02/17.
 */
public class Mensagem implements Serializable{

    private Integer id;
    private String text;


    public Mensagem(Integer id,String text) {
        this.id = id;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}

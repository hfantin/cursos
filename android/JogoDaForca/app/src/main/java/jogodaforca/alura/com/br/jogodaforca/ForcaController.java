package jogodaforca.alura.com.br.jogodaforca;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hamilton on 28/10/16.
 */

public class ForcaController {

    private String palavra; // palavra para ser adivinhada
    private Set<Character> letrasUsadas; // lista que conterá as letras que já foram usadas
    private int quantidadeErros = -1;


    public ForcaController(final String palavra) {
        this.palavra = palavra;
        this.letrasUsadas = new HashSet<>();
        this.quantidadeErros = -1;
    }

    public int getQuantidadeErros() {
        return quantidadeErros;
    }

    public void joga(Character letra) throws Exception{
        if( letrasUsadas.contains( letra ) )//caso o Set contenha a letra jogada, saimos da função
            throw new Exception("Você já usou a letra \'" + letra +"\'");
//            return;//sai da função
        letrasUsadas.add(letra);//Como a letra não foi jogada, então adiciona à lista

        if ( palavra.contains(letra.toString()) )//Verificamos se a string letra contém a letra a ser jogada
            return;//Se a lista contiver a letra a ser jogada, saímos da função

        quantidadeErros++;
        //Como a letra não foi utilizada ainda e também, a mesma não consta na palavraParaAdvinhar
        //acrescentamos uma unidade na nossa variavel que controla o numero de erros
    }

    public String getPalavraAteAgora(){
        String visualizacao = "";
        for(char c : palavra.toCharArray()){
            if(letrasUsadas.contains(c)){
                visualizacao += c;
            }else{
                visualizacao += "_";
            }
        }
        return visualizacao;
    }

    public boolean isMorreu(){
        return getQuantidadeErros() == 5;
    }

    public boolean isGanhou(){
        return !getPalavraAteAgora().contains("_");
    }

    public boolean isTerminou(){
        return isGanhou() || isMorreu();
    }

    public Set<Character> getLetrasUsadas() {
        return letrasUsadas;
    }
}

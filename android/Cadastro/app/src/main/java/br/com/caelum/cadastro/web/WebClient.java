package br.com.caelum.cadastro.web;

import android.util.Log;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Hamilton on 14/09/16.
 *
 * Bibliotecas:
 * Retrofit
 * Volley
 */
public class WebClient {

    public static String URL_CAELUM = "https://www.caelum.com.br/mobile";
    public static String METODO_POST = "POST";
    public static String TIPO_JSON = "application/json";

    public String doPost(java.lang.String json) {
        String resposta = null;
        try {
            URL url = new URL(URL_CAELUM);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(METODO_POST);
            con.setRequestProperty("Content-type", TIPO_JSON);
            con.setRequestProperty("Accept", TIPO_JSON);

            // informa que está enviando dados
            con.setDoInput(true);
            // informa que está recebendo dados
            con.setDoOutput(true);

            // escreve no body da requisição
            PrintStream saida = new PrintStream(con.getOutputStream());
            saida.print(json);

            // conecta ao servidor
            con.connect();


            // recupera a resposta
            Scanner entrada= new Scanner(con.getInputStream());

            if(entrada.hasNext()){
                resposta = entrada.next();
            }else{
                resposta = "http 204";
            }
        } catch (IOException e) {
            Log.e("WebClient", "Erro: ", e);
        }
        return resposta;

    }
}

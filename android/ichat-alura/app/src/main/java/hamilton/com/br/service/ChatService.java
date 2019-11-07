package hamilton.com.br.service;

import android.util.Log;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import hamilton.com.br.activity.MainActivity;
import hamilton.com.br.model.Mensagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by hamilton on 06/02/17.
 */
public interface ChatService  {


    @POST("polling")
    Call<Void> enviar(@Body Mensagem mensagem);

    @GET("polling")
    Call<Mensagem> ouvirMensagens();

}

package br.com.caelum.fj59.carangos;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.tasks.RegistraAparelhoTask;

/**
 * Created by Hamilton on 19/09/16.
 */

public class CarangosApplication extends Application {

    private List<AsyncTask> tasks = new ArrayList<>();
    public List<Publicacao> publicacoes = new ArrayList<>();

    private static final String REGISTRADO = "registradoNoGcm";
    private static final String ID_DO_REGISTRO = "idDoRegistro";
    private SharedPreferences preferences;

    public void registra(AsyncTask task) {
        MyLog.i("CarangosApplication.registra task " + task.getClass().getSimpleName());
        tasks.add(task) ;
    }

    public void desregistra(AsyncTask task) {
        MyLog.i("CarangosApplication.desregistra task " + task.getClass().getSimpleName());
        tasks.remove(task);
    }


    public List<Publicacao> getPublicacoes() {
        MyLog.i("CarangosApplication.getPublicacoes: " + publicacoes.size());
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        MyLog.i("CarangosApplication.setPublicacoes");
        this.publicacoes = publicacoes;
    }

    public void matarTudo(){
        MyLog.i("CarangosApplication.matarTudo");
        for(AsyncTask t : tasks){
            t.cancel(true); // kill -9
        }
    }


    @Override
    public void onCreate() {
        MyLog.i("onCreate do app");
        super.onCreate();
        preferences = getSharedPreferences("configs", Activity.MODE_PRIVATE);
        registraNoGcm();
    }

    public void registraNoGcm(){
        MyLog.i("onCreate do registra");
        if(!usuarioRegistrado()){
            new RegistraAparelhoTask(this).execute();
        }else{
            MyLog.i("Aparelho já cadastrado! Seu id é: " + preferences.getString(ID_DO_REGISTRO, null));
        }
    }
    private boolean usuarioRegistrado(){
        return preferences.getBoolean(REGISTRADO, false);
    }

    public void lidaComRespostaDoRegistroNoServidor(String registro){
        MyLog.i("lidaComRespostaDoRegistroNoServidor=" + registro);
        if(registro != null){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(REGISTRADO, true);
            editor.putString(ID_DO_REGISTRO, registro);
            editor.commit();
        }
    }
}

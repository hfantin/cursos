package br.com.caelum.fj59.carangos.activity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.fj59.carangos.CarangosApplication;
import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.Tela;
import br.com.caelum.fj59.carangos.eventos.EventoPublicacoesRecebidas;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.navegacao.EstadoMainActivity;
import br.com.caelum.fj59.carangos.tasks.BuscaMaisPublicacoesTask;
import br.com.caelum.fj59.carangos.tasks.BuscarMaisPublicacoesDelegate;
import br.com.caelum.fj59.carangos.tasks.BuscaLeiloesTask;

public class MainActivity extends Tela implements BuscarMaisPublicacoesDelegate {
    private static final String ESTADO_ATUAL = "ESTADO_ATUAL";

    private EstadoMainActivity estado;
    //guardando o evento como atributo
    private EventoPublicacoesRecebidas evento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyLog.i("MainActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.estado = EstadoMainActivity.INICIO;
        //registra o activity como observador
        this.evento = EventoPublicacoesRecebidas.registraObservador(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLog.i("MainActivity.onResume");
        this.estado.executa(this);
    }

    public void buscarPublicacoes(){
        MyLog.i("MainActivity.buscarPublicacoes");
        new BuscaMaisPublicacoesTask(getCarangosApplication()).execute();
    }

    public void alteraEstadoEExecuta(EstadoMainActivity estado){
        this.estado = estado;
        this.estado.executa(this);
    }

    @Override
    public void lidaComRetorno(List<Publicacao> resultado) {
        MyLog.i("MainActivity.lidaComRetorno");
        CarangosApplication application = (CarangosApplication)getApplication();
        List<Publicacao> publicacoes  = application.getPublicacoes();
        publicacoes.clear();
        publicacoes.addAll(resultado);
        this.estado = EstadoMainActivity.PRIMEIRAS_PUBLICACOES_RECEBIDAS;
        this.estado.executa(this);

    }

    @Override
    public void lidaComErro(Exception e) {
        MyLog.i("MainActivity.lidaComErro");
        e.printStackTrace();
        Toast.makeText(this, R.string.erroBuscarDados, Toast.LENGTH_LONG).show();
    }

    @Override
    public CarangosApplication getCarangosApplication() {
        MyLog.i("MainActivity.getCarangosApplication");
        return (CarangosApplication) getApplication();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MyLog.i("MainActivity.onSaveInstanceState");
        outState.putSerializable(ESTADO_ATUAL, this.estado);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        MyLog.i("MainActivity.onRestoreInstanceState");
        this.estado = (EstadoMainActivity) savedInstanceState.getSerializable(ESTADO_ATUAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLog.i("MainActivity.onDestroy");
        this.evento.desregistra(getCarangosApplication());
    }
}

package br.com.caelum.fj59.carangos.eventos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.fj59.carangos.CarangosApplication;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.tasks.BuscarMaisPublicacoesDelegate;

/**
 * Created by Hamilton on 21/09/16.
 */

public class EventoPublicacoesRecebidas extends BroadcastReceiver {

    private static final String RETORNO = "retorno";
    private static final String SUCESSO = "sucesso";
    private static final String PUBLICACOES_RECEBIDAS = "publicacoes_recebidas";

    private BuscarMaisPublicacoesDelegate delegate;

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean deuCerto = intent.getBooleanExtra(SUCESSO, false);
        MyLog.i("EventoPublicacoesRecebidas.onReceive() - deuCerto? " + deuCerto);
        if(deuCerto){
            MyLog.i("--> lidaComRetorno");
            delegate.lidaComRetorno((List<Publicacao>) intent.getSerializableExtra(RETORNO));
        }else{
            MyLog.i("--> lidaComErro");
            delegate.lidaComErro((Exception) intent.getSerializableExtra(RETORNO));
        }

    }

    public static EventoPublicacoesRecebidas registraObservador(BuscarMaisPublicacoesDelegate delegate){
        MyLog.i("EventoPublicacoesRecebidas.registraObservador()");
        EventoPublicacoesRecebidas receiver = new EventoPublicacoesRecebidas();
        receiver.delegate = delegate;
        LocalBroadcastManager.getInstance(delegate.getCarangosApplication())
                .registerReceiver(receiver, new IntentFilter(PUBLICACOES_RECEBIDAS));
        return receiver;
    }

    public void desregistra(CarangosApplication application){
       MyLog.i("EventoPublicacoesRecebidas.desregistra()");
       LocalBroadcastManager.getInstance(application).unregisterReceiver(this);
    }

    public static void notifica(Context context , Serializable resultado, boolean sucesso){
        MyLog.i("EventoPublicacoesRecebidas.notifica()");
        Intent intent = new Intent(PUBLICACOES_RECEBIDAS);
        intent.putExtra(RETORNO, resultado);
        intent.putExtra(SUCESSO, sucesso);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}

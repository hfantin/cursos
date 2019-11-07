package br.com.caelum.fj59.carangos.tasks;

import android.os.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import br.com.caelum.fj59.carangos.handler.LeilaoHandler;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.webservice.WebClient;

/**
 * Created by Hamilton on 23/09/16.
 */

public class BuscaLeiloesTask extends TimerTask {


//    private Timer t;
    private static final int TEMPO_REEXECUTAR = 30 * 1000;
    private Calendar horarioUltimaBusca;
    private LeilaoHandler handler;
   

    public BuscaLeiloesTask(LeilaoHandler handler, Calendar horarioUltimaBusca) {
        this.horarioUltimaBusca = horarioUltimaBusca;
        this.handler = handler;
    }

    @Override
    public void run() {
        MyLog.i("Efetuando nova busca de leiloes...");
        WebClient webClient = new WebClient("leilao/leilaoid54635/" + new SimpleDateFormat("ddMMyy-HHmmss").format(horarioUltimaBusca.getTime()));
        String json = webClient.get();
        MyLog.i("Lances recebidos: " + json);
        Message message = handler.obtainMessage();
        message.obj = json;
        handler.sendMessage(message);
        horarioUltimaBusca = Calendar.getInstance();
    }

    public void executar(){
        Timer t = new Timer();
        t.schedule(this, 0, TEMPO_REEXECUTAR);
    };

//    public void cancelar(){
//        t.cancel();
//        t.purge();
//    };
}

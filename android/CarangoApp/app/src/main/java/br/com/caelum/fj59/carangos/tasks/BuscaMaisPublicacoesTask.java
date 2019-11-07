package br.com.caelum.fj59.carangos.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.fj59.carangos.CarangosApplication;
import br.com.caelum.fj59.carangos.converter.PublicacaoConverter;
import br.com.caelum.fj59.carangos.eventos.EventoPublicacoesRecebidas;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.webservice.Pagina;
import br.com.caelum.fj59.carangos.webservice.WebClient;

/**
 * Created by erich on 7/16/13.
 */
public class BuscaMaisPublicacoesTask extends AsyncTask<Pagina, Void, List<Publicacao>> {

    private Exception erro;
    private CarangosApplication application;


    public BuscaMaisPublicacoesTask(CarangosApplication application) {
        this.application = application;
        this.application.registra(this);
    }

    @Override
    protected List<Publicacao> doInBackground(Pagina... paginas) {
        MyLog.i("BuscaMaisPublicacoesTask.doInBackground");
        try {
            Pagina paginaParaBuscar = paginas.length > 1? paginas[0] : new Pagina();
            String jsonDeResposta = new WebClient("post/list?" + paginaParaBuscar).get();
            List<Publicacao> publicacoesRecebidas = new PublicacaoConverter().converte(jsonDeResposta);
            MyLog.i("--> doInBackground retorno publicacoes");

//            Thread.sleep(4000);
            return publicacoesRecebidas;
        } catch (Exception e) {
            MyLog.i("--> doInBackground erro " + e.getMessage());
            e.printStackTrace();
            this.erro = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Publicacao> retorno) {
        MyLog.i("BuscaMaisPublicacoesTask.onPostExecute() - retorno nulo? " + retorno == null);
        if (retorno!=null) {
            MyLog.i("-->  notifica com retorno");
            EventoPublicacoesRecebidas.notifica(this.application, (Serializable) retorno, true);
        } else {
            MyLog.i("-->  notifica com erro");
            EventoPublicacoesRecebidas.notifica(this.application, this.erro, false);
        }
        this.application.desregistra(this);
    }
}

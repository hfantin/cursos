package br.com.caelum.fj59.carangos.navegacao;

import android.app.Fragment;
import android.app.FragmentTransaction;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.activity.MainActivity;
import br.com.caelum.fj59.carangos.fragments.ListaDePublicacoesFragment;
import br.com.caelum.fj59.carangos.fragments.ProgressFragment;
import br.com.caelum.fj59.carangos.infra.MyLog;

/**
 * Created by android6345 on 20/09/16.
 */

public enum EstadoMainActivity {

    INICIO{
        @Override
        public void executa(MainActivity activity) {
            MyLog.i("EstadoMainActivity.INICIO.executar");
            activity.buscarPublicacoes();
            activity.alteraEstadoEExecuta(EstadoMainActivity.AGUARDANDO_PUBLICACOES);
        }
    },
    AGUARDANDO_PUBLICACOES{
        @Override
        public void executa(MainActivity activity) {
            MyLog.i("EstadoMainActivity.AGUARDANDO_PUBLICACOES.executar");
            ProgressFragment progressFragment = ProgressFragment.comMensagem(R.string.carregando);
            this.colocaFragmentNaTela(activity, progressFragment);
        }
    },
    PRIMEIRAS_PUBLICACOES_RECEBIDAS{
        @Override
        public void executa(MainActivity activity) {
            MyLog.i("EstadoMainActivity.PRIMEIRAS_PUBLICACOES_RECEBIDAS.executar");
            ListaDePublicacoesFragment listaDePublicacoesFragment = new ListaDePublicacoesFragment();
            this.colocaFragmentNaTela(activity, listaDePublicacoesFragment);
        }
    },
    PULL_TO_REFRESH_REQUISITADO{
        @Override
        public void executa(MainActivity activity) {
            MyLog.i("EstadoMainActivity.PULL_TO_REFRESH_REQUISITADO.executar");
            activity.buscarPublicacoes();
        }
    };

    void colocaFragmentNaTela(MainActivity activity, Fragment fragment){
        MyLog.i("EstadoMainActivity.colocaFragmentNaTela");
        FragmentTransaction tx = activity.getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_principal, fragment);
        tx.commit();
    }

    public abstract void executa(MainActivity activity);

}

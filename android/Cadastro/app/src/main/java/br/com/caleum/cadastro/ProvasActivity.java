package br.com.caleum.cadastro;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.caleum.cadastro.constantes.Constantes;
import br.com.caleum.cadastro.dominio.Prova;

public class ProvasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);
        FragmentTransaction transacao = getSupportFragmentManager().beginTransaction();
        transacao.replace(R.id.provas_view, new ListaProvasFragment());
        if(isTablet()){
            transacao.replace(R.id.detalhes_view, new DetalhesProvaFragment());
        }
        transacao.commit();
    }

    private boolean isTablet(){
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void selecionaProva(Prova provaSelecionada){

        FragmentManager manager = getSupportFragmentManager();
        if(isTablet()){
            DetalhesProvaFragment detalhes = (DetalhesProvaFragment) manager.findFragmentById(R.id.detalhes_view);
            detalhes.populaCampos(provaSelecionada);
        }else{
            DetalhesProvaFragment detalhes = new DetalhesProvaFragment();
            Bundle args = new Bundle();
            args.putSerializable(Constantes.PROVA_SELECIONADA, provaSelecionada);
            detalhes.setArguments(args);
            FragmentTransaction transacao = manager.beginTransaction();
            transacao.replace(R.id.provas_view, detalhes);
            transacao.addToBackStack(null);
            transacao.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}

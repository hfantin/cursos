package br.com.caleum.cadastro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.caleum.cadastro.constantes.Constantes;
import br.com.caleum.cadastro.dominio.Prova;

/**
 * Created by Hamilton on 14/09/16.
 */
public class DetalhesProvaFragment extends Fragment {

    private Prova prova;
    private TextView detalheProvaMateria;
    private TextView detalheProvaData;
    private ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("DetalhesProvaFragment", "onCreateView");
        View layoutDetalhesProva = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);
        if(getArguments() != null){
            prova = (Prova) getArguments().getSerializable(Constantes.PROVA_SELECIONADA);
        }
        buscarCampos(layoutDetalhesProva);
        populaCampos(prova);
        return layoutDetalhesProva;
    }

    private void buscarCampos(View layout){
        detalheProvaMateria = (TextView) layout.findViewById(R.id.detalhe_prova_materia);
        detalheProvaData = (TextView) layout.findViewById(R.id.detalhe_prova_data);
        lista = (ListView) layout.findViewById(R.id.detalhe_prova_topicos);
    }

    public void populaCampos(Prova provaSelecionada) {
        Log.i("DetalhesProvaFragment", "populaCampos " + provaSelecionada );
        if(provaSelecionada != null){
            this.detalheProvaMateria.setText(provaSelecionada.getMateria());
            this.detalheProvaData.setText(provaSelecionada.getData());
            this.lista.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, provaSelecionada.getTopicos()));
        }
    }
}

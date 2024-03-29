package br.com.caleum.cadastro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caleum.cadastro.dominio.Prova;

public class ListaProvasFragment extends Fragment {

    private ListView listViewProvas;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflando o XML do layout do nosso fragment
        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        // Recupera a referência para a ListView do fragment
        this.listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas_listview);

        // Gerando as provas que irão popular nossa lista
        Prova prova1 = new Prova("20/06/2015", "Estruturas de Dados");
        prova1.setTopicos(Arrays.asList("Pilhas", "Filas"));

        Prova prova2 = new Prova("25/07/2015", "Matemática Financeira");
        prova2.setTopicos(Arrays.asList("Juros Simples", "Juros Compostos", "Amortização"));

        Prova prova3 = new Prova("30/07/2015", "Linguagem C");
        prova3.setTopicos(Arrays.asList("Blocos de decisão", "Loops", "Ponteiros"));

        Prova prova4 = new Prova("31/07/2015", "Android");
        prova4.setTopicos(Arrays.asList("Activities", "SQLite", "Async Task", "Fragments"));

        final List<Prova> provas = Arrays.asList(prova1, prova2, prova3, prova4);

        // Populando a lista com um ArrayAdapter que recebe a lista de provas
        this.listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas));

        // Listener para o evento de clique mostrar a prova selecionada
        this.listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Prova selecionada = (Prova) adapter.getItemAtPosition(posicao);
                ((ProvasActivity) getActivity()).selecionaProva(selecionada);
//              Toast.makeText(getActivity(), "Prova selecionada: " + selecionada, Toast.LENGTH_LONG).show();
            }
        });

        return layoutProvas;
    }
}
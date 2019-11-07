package com.androidteste.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.androidteste.R;
import com.androidteste.activity.ListasActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Searchview com databinding
 * https://www.journaldev.com/12478/android-searchview-example-tutorial
 * https://www.journaldev.com/11780/android-data-binding
 * Created by hamilton on 21/09/17.
 */

public class Lista1Fragment extends Fragment implements  SearchView.OnQueryTextListener {

    private ListView lista;
    private Button botao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista1, container, false);
        botao = (Button)view.findViewById(R.id.btn_ok);
        lista = (ListView) view.findViewById(R.id.lista_clientes1);

        List<String> clientes = new ArrayList<>();
        for(int i=0; i< 10; i++){
            clientes.add("Cliente " + i);
        }

        lista.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, clientes));

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListasActivity) getActivity()).navegar();
            }
        });
        return view;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

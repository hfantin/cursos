package com.androidteste.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.androidteste.R;
import com.androidteste.databinding.FragmentLista2Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamilton on 21/09/17.
 */

public class Lista2Fragment extends Fragment {

    FragmentLista2Binding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lista2, container, false);
        View view = binding.getRoot();
//        View view = inflater.inflate(R.layout.fragment_lista2, container, false);
//        ListView lista = (ListView) view.findViewById(R.id.lista_clientes2);
        List<String> filhos = new ArrayList<>();
        for(int i=0; i< 10; i++){
            filhos.add("Filhos " + i);
        }
        binding.listaClientes2.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, filhos));
        return view;
    }
}

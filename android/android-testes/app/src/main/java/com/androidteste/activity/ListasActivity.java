package com.androidteste.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.androidteste.R;
import com.androidteste.databinding.ActivityListasBinding;
import com.androidteste.fragments.Lista1Fragment;
import com.androidteste.fragments.Lista2Fragment;

/**
 * Created by hamilton on 21/09/17.
 */

public class ListasActivity extends AppCompatActivity {

    ActivityListasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listas);
        FragmentTransaction transacao = getSupportFragmentManager().beginTransaction();
        transacao.replace(R.id.listas_view, new Lista1Fragment());
        transacao.commit();
    }


    public void navegar() {
        Lista2Fragment detalhes = new Lista2Fragment();
//                Bundle args = new Bundle();
//                args.putSerializable(Constantes.PROVA_SELECIONADA, provaSelecionada);
//                detalhes.setArguments(args);
        FragmentTransaction transacao = getSupportFragmentManager().beginTransaction();
        transacao.replace(R.id.listas_view, detalhes);
        transacao.addToBackStack(null);
        transacao.commit();
    }
}

package br.com.caelum.fj59.carangos.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.caelum.fj59.carangos.CarangosApplication;
import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.activity.MainActivity;
import br.com.caelum.fj59.carangos.adapter.PublicacaoAdapter;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.navegacao.EstadoMainActivity;

/**
 * Created by erich on 9/11/13.
 */
public class ListaDePublicacoesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    private SwipeRefreshLayout swipe;
    private ListView publicacoesList;
    private  PublicacaoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.swipe = (SwipeRefreshLayout) inflater.inflate(R.layout.publicacoes_list, container, false);
//                ListView publicacoesList = (ListView) inflater.inflate(R.layout.publicacoes_list, container, false);
        this.swipe.setOnRefreshListener(this);
        this.swipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        this.publicacoesList = (ListView) this.swipe.findViewById(R.id.publicacoes_list);
        MainActivity activity = (MainActivity) this.getActivity();
        CarangosApplication app = activity.getCarangosApplication();
        this.adapter = new PublicacaoAdapter(getActivity(), app.getPublicacoes());
        this.publicacoesList.setAdapter(this.adapter);
        return this.swipe;
    }

    @Override
    public void onRefresh() {
        MyLog.i("refresh iniciado");
        ((MainActivity) this.getActivity()).alteraEstadoEExecuta(EstadoMainActivity.PULL_TO_REFRESH_REQUISITADO);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.swipe.setRefreshing(false);
        this.swipe.clearAnimation();
    }
}

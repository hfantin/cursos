package br.com.caelum.fj59.carangos.handler;


import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;

import java.util.Collections;
import java.util.List;

import br.com.caelum.fj59.carangos.converter.LanceConverter;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Lance;

/**
 * Created by Hamilton on 23/09/16.
 */

public class LeilaoHandler extends Handler {

    private ArrayAdapter<Lance> adapter;
    private List<Lance> lancesAteMomento;

    public LeilaoHandler(ArrayAdapter<Lance> adapter, List<Lance> lancesAteMomento) {
        this.adapter = adapter;
        this.lancesAteMomento = lancesAteMomento;
    }

    @Override
    public void handleMessage(Message msg) {
        MyLog.i("LeilaoHandler");
        String json = (String) msg.obj;
        List<Lance> novosLances = new LanceConverter().converte(json);
        lancesAteMomento.addAll(novosLances);
        adapter.notifyDataSetChanged();
        Collections.sort(lancesAteMomento);

    }
}

package br.com.caelum.fj59.carangos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.handler.LeilaoHandler;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Lance;
import br.com.caelum.fj59.carangos.tasks.BuscaLeiloesTask;

/**
 * Created by Hamilton on 23/09/16.
 */

public class LeilaoActivity extends Activity {

    private List<Lance> lancesAteMomento= new ArrayList<>();
    private Calendar horarioUltimaBusca = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.i("LeilaoActivity.onCreate");
        setContentView(R.layout.leilao);
        ListView lancesList = (ListView)findViewById(R.id.lances_list);
        ArrayAdapter<Lance> adapter = new ArrayAdapter<Lance>(this, android.R.layout.simple_list_item_1, lancesAteMomento);
        lancesList.setAdapter(adapter);
        LeilaoHandler handler = new LeilaoHandler(adapter, lancesAteMomento);
        new BuscaLeiloesTask(handler, horarioUltimaBusca).executar();

    }
}

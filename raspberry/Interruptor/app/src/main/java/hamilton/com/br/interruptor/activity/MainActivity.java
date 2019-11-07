package hamilton.com.br.interruptor.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hamilton.com.br.interruptor.R;
import hamilton.com.br.interruptor.adapter.ReleAdapter;
import hamilton.com.br.interruptor.app.InterruptorApplication;
import hamilton.com.br.interruptor.callback.ListarRelesCallback;
import hamilton.com.br.interruptor.component.InterruptorComponent;
import hamilton.com.br.interruptor.event.FailureEvent;
import hamilton.com.br.interruptor.event.ReleEvent;
import hamilton.com.br.interruptor.model.Rele;
import hamilton.com.br.interruptor.model.ReleDTO;
import hamilton.com.br.interruptor.service.InterruptorService;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_reles)
    ListView relesListView;

    private List<Rele> reles;

    private ReleAdapter adapter;

    private InterruptorComponent component;

    @Inject
    InterruptorService interruptorService;


    @Inject
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // recuperando as reles, caso haja algum
        if (savedInstanceState != null) {
            reles = (List<Rele>) savedInstanceState.getSerializable("reles");
        } else {
            reles = new ArrayList<>();
        }
        adapter = new ReleAdapter(reles, this, interruptorService, eventBus);
        relesListView.setAdapter(adapter);

        InterruptorApplication app = (InterruptorApplication) getApplication();
        component = app.getComponent();
        component.inject(this);
        listarReles();

        // vamos começar a ouvir o evento, por isso passamos o this
        eventBus.register(this);




    }

    @Subscribe
    public void colocaNaLista(ReleEvent releEvent) {
        Log.i(this.getClass().getSimpleName(), "Adicionando rele " + releEvent.releDTO.getReles());
        reles = releEvent.releDTO.getReles();
        ReleAdapter adapter = new ReleAdapter(reles, this, interruptorService, eventBus);
        relesListView.setAdapter(adapter);
    }

    public void listarReles() {
        Call<ReleDTO> call = interruptorService.listar();
        call.enqueue(new ListarRelesCallback(eventBus));
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Subscribe
    public void lidarCom(FailureEvent event) {
        Toast.makeText(this, "Erro: " +  event.mensagem, Toast.LENGTH_LONG).show();
        listarReles();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("reles", (ArrayList<Rele>) reles);
    }
}

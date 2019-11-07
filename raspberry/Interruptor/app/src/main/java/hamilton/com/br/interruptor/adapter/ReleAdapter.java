package hamilton.com.br.interruptor.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hamilton.com.br.interruptor.R;
import hamilton.com.br.interruptor.callback.AlterarReleCallback;
import hamilton.com.br.interruptor.callback.ListarRelesCallback;
import hamilton.com.br.interruptor.event.AlterarReleEvent;
import hamilton.com.br.interruptor.event.ReleEvent;
import hamilton.com.br.interruptor.model.Rele;
import hamilton.com.br.interruptor.service.InterruptorService;
import retrofit2.Call;

/**
 * Created by hamilton on 27/03/17.
 */

public class ReleAdapter extends BaseAdapter {

    private List<Rele> reles;
    private Activity activity;
    private InterruptorService interruptorService;
    private EventBus eventBus;
    @BindView(R.id.tv_nome_rele)
    TextView idTextView;

    @BindView(R.id.sw_interrruptor)
    Switch interruptor;


    public ReleAdapter(List<Rele> reles, Activity activity, InterruptorService interruptorService, EventBus eventBus) {
        this.reles = reles;
        this.activity = activity;
        this.interruptorService = interruptorService;
        this.eventBus = eventBus;
    }

    @Override
    public int getCount() {
        return reles.size();
    }

    @Override
    public Rele getItem(int i) {
        return reles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View linha = activity.getLayoutInflater().inflate(R.layout.rele, viewGroup, false);
//        texto = (TextView) linha.findViewById(R.id.tv_texto);
        ButterKnife.bind(this, linha);

        final Rele rele = getItem(position);
        idTextView.setText("GPIO " + rele.getId());
        interruptor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                Log.i("liga botao", "checked=" + checked);
                rele.setLigado(checked);
                interruptorService.alterar(rele);
                Call<Rele> call = interruptorService.alterar(rele);
                call.enqueue(new AlterarReleCallback(eventBus));
            }
        });


        return linha;
    }

//    @OnCheckedChanged(R.id.sw_interrruptor)
//    public void alterarSwitch(boolean ischecked){
//
//       Log.i("altera switch ", "checked=" + ischecked);
//    }



    @Subscribe
    public void teste(AlterarReleEvent releEvent) {
        Log.i(this.getClass().getSimpleName(), "alterar rele " + releEvent.rele);
    }

}

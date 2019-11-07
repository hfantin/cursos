package hamilton.com.br.callback;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import hamilton.com.br.event.FailureEvent;
import hamilton.com.br.event.MensagemEvent;
import hamilton.com.br.model.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hamilton on 06/02/17.
 */

public class OuvirMensagemCallback  implements Callback<Mensagem> {

//    private MainActivity activity;

    private EventBus eventBus;
    private Context context;

    public OuvirMensagemCallback(EventBus eventBus, Context context){
        this.eventBus = eventBus;
        this.context = context;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if(response.isSuccessful()){
            Mensagem mensagem = response.body();
            // usando delegate
//            activity.colocaNaLista(mensagem);

            // com broadcast
//            Intent intent = new Intent("nova_mensagem");
//            intent.putExtra("mensagem", mensagem);
//
//            // O método 'getInstance' recebe um Context !!
//            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
//
//            localBroadcastManager.sendBroadcast(intent);

            eventBus.post(new MensagemEvent(mensagem));


        }


    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
//        activity.ouvirMensagem();
        eventBus.post(new FailureEvent());

    }
}

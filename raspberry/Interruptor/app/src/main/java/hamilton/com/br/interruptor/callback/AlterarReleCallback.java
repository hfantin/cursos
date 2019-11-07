package hamilton.com.br.interruptor.callback;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import hamilton.com.br.interruptor.event.AlterarReleEvent;
import hamilton.com.br.interruptor.event.FailureEvent;
import hamilton.com.br.interruptor.event.ReleEvent;
import hamilton.com.br.interruptor.model.Rele;
import hamilton.com.br.interruptor.model.ReleDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hamilton on 06/02/17.
 */

public class AlterarReleCallback implements Callback<Rele> {

    private EventBus eventBus;

    public AlterarReleCallback(EventBus eventBus){
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<Rele> call, Response<Rele> response) {
        if(response.isSuccessful()){
            Rele rele = response.body();
            eventBus.post(new AlterarReleEvent(rele));
        }else{
            Log.i("onResponse", "erro: " + response.errorBody());
            eventBus.post(new FailureEvent("fallha ao alterar rele"));
        }
    }

    @Override
    public void onFailure(Call<Rele> call, Throwable t) {
        eventBus.post(new FailureEvent(t.getMessage()));
    }
}

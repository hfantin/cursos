package hamilton.com.br.interruptor.callback;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

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

public class ListarRelesCallback implements Callback<ReleDTO> {

    private EventBus eventBus;

    public ListarRelesCallback(EventBus eventBus){
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<ReleDTO> call, Response<ReleDTO> response) {
        if(response.isSuccessful()){
            ReleDTO releDTO = response.body();
            Log.i("listarReles", "dto" +  releDTO);
            eventBus.post(new ReleEvent(releDTO));
        }else{
            Log.i("onResponse", "erro: " + response.errorBody());
            eventBus.post(new FailureEvent("fallha ao obter lista"));
        }
    }

    @Override
    public void onFailure(Call<ReleDTO> call, Throwable t) {
        eventBus.post(new FailureEvent(t.getMessage()));
    }
}

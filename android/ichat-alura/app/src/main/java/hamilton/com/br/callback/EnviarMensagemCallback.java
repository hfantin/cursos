package hamilton.com.br.callback;

import hamilton.com.br.activity.MainActivity;
import hamilton.com.br.model.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hamilton on 06/02/17.
 */

public class EnviarMensagemCallback implements Callback<Void> {


    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {

    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }


}

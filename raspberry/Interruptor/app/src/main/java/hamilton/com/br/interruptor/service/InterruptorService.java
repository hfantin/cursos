package hamilton.com.br.interruptor.service;

import hamilton.com.br.interruptor.model.Rele;
import hamilton.com.br.interruptor.model.ReleDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by hamilton on 27/03/17.
 */

public interface InterruptorService {
    @PUT("api/v1/reles")
    Call<Rele> alterar(@Body Rele rele);

    @GET("api/v1/reles")
    Call<ReleDTO> listar();

    @GET("api/v1/reles/{id}")
    Call<Rele> obter(@Path("id") String id);

}

package hamilton.com.br.interruptor.module;

import android.app.Application;
import android.view.inputmethod.InputMethodManager;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import hamilton.com.br.interruptor.service.InterruptorService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by hamilton on 27/03/17.
 */
@Module
public class InterruptorModule {

    private static final String SERVIDOR = "http://192.168.25.12:5000/";

    private Application app;

    public InterruptorModule(Application app) {
        this.app = app;
    }

    @Provides
    public InterruptorService getInterruptorService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                // Altere para o seu IP
                .baseUrl(SERVIDOR)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        InterruptorService chatService = retrofit.create(InterruptorService.class);
        return chatService;
    }


    @Provides
    public EventBus getEventBus(){
        return EventBus.builder().build();
    }


}

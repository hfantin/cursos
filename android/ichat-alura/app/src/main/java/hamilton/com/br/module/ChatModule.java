package hamilton.com.br.module;

import android.app.Application;
import android.view.inputmethod.InputMethodManager;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import hamilton.com.br.service.ChatService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by hamilton on 21/02/17.
 */

@Module
public class ChatModule {

    private static final String NOTEBOOK_HAMILTON = "http://192.168.25.14:8080";

    private Application app;

    public ChatModule(Application app) {
        this.app = app;
    }

    @Provides
    public ChatService getChatService(){
        Retrofit retrofit = new Retrofit.Builder()
                // Altere para o seu IP
                .baseUrl(NOTEBOOK_HAMILTON)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);
        return chatService;
    }

    @Provides
    public Picasso picasso() {
        Picasso picasso = new Picasso.Builder(app).build();
        return picasso;
    }

    @Provides
    public EventBus getEventBus(){
        return EventBus.builder().build();
    }

    @Provides
    public InputMethodManager getInputMethodManager(){
        return (InputMethodManager) app.getSystemService(INPUT_METHOD_SERVICE);
    };
}

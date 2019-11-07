package hamilton.com.br.app;

import android.app.Application;

import hamilton.com.br.component.ChatComponent;
import hamilton.com.br.component.DaggerChatComponent;
import hamilton.com.br.module.ChatModule;

/**
 * Created by hamilton on 21/02/17.
 */

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        component = DaggerChatComponent.builder().chatModule(new ChatModule(this)).build();
    }

    public ChatComponent getComponent() {
        return component;
    }
}

package hamilton.com.br.component;

import android.view.inputmethod.InputMethodManager;

import dagger.Component;
import hamilton.com.br.activity.MainActivity;
import hamilton.com.br.adapter.MensagemAdapter;
import hamilton.com.br.module.ChatModule;

/**
 * Created by hamilton on 21/02/17.
 */

@Component(modules= ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);
    void inject(MensagemAdapter adapter);
    void inject(InputMethodManager inputMethodManager);
}

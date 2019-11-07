package hamilton.com.br.interruptor.component;

import dagger.Component;
import hamilton.com.br.interruptor.activity.MainActivity;
import hamilton.com.br.interruptor.adapter.ReleAdapter;
import hamilton.com.br.interruptor.module.InterruptorModule;

/**
 * Created by hamilton on 27/03/17.
 */
@Component(modules= InterruptorModule.class)
public interface InterruptorComponent {
    void inject(MainActivity activity);
}

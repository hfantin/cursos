package hamilton.com.br.interruptor.app;

import android.app.Application;

import hamilton.com.br.interruptor.component.DaggerInterruptorComponent;
import hamilton.com.br.interruptor.component.InterruptorComponent;
import hamilton.com.br.interruptor.module.InterruptorModule;

/**
 * Created by hamilton on 27/03/17.
 */

public class InterruptorApplication extends Application{

    private InterruptorComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        this.component = DaggerInterruptorComponent.builder().interruptorModule(new InterruptorModule(this)).build();
    }

    public InterruptorComponent getComponent() {
        return component;
    }
}

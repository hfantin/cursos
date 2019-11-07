package com.androidteste.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.androidteste.R;
import com.androidteste.databinding.ActivityProgressbarBinding;
import com.androidteste.model.ProgressModel;


/**
 * Created by f3900699 on 28/06/17.
 */

public class ProgressbarActivity extends AppCompatActivity {

    private static final int VALOR_MAXIMO = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityProgressbarBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_progressbar);
        // valores
        binding.alteraValor.setMax(VALOR_MAXIMO);
        final ProgressModel model = new ProgressModel(0, 0, 0);
        binding.setProgress(model);
        // listeners
        binding.alteraValor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    binding.valor.setText(""+progress);
                    int maximo = binding.barra.getMax();
                    if(progress <= maximo){
                        model.setPrimario(progress);
                        model.setSecundario(0);
                    }else{
                        float percentualBarraVerde = maximo * 1.0f/ progress;
                        int valorVerde = Math.round(percentualBarraVerde * maximo);
                        // atribui valores
                        model.setSecundario(maximo);
                        model.setPrimario(valorVerde);
                    }
                    binding.setProgress(model);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        

    }
}

package com.github.hfantin.hac.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.hfantin.hac.R;
import com.hfantin.hac.componentes.GraficoBarrasPonteiro;
import com.hfantin.hac.utils.Utils;


public class GraficoBarrasActivity extends AppCompatActivity {

    private GraficoBarrasPonteiro grafico;
    private SeekBar limite;

    private TextView valor;


//    valorOk=4658.63, valorAtual=1154.87, valorSugerido=1154.87, valorTotal=4658.63
    private Double valorMediaDespesa = 1300.0;
    private Double valorMedioReceita = 1200.0;
    // gasto recomendado
    private Double valorSugestaoOrcamento = 1000.0; // posicao inical do ponteiro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_barras);
        Double valorTotal = valorMediaDespesa > valorMedioReceita ? valorMediaDespesa : valorMedioReceita;
        limite = (SeekBar) findViewById(R.id.limite);
        valor = (TextView) findViewById(R.id.valor);
        valor.setText(Utils.price(valorTotal));
        grafico = (GraficoBarrasPonteiro) findViewById(R.id.grafico);

        grafico.setValorOk(valorMedioReceita);
        grafico.setValorSugerido(valorSugestaoOrcamento);
        grafico.setValorAtual(valorSugestaoOrcamento);
        grafico.setValorTotal(valorTotal);
        grafico.setPercentualAlerta(10);
        grafico.setTextoValorReferencia(Utils.price(valorSugestaoOrcamento));
        grafico.setTextoValorTotal(Utils.price(valorMediaDespesa));
        grafico.invalidate();

        limite.setProgress(valorTotal.intValue());

        limite.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    Double valorOrcado = progress * 1.0;
                    valor.setText(Utils.price(valorOrcado));
                    grafico.setValorAtual(valorOrcado);
                    grafico.invalidate();
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

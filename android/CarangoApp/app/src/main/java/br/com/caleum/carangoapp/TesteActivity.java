package br.com.caleum.carangoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.activity.LeilaoActivity;
import br.com.caelum.fj59.carangos.gcm.Constantes;

public class TesteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        Button botao = (Button) findViewById(R.id.botao_teste);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TesteActivity.this, LeilaoActivity.class);
                intent.putExtra("idDaNotificacao", Constantes.ID_NOTIFICACAO);
                startActivity(intent);
            }
        });
    }
}

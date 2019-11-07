package com.github.hfantin.clima.java.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bb.com.br.clima.R;
import com.github.hfantin.clima.java.domain.model.Temperatura;
import com.github.hfantin.clima.java.ui.adapter.TemperaturaAdapter;

public class TemperaturaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private RecyclerView temperaturasRV;
    private Calendar data = Calendar.getInstance();
    private Button botao;
    private TemperaturaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("activity", "onCreate()");
        setContentView(R.layout.activity_clima);
        setTitle("Diadema - SP");
        obterViews();
        defineListener();
        definirAdapter();
    }

    private void obterViews() {
        temperaturasRV = findViewById(R.id.temperaturas);
        botao = findViewById(R.id.botao);
    }

    private void defineListener() {
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Teste", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void definirAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        temperaturasRV.setLayoutManager(layoutManager);
        adapter = new TemperaturaAdapter(listaMockada(), this);
        temperaturasRV.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
        Temperatura temperatura = adapter.getItem(posicao);
        String mensagem = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("pt","BR")).format(temperatura.getData())
                + " - " + temperatura.getDescricao()
                + " - max="  + temperatura.getTemperaturaMaxima()
                + " - min=" + temperatura.getTemperaturaMinima();
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
    }

    private List<Temperatura> listaMockada() {
        List<Temperatura> temperaturas = new ArrayList<>();
        // dados obtidos em https://openweathermap.org/city/3464739
        temperaturas.add(new Temperatura(R.drawable.ic_chuva_forte, getDataPosterior(), "Chuva Forte",24.1, 18.8)); // dia 11/01/2017
        temperaturas.add(new Temperatura(R.drawable.ic_chuva_moderada, getDataPosterior(), "Chuva Moderada",25.7, 21.8));
        temperaturas.add(new Temperatura(R.drawable.ic_chuva_fraca, getDataPosterior(), "Chuva Fraca",28.1, 23.6));
        temperaturas.add(new Temperatura(R.drawable.ic_chuva_forte, getDataPosterior(), "Chuva Forte",24.7, 21.8));
        temperaturas.add(new Temperatura(R.drawable.ic_chuva_forte, getDataPosterior(), "Chuva Forte",23.8, 20.6));


        temperaturas.add(new Temperatura(R.drawable.ic_tempestade, getDataPosterior(), "Tempestade",20.3, 15.4));
        temperaturas.add(new Temperatura(R.drawable.ic_chuva_forte, getDataPosterior(), "Chuva Forte",21.7, 17.9));
        temperaturas.add(new Temperatura(R.drawable.ic_nublado, getDataPosterior(), "Nublado",23.1, 19.3));
        temperaturas.add(new Temperatura(R.drawable.ic_parcialmente_nublado, getDataPosterior(), "Parcialmente Nublado",25.2, 21.1));
        temperaturas.add(new Temperatura(R.drawable.ic_sol, getDataPosterior(), "Sol",26.9, 22.2));
        return temperaturas;
    }

    private Date getDataPosterior() {
        data.add(Calendar.DAY_OF_MONTH, 1);
        return data.getTime();
    }

}

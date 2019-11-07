package com.github.hfantin.hac.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.hfantin.hac.R;
import com.hfantin.hac.componentes.GraficoMeiaLua;


public class GraficoMeiaLuaActivity extends AppCompatActivity {

    private GraficoMeiaLua grafico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_meia_lua);

    }

}

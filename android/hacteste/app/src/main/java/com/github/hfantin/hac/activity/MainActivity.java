package com.github.hfantin.hac.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.hfantin.hac.R;

/**
 * Created by hamilton on 18/09/17.
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_grafico_barras_ponteiro:
                Intent graficoBarrasPonteiroIntent = new Intent(this, GraficoBarrasActivity.class);
                startActivity(graficoBarrasPonteiroIntent);
                return true;
            case R.id.item_grafico_meia_lua:
                Intent graficoMeiaLua = new Intent(this, GraficoMeiaLuaActivity.class);
                startActivity(graficoMeiaLua);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.androidteste.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.androidteste.R;


public class MainActivity extends AppCompatActivity {

    static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("MainActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("MainActivity.onRestoreInstanceState() - " + savedInstanceState.toString());
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        System.out.println("MainActivity.onRestoreInstanceState() - " + savedInstanceState.toString() + ", \n" + persistentState.toString());
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    public void enviarMensagem(View view){
        System.out.println("MainActivity.enviarMensagem()");
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        Switch s = (Switch) findViewById(R.id.teste);
        s.setTextOff("OFF");
        s.setTextOn("On");
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }


    @Override
    protected void onStart() {
        System.out.println("MainActivity.onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        System.out.println("MainActivity.onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("MainActivity.onPause()");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        System.out.println("MainActivity.onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        System.out.println("MainActivity.onStop()");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        System.out.println("MainActivity.onRestart()");
        super.onRestart();
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
            case R.id.item_lista:
                Intent intent1 = new Intent(this, ExpandableListActivity.class);
                startActivity(intent1);
                return true;
            case R.id.item_grafico:
                Intent intent2 = new Intent(this, GraficoActivity.class);
                startActivity(intent2);
                return true;
            case R.id.item_progressbar:
                Intent intent3 = new Intent(this, ProgressbarActivity.class);
                startActivity(intent3);
                return true;
            case R.id.item_start:
                Intent intent4 = new Intent(this, StartActivity.class);
                startActivity(intent4);
                return true;
            case R.id.item_surfaceview:
                Intent intent5 = new Intent(this, SurfaceViewActivity.class);
                startActivity(intent5);
                return true;
            case R.id.item_listas_fragment:
                Intent intent6 = new Intent(this, ListasActivity.class);
                startActivity(intent6);
                return true;
            case R.id.item_listas_com_busca:
                Intent intent7 = new Intent(this, FriendListActivity.class);
                startActivity(intent7);
                return true;
            case R.id.item_osm:
                Intent intent8 = new Intent(this, OSMActivity.class);
                startActivity(intent8);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.androidteste.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidteste.R;


public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("DisplayMessageActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);

    }

    @Override
    protected void onStart() {
        System.out.println("DisplayMessageActivity.onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        System.out.println("DisplayMessageActivity.onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("DisplayMessageActivity.onPause()");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        System.out.println("DisplayMessageActivity.onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        System.out.println("DisplayMessageActivity.onStop()");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        System.out.println("DisplayMessageActivity.onRestart()");
        super.onRestart();
    }
}

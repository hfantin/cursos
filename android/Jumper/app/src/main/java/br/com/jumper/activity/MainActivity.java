package br.com.jumper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Set;

import br.com.jumper.engine.Game;

public class MainActivity extends Activity implements View.OnClickListener{

    private Game game;
    private Button reiniciar;
    private Thread jogo;
    private FrameLayout container;
//    private int numero = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (FrameLayout) findViewById(R.id.container);
        reiniciar = (Button) findViewById(R.id.botao_reiniciar);
        reiniciar.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(game != null && game.isPaused()){https://www.vivaolinux.com.br/dica/Wiithon-WBFS-Manager-para-Linux
            game.despausar();
        }else{
            iniciarJogo();
        }


    }

    public void iniciarJogo(){
        reiniciar.setVisibility(View.GONE);
        this.game = new Game(this);
        this.game.inicia();
        jogo = new Thread(this.game);
//        jogo.setName("jumper_" + numero++);
        jogo.start();
        container.addView(game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(game != null && !game.isPaused()){
            game.pausar();
        }
//        this.game.cancela();
    }

    public void exibirBotaoReiniciar() {
        reiniciar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        container.removeAllViews();
        iniciarJogo();
    }

//    private void listarThreads(){
//        System.out.println("**** lista de threads ****");
//        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//        for ( Thread t : threadSet){
//            if ( t.getThreadGroup() == Thread.currentThread().getThreadGroup()){
//                System.out.println("Thread :"+t.getName()+":"+"state:"+t.getState());
//            }
//        }
//    }
}

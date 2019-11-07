package br.com.jumper.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import br.com.jumper.activity.MainActivity;
import br.com.jumper.activity.R;
import br.com.jumper.element.Canos;
import br.com.jumper.element.GameOver;
import br.com.jumper.element.Passaro;
import br.com.jumper.element.Pontuacao;
import br.com.jumper.graficos.Tela;

/**
 * Created by hamilton on 10/11/16.
 */

public class Game extends SurfaceView implements Runnable, View.OnTouchListener{

    private final Som som;
    private MainActivity mainActivity;
    private boolean isRunning = true;
    private boolean isPaused = false;
    private Passaro passaro;
    private Canos canos;

    /**
     * dá acesso ao canvas
     */
    private SurfaceHolder holder = getHolder();
    private Bitmap background;
    private final Tela tela;
    private Pontuacao pontuacao;

    public Game(MainActivity mainActivity) {
        super(mainActivity);
        this.tela = new Tela(mainActivity);
        this.som = new Som(mainActivity);
        this.mainActivity = mainActivity;
        setOnTouchListener(this);
        inicializaElementos();
    }
    public void inicializaElementos() {
        this.passaro = new Passaro(tela, getContext(), som);
        this.pontuacao = new Pontuacao(som);
        this.canos = new Canos(tela, pontuacao, getContext());
        Bitmap back = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        this.background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);

    }


    @Override
    public void run() {
        while(isRunning){
            if(isPaused){
                Log.i("game", "game " + "jogo pausado");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                continue;
            }
            // evita nullpointer
            if(!holder.getSurface().isValid()) continue;
            // obtem o canvas em background para evitar o efeito flickering
            Canvas canvas = holder.lockCanvas();
            canvas.drawBitmap(this.background, 0, 0, null);

            passaro.desenhaNo(canvas);
            passaro.cai();
            canos.desenhaNo(canvas);
            canos.move();
            pontuacao.desenhaNo(canvas);

            if(new VerificadorDeColisao(passaro, canos).temColisao()){
                som.play(Som.COLISAO);
                new GameOver(tela).desenhaNo(canvas);
                isRunning = false;
                handler.sendEmptyMessage(0);
            }

            holder.unlockCanvasAndPost(canvas);
        }

    }

    public void cancela() {
        this.isRunning = false;
    }
    public void pausar() {
        this.isPaused = true;
    }
    public void despausar() {  this.isPaused = false; }

    public boolean isPaused() {
        return isPaused;
    }

    public void inicia() {
        this.isRunning = true;

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        passaro.pula();
        return false;
    }

    protected Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            mainActivity.exibirBotaoReiniciar();
        }
    };
}

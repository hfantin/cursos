package br.com.jumper.engine;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import br.com.jumper.activity.R;

/**
 * Created by hamilton on 18/11/16.
 */

public class Som {

    public static int PONTUACAO;
    public static int PULO;
    public static int COLISAO;
    private SoundPool soundPool;

    public Som(Context context) {
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        PULO = soundPool.load(context, R.raw.pulo, 1);
        PONTUACAO = soundPool.load(context, R.raw.pontos, 0);
        COLISAO = soundPool.load(context, R.raw.colisao, 0);

    }

    public void play(int som){
        soundPool.play(som, 1, 1, 1, 0, 1);
    }
}

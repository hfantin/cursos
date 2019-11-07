package br.com.jumper.util;

import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by hamilton on 10/11/16.
 */
public class Cores {


    public static Paint getCorDoPassaro() {
        Paint vermelho = new Paint();
        // formato argb
        vermelho.setColor(0xFFFF0000);
        return vermelho;
    }
    public static Paint getCorDoCano() {
        Paint verde = new Paint();
        // formato argb
        verde.setColor(0xFF00FF00);
        return verde;
    }

    public static Paint getCorDaPontuacao() {
        Paint branco = new Paint();
        // formato argb
        branco.setColor(0xFFFFFFFF);
        branco.setTextSize(80);
        branco.setTypeface(Typeface.DEFAULT_BOLD);
        branco.setShadowLayer(3, 5, 5, 0xFF000000);
        return branco;
    }

    public static Paint getCorDoGameOver() {
        Paint vermelho = new Paint();
        vermelho.setColor(0xFFFF0000);
        vermelho.setTextSize(50);
        vermelho.setTypeface(Typeface.DEFAULT_BOLD);
        vermelho.setShadowLayer(2, 3, 3, 0xFF000000);
        return vermelho;
    }
}

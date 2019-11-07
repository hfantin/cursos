package br.com.caelum.fj59.carangos;

import android.app.Activity;

/**
 * Created by Hamilton on 19/09/16.
 */

public class Tela extends Activity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((CarangosApplication)getApplication()).matarTudo();

    }
}

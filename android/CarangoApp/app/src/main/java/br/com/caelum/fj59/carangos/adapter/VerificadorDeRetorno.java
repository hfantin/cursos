package br.com.caelum.fj59.carangos.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.infra.MyLog;

/**
 * Created by Hamilton on 21/09/16.
 */

public class VerificadorDeRetorno implements Callback {

    private ProgressBar progressBar;

    public VerificadorDeRetorno(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }


    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        progressBar.setVisibility(View.GONE);
    }
}

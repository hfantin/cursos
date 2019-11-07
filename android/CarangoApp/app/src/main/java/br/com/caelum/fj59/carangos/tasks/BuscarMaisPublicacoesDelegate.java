package br.com.caelum.fj59.carangos.tasks;

import android.content.Context;

import java.util.List;

import br.com.caelum.fj59.carangos.CarangosApplication;
import br.com.caelum.fj59.carangos.modelo.Publicacao;

/**
 * Created by Hamilton on 19/09/16.
 */

public interface BuscarMaisPublicacoesDelegate {

    public void lidaComRetorno(List<Publicacao> retorno);
    public void lidaComErro(Exception e);
    public CarangosApplication getCarangosApplication();
}

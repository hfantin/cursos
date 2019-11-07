package br.com.caleum.cadastro.location;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by Hamilton on 17/09/16.
 */
public class Configurador implements GoogleApiClient.ConnectionCallbacks{

    private AtualizadorDeLocalizacao atualizadorDeLocalizacao;

    public Configurador(AtualizadorDeLocalizacao atualizador) {
        this.atualizadorDeLocalizacao = atualizador;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest request = LocationRequest.create();
        request.setInterval(5000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setSmallestDisplacement(50);

        atualizadorDeLocalizacao.inicia(request);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}

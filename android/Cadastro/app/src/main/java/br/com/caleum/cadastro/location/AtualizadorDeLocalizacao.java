package br.com.caleum.cadastro.location;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import br.com.caleum.cadastro.MostraAlunosActivity;

/**
 * Created by Hamilton on 17/09/16.
 */
public class AtualizadorDeLocalizacao implements LocationListener {

    private GoogleApiClient client;
    private MostraAlunosActivity mapa;

    public AtualizadorDeLocalizacao(MostraAlunosActivity context) {
        this.mapa = context;
        Configurador config = new Configurador(this);
        this.client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(config)
                .build();
        this.client.connect();
    }


    @Override
    public void onLocationChanged(Location location) {
        LatLng local = new LatLng(location.getLatitude(), location.getLongitude());
        this.mapa.centralizar(local);
    }


    @SuppressWarnings("MissingPermission")
    public void inicia(LocationRequest request) {
        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
    }

    @SuppressWarnings("MissingPermission")
    public void cancela(){
        LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        this.client.disconnect();
    }
}

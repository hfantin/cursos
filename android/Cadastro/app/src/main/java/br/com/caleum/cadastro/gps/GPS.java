package br.com.caleum.cadastro.gps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import br.com.caleum.cadastro.MostraAlunosActivity;

/**
 * Created by Hamilton on 16/09/16.
 */
public class GPS implements GoogleApiClient.ConnectionCallbacks, LocationListener {

    private GoogleApiClient client;
    private MostraAlunosActivity act;

    public GPS(MostraAlunosActivity act) {
        this.act = act;
        client = new GoogleApiClient.Builder(act)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
        client.connect();
    }

    @Override
    @SuppressWarnings("MissingPermission")
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest req = LocationRequest.create()
                .setInterval(5000)   // intervalo de atualizacao
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY) // prioridade(gps, wifi, 3g...)
                .setSmallestDisplacement(10);  // distancia em metros

        LocationServices.FusedLocationApi.requestLocationUpdates(client, req, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng local = new LatLng(location.getLatitude(), location.getLongitude());
        act.centralizar(local);
    }
}

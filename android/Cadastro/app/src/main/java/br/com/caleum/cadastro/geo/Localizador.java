package br.com.caleum.cadastro.geo;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hamilton on 16/09/16.
 */
public class Localizador {

    private Geocoder geo;

    public Localizador(Context context){
        geo = new Geocoder(context);
    }

    public LatLng getCoordenada(String endereco){
        List<Address> enderecos = null;
        LatLng local = null;
        if(endereco !=null && !endereco.isEmpty()){
            try {
                enderecos = geo.getFromLocationName(endereco, 1);
                if(!enderecos.isEmpty()){
                    Address address = enderecos.get(0);
                    local = new LatLng(address.getLatitude(), address.getLongitude());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       return local;

    }
}

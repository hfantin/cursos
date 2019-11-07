package com.androidteste.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.androidteste.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

/**
 * Created by hamilton on 25/09/17.
 */

public class OSMActivity extends AppCompatActivity implements LocationListener{


    private static final int REQUISICAO_DE_PERMISSAO = 1;
    private GeoPoint currentLocation;
    private LocationManager locationManager;
    private Object currentLocationOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("", ".onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osm);
        // verificar permissoes
        String permissaoLocalizacaoGrosseira = Manifest.permission.ACCESS_COARSE_LOCATION;
        String permissaoLocalizacaoFina = Manifest.permission.ACCESS_FINE_LOCATION;
        if (ActivityCompat.checkSelfPermission(OSMActivity.this, permissaoLocalizacaoGrosseira) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(OSMActivity.this, permissaoLocalizacaoFina) != PackageManager.PERMISSION_GRANTED) {
            String[] permissoes = {permissaoLocalizacaoGrosseira, permissaoLocalizacaoFina};
            ActivityCompat.requestPermissions(OSMActivity.this, permissoes, REQUISICAO_DE_PERMISSAO);
        }

        Context ctx = getApplicationContext();
        //important! set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        // habilita zoom com 2 dedos
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        // TODO obter localizacao atual
        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);


        // marcar localizacao atual
//        final GeoPoint berlinGeoPoint = new GeoPoint(52.516667, 13.383333);
        OverlayItem overlayItem = new OverlayItem("Berlin", "City", startPoint);
        Drawable markerDrawable= this.getResources().getDrawable(R.drawable.ic_location);
        DrawableCompat.setTint(markerDrawable, Color.RED);
        overlayItem.setMarker(markerDrawable);


        ArrayList<OverlayItem> overlayItemArrayList = new ArrayList<>();
        overlayItemArrayList.add(overlayItem);
        ItemizedOverlay<OverlayItem> locationOverlay = new ItemizedIconOverlay<>(this, overlayItemArrayList, null);
        map.getOverlays().add(locationOverlay);

        // move para posicao especifica do mapa

        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);


        // listener
//        map.addon addTapListener(new MapViewLoc.OnTapListener() {
//
//            @Override
//            public void onMapTapped(GeoPoint geoPoint) {}
//
//            @Override
//            public void onMapTapped(Location location) {
//
//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude(),
//                        Toast.LENGTH_SHORT);
//
//                toast.show();
//            }
//        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if( location != null ) {
            currentLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if you make changes to the configuration, use
        //this will refresh the osmdroid configuration on resuming.
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissoes, int[] resultados) {
        if (requestCode == REQUISICAO_DE_PERMISSAO) {
            if (resultados[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = new GeoPoint(location);
        displayMyCurrentLocationOverlay();

    }

    private void displayMyCurrentLocationOverlay() {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

package br.com.caleum.cadastro;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.caleum.cadastro.dominio.Aluno;
import br.com.caleum.cadastro.geo.Localizador;
import br.com.caleum.cadastro.location.AtualizadorDeLocalizacao;
import br.com.caleum.cadastro.persistencia.AlunoDAO;

public class MostraAlunosActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng local;
    private static final LatLng DIADEMA = new LatLng(-23.674713, -46.619365);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_alunos);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.consultar();
        Localizador localizador = new Localizador(this);
        for(Aluno aluno : alunos){
            local = localizador.getCoordenada(aluno.getEndereco());
            if(local != null){
                MarkerOptions marker = new MarkerOptions()
                        .title(aluno.getNome())
                        .snippet(aluno.getTelefone())
                        .position(local);
                mMap.addMarker(marker);
            }
        }
//        centralizar(local);
        new AtualizadorDeLocalizacao(this);

        // Add a marker in diadema and move the camera
//        LatLng teste = new LatLng(-23.674713, -46.619365); //-34, 151);
//
//
//        mMap.addMarker(new MarkerOptions().position(teste) .title("teste"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(teste));
    }

    public void centralizar(LatLng local){

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(local == null ? DIADEMA : local, 11));
    }
}

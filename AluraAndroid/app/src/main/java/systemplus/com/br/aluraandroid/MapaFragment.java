package systemplus.com.br.aluraandroid;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import systemplus.com.br.aluraandroid.dao.AlunoDAO;
import systemplus.com.br.aluraandroid.model.Aluno;

/**
 * Created by root on 02/09/16.
 */
public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mapa;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mapa = googleMap;

        LatLng posicaoDaEscola =
                pegaCordenadasDoEndereco("Rua Manoel Luiz de Abreu 149, bairro Goiânia, Belo Horizonte");

        if (posicaoDaEscola != null) {
            centralizarEm(posicaoDaEscola);
        }

        AlunoDAO alunoDAO = new AlunoDAO(getContext());
        for (Aluno aluno : alunoDAO.listaDeAlunos()) {
            LatLng coordenada = pegaCordenadasDoEndereco(aluno.getEndereco());
            if (coordenada != null) {
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(aluno.getNome());
                marcador.snippet(aluno.getNota().toString());
                googleMap.addMarker(marcador);
            }
        }
        alunoDAO.close();
        new Localizador(getContext(), this);
    }

    public void centralizarEm(LatLng posicaoDaEscola) {
        if (mapa != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoDaEscola, 17);
            mapa.moveCamera(update);
        }
    }

    private LatLng pegaCordenadasDoEndereco(String endereco) {
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados =
                    geocoder.getFromLocationName(endereco, 1);

            if (!resultados.isEmpty()) {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

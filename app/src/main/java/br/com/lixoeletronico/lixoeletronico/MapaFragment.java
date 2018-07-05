package br.com.lixoeletronico.lixoeletronico;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback, LocationListener {

    private static final String TAG = "MapaFragment";

    private GoogleMap mMap;

    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }


    @Override
    public void onResume() {
        super.onResume();

        try {

            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1000, this);
        } catch (SecurityException e){
            e. printStackTrace ( ) ;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(this);


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

        try {

            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            mMap = googleMap;

            mMap.getUiSettings().setZoomControlsEnabled(true);

            //ativa uma função baseada na localização naquele momento
            mMap.setMyLocationEnabled(true);

        } catch (SecurityException ex) {
            Log.e(TAG, "Error", ex);
        }

        // Define a latitude e longitude dos pontos de coleta que serão exibidos no mapa
        LatLng local = new LatLng(-21.261839, -44.9963659);

        MarkerOptions marker = new MarkerOptions();
        marker.position(local);
        marker.title("Eletrônicos - R. Comandante Vilas Bôas, 194 ");
        marker.snippet("Jardim Floresta, Lavras - Telefone:(35)9983-3925");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapa_lixo));


        mMap.addMarker(marker);

        // Define a latitude e longitude dos pontos de coleta que serão exibidos no mapa
        LatLng local2 = new LatLng(-21.235071, -44.990093);

        MarkerOptions marker2 = new MarkerOptions();
        marker2.position(local2);
        marker2.title("Eletrônicos - R. Silvio Modesto de Souza, 540 ");
        marker2.snippet("Jardim das Alterosas, Lavras - Telefone:(35)3822-1166");
        marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapa_lixo));

        mMap.addMarker(marker2);

        // Define a latitude e longitude dos pontos de coleta que serão exibidos no mapa
        LatLng local3 = new LatLng(-21.239560, -44.998216);

        MarkerOptions marker3 = new MarkerOptions();
        marker3.position(local3);
        marker3.title("Eletrônicos - R. Raul Soares, 65 ");
        marker3.snippet("Centro, Lavras - Telefone:(35)3694-4158");
        marker3.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapa_lixo));

        mMap.addMarker(marker3);

        // Define a latitude e longitude dos pontos de coleta que serão exibidos no mapa
        LatLng local4 = new LatLng(-21.245239, -45.000387);

        MarkerOptions marker4 = new MarkerOptions();
        marker4.position(local4);
        marker4.title("Pilhas&Baterias - R. Dr. Francisco Sales, 220");
        marker4.snippet("Padre Dehon, Lavras - DROGARIA SÃO PAULO");
        marker4.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapa_lixo));

        mMap.addMarker(marker4);

        // Define a latitude e longitude dos pontos de coleta que serão exibidos no mapa
        LatLng local5 = new LatLng(-21.240417, -44.998445);

        MarkerOptions marker5 = new MarkerOptions();
        marker5.position(local5);
        marker5.title("Pilhas&Baterias - R. Raul Soares, 159");
        marker5.snippet("Centro, Lavras - AGÊNCIA DOS CORREIOS");
        marker5.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapa_lixo));

        mMap.addMarker(marker5);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(local,13));

    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng localizacao = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions marker = new MarkerOptions();
        marker.position(localizacao);
        marker.title("Minha localizaçao");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapa_person));

        mMap.addMarker(marker);


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



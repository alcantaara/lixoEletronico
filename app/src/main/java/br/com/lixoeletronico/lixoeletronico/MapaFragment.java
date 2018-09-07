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
        marker.title(" Rua Comandante Vilas Bôas, 194 ");
        marker.snippet("Jardim Floresta, Lavras - Telefone:(35)9983-3925");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapa_lixo));


        mMap.addMarker(marker);

        // Define a latitude e longitude dos pontos de coleta que serão exibidos no mapa
        LatLng local2 = new LatLng(-21.235071, -44.990093);

        MarkerOptions marker2 = new MarkerOptions();
        marker2.position(local2);
        marker2.title(" Rua Silvio Modesto de Souza, 540 ");
        marker2.snippet("Jardim das Alterosas, Lavras - Telefone:(35)3822-1166");
        marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapa_lixo));

        mMap.addMarker(marker2);


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



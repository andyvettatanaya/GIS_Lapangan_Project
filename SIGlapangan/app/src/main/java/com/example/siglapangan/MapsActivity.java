package com.example.siglapangan;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] nama,alamat,keterangan,foto;
    private Integer[] id;
    Boolean MarkerD[];

    int jumdata;
    private Double[] latitude,longitude;
    LatLng latLng[];

    public static String getjenis="lapangan";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maps );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
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
                    tampilpeta();


                    // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng( -34, 151 );
//        mMap.addMarker( new MarkerOptions().position( sydney ).title( "Marker in Sydney" ) );
//        mMap.moveCamera( CameraUpdateFactory.newLatLng( sydney ) );
                }

                private void tampilpeta() {

                    String Url="https://lapangangis.000webhostapp.com/lapanganjson.php";

                    final String getdataintent =getIntent().getStringExtra( getjenis );
                    if ("lapangan".equals( getdataintent )){
                        Url="https://lapangangis.000webhostapp.com/lapanganjson.php";
                    }else if ("toko".equals( getdataintent )){
                        Url="https://lapangangis.000webhostapp.com/tokolapjson.php";
                    }else if ("hotel".equals( getdataintent )){
                        Url="https://lapangangis.000webhostapp.com/hoteljson.php";
                    }else if ("rumahsakit".equals( getdataintent )){
                        Url="https://lapangangis.000webhostapp.com/rumahsakitjson.php";
                    }


                    JsonArrayRequest request = new JsonArrayRequest( Request.Method.GET, Url, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            jumdata = response.length();
                            Log.d( "DEBUG_", "parse json: " );
                latLng = new LatLng[jumdata];
                MarkerD = new Boolean[jumdata];

                latitude = new Double[jumdata];
                longitude = new Double[jumdata];

                nama = new String[jumdata];
                alamat = new String[jumdata];
                foto = new String[jumdata];
                keterangan = new String[jumdata];


                id = new Integer[jumdata];
                for (int i = 0; i < jumdata; i++) {
                    try {
                        JSONObject data = response.getJSONObject( i );
                        id[i] = data.getInt( "id" );
                        latLng[i] = new LatLng( data.getDouble( "latitude" ), data.getDouble( "longitude" ) );
                        nama[i] = data.getString( "nama" );
                        alamat[i] = data.getString( "alamat" );
                        keterangan[i] = data.getString( "keterangan" );
                        latitude[i] = data.getDouble( "latitude" );
                        longitude[i] = data.getDouble( "longitude" );
                        foto[i] = data.getString( "foto" );
                        MarkerD[i] = false;
                        mMap.addMarker( new MarkerOptions()
                                .position( latLng[i] )
//                                .snippet( keterangan[i] )
//                                .icon( BitmapDescriptorFactory.fromResource( R.drawable.icon ) )
                                .title( nama[i] ) );


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( latLng[i], 15.5f ) );
                }
                mMap.setOnMarkerClickListener( new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        for(int i=0; i<jumdata; i++){
                            if(marker.getTitle().equals( nama[i] )){
                                if(MarkerD[i]){
                                    DetailActivity.nama=nama[i];
                                    DetailActivity.keterangan=keterangan[i];
                                    DetailActivity.alamat=alamat[i];
                                    DetailActivity.foto=foto[i];
//                                    Intent pindahdetail = new Intent( MapsActivity.this,DetailActivity.class );
                                    Intent pindahdetail = new Intent( MapsActivity.this,DetailActivity.class );
                                    startActivity( pindahdetail );
                                    MarkerD[i]=false;

                                }else{
                                    MarkerD[i]=true;
                                    marker.showInfoWindow();
                                    Toast pesan = Toast.makeText( MapsActivity.this,"Silahkan Klik Untuk Detail",Toast.LENGTH_LONG );
                                    TextView tv = pesan.getView().findViewById( R.id.message );
                                    if (tv !=null)
                                        tv.setGravity( Gravity.CENTER );
                                    pesan.show();
                                }

                            }else {
                                MarkerD[i]=false;
                            }
                        }
                        return false;
                    }

                } );
            }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder( MapsActivity.this );
                        builder.setTitle( "Error" );
                        builder.setMessage( "Failed" );
                        builder.setPositiveButton( "Reload", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tampilpeta();
                            }
                        } );
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
        } );
                Volley.newRequestQueue( this ).add( request );

    }
}

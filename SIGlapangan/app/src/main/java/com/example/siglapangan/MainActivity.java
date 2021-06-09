package com.example.siglapangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout lllapangan,lltoko,llexit,llhotel,llrumahsakit,llpengembang;
    public String jenisdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        lllapangan=findViewById( R.id.lapanganmap );
        lltoko=findViewById( R.id.tokomap );
        llexit=findViewById( R.id.exit );
        llhotel=findViewById( R.id.hotelmap );
        llrumahsakit=findViewById( R.id.rumahsakitmap );
        llpengembang=findViewById( R.id.pengembangmap );

        lllapangan.setOnClickListener( this );
        lltoko.setOnClickListener( this );
        llexit.setOnClickListener( this );
        llhotel.setOnClickListener( this );
        llrumahsakit.setOnClickListener( this );
        llpengembang.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lapanganmap:
                Intent pindahmap = new Intent( MainActivity.this,MapsActivity.class );
                pindahmap.putExtra( MapsActivity.getjenis, jenisdata="lapangan" );
                startActivity( pindahmap );
                break;
            case R.id.tokomap:
                Intent pindahtoko = new Intent( MainActivity.this,MapsActivity.class );
                pindahtoko.putExtra( MapsActivity.getjenis, jenisdata="toko" );
                startActivity( pindahtoko );
                break;
            case R.id.hotelmap:
                Intent pindahhotel = new Intent( MainActivity.this,MapsActivity.class );
                pindahhotel.putExtra( MapsActivity.getjenis, jenisdata="hotel" );
                startActivity( pindahhotel );
                break;
            case R.id.rumahsakitmap:
                Intent pindahrumahsakit = new Intent( MainActivity.this,MapsActivity.class );
                pindahrumahsakit.putExtra( MapsActivity.getjenis, jenisdata="rumahsakit" );
                startActivity( pindahrumahsakit );
                break;
            case R.id.pengembangmap:
                Intent pindahpengembang = new Intent( MainActivity.this,PengembangActivity.class );
                startActivity( pindahpengembang );
                break;
            case R.id.exit:
                finish();
                System.exit(0);
                break;
        }

    }
}

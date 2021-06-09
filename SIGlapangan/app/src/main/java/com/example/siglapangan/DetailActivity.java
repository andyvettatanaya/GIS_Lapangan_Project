package com.example.siglapangan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
TextView tvanama,tvaketerangan,tvalamat;
ImageView gambar;
public static String nama,keterangan,alamat,foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail );

        tvanama=findViewById( R.id.tvdnama );
        tvaketerangan=findViewById( R.id.tvdketerangan );
        tvalamat=findViewById( R.id.tvdalamat );
        gambar=findViewById( R.id.gambardet );

        tvanama.setText( nama );
        tvaketerangan.setText( keterangan );
        tvalamat.setText( alamat );
        Glide
                .with(this)
                .load(foto)
                .centerCrop()
                .into(gambar);

    }
}

package com.saddam.nur.kalimasadabaru.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.saddam.nur.kalimasadabaru.R;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class MainActivity extends AppCompatActivity {

    CardView crdJersey, crdKaos, crdJaket, crdSignout;
    Intent pemesanan, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView lblnama = (TextView) findViewById(R.id.lblNama);
        SharedPreferences prefs = getSharedPreferences("kalimasada", Context.MODE_PRIVATE);
        String coba = prefs.getString("nama", null);
        lblnama.setText("Hai " + coba);


        BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider1);
        List<Banner> banners = new ArrayList<>();
        //add banner using image url
        banners.add(new RemoteBanner("https://2.bp.blogspot.com/-FmWjzrULjYw/Vhpq8pW1fhI/AAAAAAAAB_k/t_HFSSzDyUM/s1600/Membuat%2Bkelas%2BDataKaryawan%2Bdi%2Bpaket%2Bpertemuan2%2B-%2Blab%2Bjava%2Bpetani%2Bkode.png"));
        banners.add(new RemoteBanner("https://1.bp.blogspot.com/-SZK84ux2_ic/VhpyyY7R3eI/AAAAAAAAB_0/BC6M4FtlG0s/s1600/Hasil%2Boutput%2Bprogram%2BDataKaryawan%2B-%2Blab%2Bjava%2Bpetani%2Bkode.png"));
        //add banner using resource drawable
        //.add(new DrawableBanner(R.drawable.yourDrawable));
        bannerSlider.setBanners(banners);


        crdJersey = (CardView) findViewById(R.id.crdJersey);
        crdKaos = (CardView) findViewById(R.id.crdKaos);
        crdJaket = (CardView) findViewById(R.id.crdJaket);
        crdSignout = (CardView) findViewById(R.id.crdSignOut);

        pemesanan = new Intent(this, pemesanan.class);
        login = new Intent(this, login.class);

        crdJersey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pemesanan.putExtra("Kategori", "Jersey");
                startActivity(pemesanan);
            }
        });

        crdKaos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pemesanan.putExtra("Kategori", "Kaos");
                startActivity(pemesanan);
            }
        });

        crdJaket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pemesanan.putExtra("Kategori", "Jaket");
                startActivity(pemesanan);
            }
        });



        crdSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("kalimasada", MODE_PRIVATE);
                SharedPreferences.Editor mEditor = prefs.edit();

                mEditor.clear();
                mEditor.commit(); // commit changes
                startActivity(login);
                finish();
            }
        });

    }
}
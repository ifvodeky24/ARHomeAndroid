package com.project.idw.arhome.common;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.project.idw.arhome.R;
import com.project.idw.arhome.adapter.PemesananKontrakanKosViewPagerAdapter;
import com.project.idw.arhome.fragment.PemesananKontrakanFragment;
import com.project.idw.arhome.fragment.PemesananKosFragment;

import java.util.Objects;

public class PemesananKontrakanKosActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    public static final String TAG_ID = "id";

    public static final String TAG = PemesananKontrakanKosActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Riwayat Pemesanan");
        setContentView(R.layout.activity_pemesanan_kontrakan_kos);

        Toolbar toolbar = findViewById(R.id.toolbar_pemesanan_kontrakan_kos);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        viewPager = findViewById(R.id.viewpager_pemesanan_kontrakan_kos);
        tabLayout = findViewById(R.id.tabs_pemesanan_kontrakan_kos);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        PemesananKontrakanKosViewPagerAdapter adapter = new PemesananKontrakanKosViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(PemesananKontrakanFragment.newInstance("Pemesanan Kontrakan"), "Pemesanan Kontrakan");
        adapter.addFragment(PemesananKosFragment.newInstance("Pemesanan Kos"), "Pemesanan Kos");
        viewPager.setAdapter(adapter);
    }

    private void goToMainActivity() {
        //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
        Intent intent = new Intent(PemesananKontrakanKosActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToMainActivity();
    }
}


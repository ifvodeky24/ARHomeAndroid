package com.project.idw.arhome.pemilik_iklan;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.project.idw.arhome.R;
import com.project.idw.arhome.adapter.DaftarKontrakanKosPemilikViewPagerAdapter;
import com.project.idw.arhome.common.MainActivity;
import com.project.idw.arhome.fragment.DaftarKontrakanPemilikFragment;
import com.project.idw.arhome.fragment.DaftarKosPemilikFragment;

public class DaftarKontrakanKosPemilikActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    public static final String TAG = DaftarKontrakanKosPemilikActivity.class.getSimpleName();

    public static final String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Daftar Kontrakan dan Kos Saya");
        setContentView(R.layout.activity_daftar_kontrakan_kos_pemilik);

        Toolbar toolbar = findViewById(R.id.toolbar_daftar_kontrakan_kos_pemilik);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager_daftar_kontrakan_kos_pemilik);
        tabLayout = findViewById(R.id.tabs_daftar_kontrakan_kos_pemilik);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        DaftarKontrakanKosPemilikViewPagerAdapter adapter = new DaftarKontrakanKosPemilikViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DaftarKontrakanPemilikFragment.newInstance("Kontrakan"), "Kontrakan");
        adapter.addFragment(DaftarKosPemilikFragment.newInstance("Kos"), "Kos");
        viewPager.setAdapter(adapter);
    }

    private void goToMainActivity() {
        //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
        Intent intent = new Intent(DaftarKontrakanKosPemilikActivity.this, MainActivity.class);
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

package com.project.idw.arhome.pengguna;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.project.idw.arhome.R;
import com.project.idw.arhome.adapter.RekomendasiKontrakanKosViewPagerAdapter;
import com.project.idw.arhome.common.MainActivity;
import com.project.idw.arhome.fragment.RekomendasiKontrakanFragment;
import com.project.idw.arhome.fragment.RekomendasiKosFragment;

public class RekomendasiKontrakanKosActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    public static final String TAG_ID = "id";

    public static final String TAG = RekomendasiKontrakanKosActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Rekomendasi Kontrakan & Kos");
        setContentView(R.layout.activity_rekomendasi_kontrakan_kos);

        Toolbar toolbar = findViewById(R.id.toolbar_rekomendasi_kontrakan_kos);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager_rekomendasi_kontrakan_kos);
        tabLayout = findViewById(R.id.tabs_rekomendasi_kontrakan_kos);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        RekomendasiKontrakanKosViewPagerAdapter adapter = new RekomendasiKontrakanKosViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(RekomendasiKontrakanFragment.newInstance("Rekomendasi Kontrakan"), "Rekomendasi Kontrakan");
        adapter.addFragment(RekomendasiKosFragment.newInstance("Rekomendasi Kos"), "Rekomendasi Kos");
        viewPager.setAdapter(adapter);
    }

    private void goToMainActivity() {
        //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
        Intent intent = new Intent(RekomendasiKontrakanKosActivity.this, MainActivity.class);
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

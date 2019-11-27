package com.project.idw.arhome.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.idw.arhome.R;

public class PetunjukPenggunaanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk_penggunaan);
        this.setTitle("Petunjuk Penggunaan");
    }
}

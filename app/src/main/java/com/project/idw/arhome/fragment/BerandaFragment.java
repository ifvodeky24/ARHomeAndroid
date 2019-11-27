package com.project.idw.arhome.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.idw.arhome.R;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    SliderLayout sliderLayout;
    TextView tv_greeting, tv_greeting_2;
    CardView cv_1, cv_2, cv_3;

    ApiInterface apiInterface;

    SessionManager sessionManager;


    public BerandaFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(6); //set scroll delay in seconds :

        tv_greeting = view.findViewById(R.id.tv_greeting);
        tv_greeting_2 = view.findViewById(R.id.tv_greeting_2);


        cv_1 = view.findViewById(R.id.cv_1);
        cv_2 = view.findViewById(R.id.cv_2);
        cv_3 = view.findViewById(R.id.cv_3);

        setSliderViews();

        cv();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(getActivity());

        TampilkanInformasi();

        return view;


    }

    private void cv() {
        cv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                startActivity(intent);
            }
        });
    }


    private void TampilkanInformasi() {
        if (!sessionManager.isLoggedIn()){
            tv_greeting.setText("Selamat Datang di Aplikasi ARHome");
            tv_greeting_2.setText("Semoga hari anda menyenangkan, jangan lupa login terlebih dahulu");
        }

        if (sessionManager.isLoggedIn()){
            if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)) {
                tv_greeting.setText("Selamat Datang, "+sessionManager.getLoginDetail().get(SessionManager.NAMA_LENGKAP));
                tv_greeting_2.setText("Kontrakan dan kos apa yang ingin anda cari hari ini? Kami memiliki rekomendasi yang bagus khusus untuk anda");
            }else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)) {
                tv_greeting.setText("Selamat Datang, "+sessionManager.getLoginDetail().get(SessionManager.NAMA_LENGKAP));
                tv_greeting_2.setText("Hai Pemilik, apakah anda sudah mengecek pesanan hari ini? atau anda ingin menambahkan kontrakan dan kos baru?");
            }
        }
    }

    private void setSliderViews() {
        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(getActivity());

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.tentang_iklan_1);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.tentang_iklan_2);
//                    sliderView.setDescription("Haiiii");
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.tentang_iklan_3);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.tentang_iklan_4);
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//            sliderView.setDescription("setDescription " + (i + 1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
//                    Toast.makeText(BerandaFragment.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }


}

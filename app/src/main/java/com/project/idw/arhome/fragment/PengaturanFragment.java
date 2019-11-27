package com.project.idw.arhome.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.idw.arhome.R;
import com.project.idw.arhome.common.PetunjukPenggunaanActivity;
import com.project.idw.arhome.pemilik_iklan.UbahPasswordPemilikActivity;
import com.project.idw.arhome.pengguna.UbahPasswordPenggunaActivity;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengaturanFragment extends Fragment {
    TextView tv_ubah_password;
    String id;
    ImageView img_1, img_2;
    LinearLayout ll_ubah_password;

    SessionManager sessionManager;
    ApiInterface apiService;


    public PengaturanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pengaturan, container, false);

        id = sessionManager.getLoginDetail().get("id");

        System.out.println("Hasil" + id);

        tv_ubah_password = view.findViewById(R.id.tv_ubah_password);
        img_1 = view.findViewById(R.id.img_1);
        img_2 = view.findViewById(R.id.img_2);
        ll_ubah_password = view.findViewById(R.id.ll_ubah_password);

        ll_ubah_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UbahPassword();
            }
        });

        return view;
    }

    private void UbahPassword() {
        if (sessionManager.isLoggedIn()){
            if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)){
                Intent intent = new Intent(getActivity(), UbahPasswordPenggunaActivity.class);
                intent.putExtra(UbahPasswordPenggunaActivity.TAG_ID, id);
                startActivity(intent);
            }else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)){
                Intent intent = new Intent(getActivity(), UbahPasswordPemilikActivity.class);
                intent.putExtra(UbahPasswordPemilikActivity.TAG_ID, id);
                startActivity(intent);
            }
        }
    }

}

package com.project.idw.arhome.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.project.idw.arhome.R;
import com.project.idw.arhome.common.MainActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.pemilik_iklan.DaftarKontrakanKosPemilikActivity;
import com.project.idw.arhome.pemilik_iklan.EditProfilPemilikActivity;
import com.project.idw.arhome.pengguna.EditProfilPenggunaActivity;
import com.project.idw.arhome.response.ResponsePemilik;
import com.project.idw.arhome.response.ResponsePemilikDetail;
import com.project.idw.arhome.response.ResponsePengguna;
import com.project.idw.arhome.response.ResponsePenggunaDetail;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {
    private CircleImageView iv_foto_profil;
    private TextView tv_nama_profil, tv_username_profil, tv_email_profil, tv_nama_lengkap_profil, tv_alamat_profil, tv_no_handphone_profil, tv_no_kk;
    LinearLayout linear_no_kk;
    FloatingActionButton fab_edit_profil, fab_data_kontrakan_kos;

    SessionManager sessionManager;
    ApiInterface apiService;

    public static final String TAG_ID = "id";

    public ProfilFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_profil, container, false);

        iv_foto_profil = view.findViewById(R.id.iv_foto_profil);
        tv_nama_profil = view.findViewById(R.id.tv_nama_profil);
        tv_username_profil = view.findViewById(R.id.tv_username_profil);
        tv_email_profil = view.findViewById(R.id.tv_email_profil);
        tv_nama_lengkap_profil = view.findViewById(R.id.tv_nama_lengkap_profil);
        tv_alamat_profil = view.findViewById(R.id.tv_alamat_profil);
        tv_no_handphone_profil = view.findViewById(R.id.tv_no_handphone_profil);
        tv_no_kk = view.findViewById(R.id.tv_no_kk);
        linear_no_kk = view.findViewById(R.id.linear_no_kk);
        fab_edit_profil = view.findViewById(R.id.fab_edit_profil);
        fab_data_kontrakan_kos = view.findViewById(R.id.fab_data_kontrakan_kos);



        TampilkanData();

        return view;
    }

    private void TampilkanData() {
        String id_pengguna = sessionManager.getLoginDetail().get(SessionManager.ID);
        String id_pemilik = sessionManager.getLoginDetail().get(SessionManager.ID);

        if (sessionManager.isLoggedIn()){
            if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)){
                apiService.penggunaById(id_pengguna).enqueue(new Callback<ResponsePenggunaDetail>() {
                    @Override
                    public void onResponse(Call<ResponsePenggunaDetail> call, Response<ResponsePenggunaDetail> response) {
                        if (response.isSuccessful()){
                            final String id_pengguna = sessionManager.getLoginDetail().get(SessionManager.ID);
                            final String foto_profil = sessionManager.getLoginDetail().get(SessionManager.FOTO);
                            final String password = response.body().getMaster().getPassword();
                            final String nama_lengkap = response.body().getMaster().getNamaLengkap();
                            final String username = response.body().getMaster().getUsername();
                            final String email = response.body().getMaster().getEmail();
                            final String alamat = response.body().getMaster().getAlamat();
                            final String no_handphone = response.body().getMaster().getNoHandphone();

                            Picasso.with(getActivity())
                                    .load(ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO))
                                    .into(iv_foto_profil);

                            tv_nama_profil.setText(nama_lengkap);
                            tv_username_profil.setText(username);
                            tv_email_profil.setText(email);
                            tv_nama_lengkap_profil.setText(nama_lengkap);
                            tv_alamat_profil.setText(alamat);
                            tv_no_handphone_profil.setText(no_handphone);

                            iv_foto_profil.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                    alertDialogBuilder.setCancelable(true);
                                    alertDialogBuilder.setTitle("Foto Profil");
                                    alertDialogBuilder.setIcon(R.drawable.ic_icon);

                                    LayoutInflater inflater = getLayoutInflater();

                                    View dialogView = inflater.inflate(R.layout.tampilkan_foto_dialog, null);

                                    alertDialogBuilder.setView(dialogView);

                                    ImageView iv_tampilkan_foto = dialogView.findViewById(R.id.iv_tampilkan_foto);


                                    Picasso.with(getActivity())
                                            .load(ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO))
                                            .into(iv_tampilkan_foto);

                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                }
                            });

                            fab_data_kontrakan_kos.setVisibility(View.GONE);

                            fab_edit_profil.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                                    //set pesan dari dialog
                                    alertDialogBuilder
                                            .setMessage("Apakah anda ingin mengedit profil?")
                                            .setCancelable(false)
                                            .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent_edit_profil = new Intent(getActivity(), EditProfilPenggunaActivity.class);
                                                    intent_edit_profil.putExtra(EditProfilPenggunaActivity.TAG_ID, id_pengguna);
                                                    intent_edit_profil.putExtra(EditProfilPenggunaActivity.TAG_NAMA_LENGKAP, nama_lengkap);
                                                    intent_edit_profil.putExtra(EditProfilPenggunaActivity.TAG_USERNAME, username);
                                                    intent_edit_profil.putExtra(EditProfilPenggunaActivity.TAG_EMAIL, email);
                                                    intent_edit_profil.putExtra(EditProfilPenggunaActivity.TAG_ALAMAT, alamat);
                                                    intent_edit_profil.putExtra(EditProfilPenggunaActivity.TAG_NO_HANDPHONE, no_handphone);
                                                    intent_edit_profil.putExtra(EditProfilPenggunaActivity.TAG_FOTO_PROFIL, foto_profil);
                                                    intent_edit_profil.putExtra(EditProfilPenggunaActivity.TAG_PASSWORD, password);
                                                    startActivity(intent_edit_profil);
                                                }
                                            })
                                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            });

                                    //Membuat alert dialog dari builder
                                    AlertDialog alertDialog = alertDialogBuilder.create();

                                    //Menampilkan alert dialog
                                    alertDialog.show();

                                }
                            });

                        }else {
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePenggunaDetail> call, Throwable t) {

                    }
                });

            }else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)){
                apiService.pemilikById(id_pemilik).enqueue(new Callback<ResponsePemilikDetail>() {
                    @Override
                    public void onResponse(Call<ResponsePemilikDetail> call, Response<ResponsePemilikDetail> response) {
                        if (response.isSuccessful()){
                            if (response.body().getMaster().size()>0){

                                final String id_pemilik = sessionManager.getLoginDetail().get(SessionManager.ID);
                                final String foto_profil = sessionManager.getLoginDetail().get(SessionManager.FOTO);
                                final String password = response.body().getMaster().get(0).getPassword();
                                final String nama_lengkap = response.body().getMaster().get(0).getNamaLengkap();
                                final String username = response.body().getMaster().get(0).getUsername();
                                final String email = response.body().getMaster().get(0).getEmail();
                                final String alamat = response.body().getMaster().get(0).getAlamat();
                                final String no_handphone = response.body().getMaster().get(0).getNoHandphone();
                                final String no_kk = response.body().getMaster().get(0).getNoKk();

                                System.out.println("hasilnya ada"+id_pemilik);

                                Picasso.with(getActivity())
                                        .load(ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO))
                                        .into(iv_foto_profil);

                                linear_no_kk.setVisibility(View.VISIBLE);

                                tv_nama_profil.setText(nama_lengkap);
                                tv_username_profil.setText(username);
                                tv_email_profil.setText(email);
                                tv_nama_lengkap_profil.setText(nama_lengkap);
                                tv_alamat_profil.setText(alamat);
                                tv_no_handphone_profil.setText(no_handphone);
                                tv_no_kk.setText(no_kk);

                                iv_foto_profil.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                        alertDialogBuilder.setCancelable(true);
                                        alertDialogBuilder.setTitle("Foto Profil");
                                        alertDialogBuilder.setIcon(R.drawable.ic_icon);

                                        LayoutInflater inflater = getLayoutInflater();

                                        View dialogView = inflater.inflate(R.layout.tampilkan_foto_dialog, null);

                                        alertDialogBuilder.setView(dialogView);

                                        ImageView iv_tampilkan_foto = dialogView.findViewById(R.id.iv_tampilkan_foto);


                                        Picasso.with(getActivity())
                                                .load(ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO))
                                                .into(iv_tampilkan_foto);

                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                        alertDialog.show();
                                    }
                                });

                                fab_edit_profil.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                                        //set pesan dari dialog
                                        alertDialogBuilder
                                                .setMessage("Apakah anda ingin mengedit profil?")
                                                .setCancelable(false)
                                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Intent intent_edit_profil = new Intent(getActivity(), EditProfilPemilikActivity.class);
                                                        intent_edit_profil.putExtra(EditProfilPemilikActivity.TAG_ID, id_pemilik);
                                                        intent_edit_profil.putExtra(EditProfilPemilikActivity.TAG_NAMA_LENGKAP, nama_lengkap);
                                                        intent_edit_profil.putExtra(EditProfilPemilikActivity.TAG_USERNAME, username);
                                                        intent_edit_profil.putExtra(EditProfilPemilikActivity.TAG_EMAIL, email);
                                                        intent_edit_profil.putExtra(EditProfilPemilikActivity.TAG_ALAMAT, alamat);
                                                        intent_edit_profil.putExtra(EditProfilPemilikActivity.TAG_NO_HANDPHONE, no_handphone);
                                                        intent_edit_profil.putExtra(EditProfilPemilikActivity.TAG_FOTO_PROFIL, foto_profil);
                                                        intent_edit_profil.putExtra(EditProfilPemilikActivity.TAG_PASSWORD, password);
                                                        intent_edit_profil.putExtra(EditProfilPemilikActivity.TAG_NO_KK, no_kk);
                                                        startActivity(intent_edit_profil);

                                                        System.out.println("foto" + foto_profil);
                                                    }
                                                })
                                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                });

                                        //Membuat alert dialog dari builder
                                        AlertDialog alertDialog = alertDialogBuilder.create();

                                        //Menampilkan alert dialog
                                        alertDialog.show();


                                    }
                                });

                                fab_data_kontrakan_kos.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                                        //set pesan dari dialog
                                        alertDialogBuilder
                                                .setMessage("Apakah anda ingin melihat daftar kontrakan dan kos yang anda miliki?")
                                                .setCancelable(false)
                                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Intent intent_daftar_kontrakan_kos_pemilik = new Intent(getActivity(), DaftarKontrakanKosPemilikActivity.class);
                                                        startActivity(intent_daftar_kontrakan_kos_pemilik);
                                                    }
                                                })
                                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                });

                                        //Membuat alert dialog dari builder
                                        AlertDialog alertDialog = alertDialogBuilder.create();

                                        //Menampilkan alert dialog
                                        alertDialog.show();
                                    }
                                });

                            }else {
                                Toast.makeText(getContext(), "Data tidak tersedia", Toast.LENGTH_LONG).show();
                            }

                        }else {
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponsePemilikDetail> call, Throwable t) {
                        t.printStackTrace();

                    }
                });

            }
        }
    }

}

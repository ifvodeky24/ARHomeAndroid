package com.project.idw.arhome.pemilik_iklan;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.idw.arhome.R;
import com.project.idw.arhome.common.DaftarKontrakanKosActivity;
import com.project.idw.arhome.common.DetailPemesananKontrakanActivity;
import com.project.idw.arhome.common.MessageActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.KontrakanDetail;
import com.project.idw.arhome.response.ResponseBookingKontrakan;
import com.project.idw.arhome.response.ResponseHapusKontrakan;
import com.project.idw.arhome.response.ResponseKontrakanDetail;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKontrakanPemilikActivity extends AppCompatActivity {
    ImageView iv_foto_kontrakan, iv_foto_pemilik;
    TextView tv_nama_kontrakan, tv_status_kontrakan, tv_alamat_kontrakan, tv_harga_kontrakan, tv_deskripsi_kontrakan, tv_fasilitas_kontrakan, tv_nama_pemilik, tv_no_handphone_pemilik, tv_waktu_post_kontrakan;
    FloatingActionButton fab_edit, fab_trash_1;
    TextView tv_foto_full;

    int id_pemilik;
    String id_kontrakan, nama_lengkap_pemilik, nama_kontrakan, foto_kontrakan, status_kontrakan, alamat_kontrakan;
    String harga_kontrakan, deskripsi_kontrakan, fasilitas_kontrakan, nama_pemilik, foto_pemilik, no_handphone_pemilik, waktu_post_kontrakan;
    String latitude_kontrakan, longitude_kontrakan, altitude_kontrakan, rating_kontrakan;
    String foto_kontrakan_2, foto_kontrakan_3;

    ApiInterface apiInterface;

    SessionManager sessionManager;

    public static final String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Detail Kontrakan");
        setContentView(R.layout.activity_detail_kontrakan_pemilik);

        iv_foto_kontrakan = findViewById(R.id.iv_foto_kontrakan_1);
        tv_nama_kontrakan = findViewById(R.id.tv_nama_kontrakan_1);
        tv_status_kontrakan = findViewById(R.id.tv_status_kontrakan_1);
        tv_alamat_kontrakan = findViewById(R.id.tv_alamat_kontrakan_1);
        tv_harga_kontrakan = findViewById(R.id.tv_harga_kontrakan_1);
        tv_deskripsi_kontrakan = findViewById(R.id.tv_deskripsi_kontrakan_1);
        tv_fasilitas_kontrakan = findViewById(R.id.tv_fasilitas_kontrakan_1);
        iv_foto_pemilik = findViewById(R.id.iv_foto_pemilik_1);
        tv_nama_pemilik = findViewById(R.id.tv_nama_pemilik_1);
        tv_no_handphone_pemilik = findViewById(R.id.tv_no_handphone_pemilik_1);
        tv_waktu_post_kontrakan = findViewById(R.id.tv_waktu_post_kontrakan_1);
        fab_edit = findViewById(R.id.fab_edit_1);
        fab_trash_1 = findViewById(R.id.fab_trash_1);
        tv_foto_full = findViewById(R.id.tv_foto_full_1);

        apiInterface  = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);

        final String EXTRA_ID_KONTRAKAN_PEMILIK = getIntent().getStringExtra(KontrakanDetail.TAG_ID);

//        Toast.makeText(getApplicationContext(), "EXTRA_ID_KONTRAKAN: "+EXTRA_ID_KONTRAKAN, Toast.LENGTH_SHORT).show();
        apiInterface.kontrakanById(String.valueOf(EXTRA_ID_KONTRAKAN_PEMILIK)).enqueue(new Callback<ResponseKontrakanDetail>() {
            @Override
            public void onResponse(Call<ResponseKontrakanDetail> call, Response<ResponseKontrakanDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMaster().size()>0){
                        KontrakanDetail kontrakanDetail = response.body().getMaster().get(0);
                        id_kontrakan = EXTRA_ID_KONTRAKAN_PEMILIK;
                        System.out.println("id_kontrakan"+id_kontrakan);
                        nama_kontrakan = kontrakanDetail.getNamaKontrakan();
                        foto_kontrakan = kontrakanDetail.getFotoKontrakan1();
                        foto_kontrakan_2 = kontrakanDetail.getFotoKontrakan2();
                        foto_kontrakan_3 = kontrakanDetail.getFotoKontrakan3();
                        status_kontrakan = kontrakanDetail.getStatusKontrakan();
                        alamat_kontrakan = kontrakanDetail.getAlamatKontrakan();
                        harga_kontrakan = kontrakanDetail.getHargaKontrakan();
                        deskripsi_kontrakan = kontrakanDetail.getDeskripsiKontrakan();
                        fasilitas_kontrakan = kontrakanDetail.getFasilitasKontrakan();
                        nama_pemilik = kontrakanDetail.getNamaLengkapPemilik();
                        foto_pemilik = kontrakanDetail.getFotoPemilik();
                        no_handphone_pemilik = kontrakanDetail.getNoHandphonePemilik();
                        waktu_post_kontrakan = kontrakanDetail.getWaktuPost();
                        id_pemilik = Integer.parseInt(sessionManager.getLoginDetail().get(SessionManager.ID));
                        nama_lengkap_pemilik = kontrakanDetail.getNamaLengkapPemilik();
                        latitude_kontrakan = kontrakanDetail.getLatitude();
                        longitude_kontrakan = kontrakanDetail.getLongitude();
                        rating_kontrakan = kontrakanDetail.getRatingKontrakan();
                        altitude_kontrakan = kontrakanDetail.getAltitude();

                        Picasso.with(DetailKontrakanPemilikActivity.this)
                                .load(ServerConfig.KONTRAKAN_IMAGE + foto_kontrakan)
                                .into(iv_foto_kontrakan);

                        System.out.println(ServerConfig.KONTRAKAN_IMAGE + foto_kontrakan);

                        tv_nama_kontrakan.setText(nama_kontrakan);
                        tv_status_kontrakan.setText(status_kontrakan);
                        tv_alamat_kontrakan.setText(alamat_kontrakan);
                        tv_harga_kontrakan.setText("Rp. " + harga_kontrakan + " / Tahun");
                        tv_deskripsi_kontrakan.setText(deskripsi_kontrakan);
                        tv_fasilitas_kontrakan.setText(fasilitas_kontrakan);
                        tv_nama_pemilik.setText("Nama Pemilik: " + nama_pemilik);
                        tv_waktu_post_kontrakan.setText("Update pada " + waktu_post_kontrakan);
                        Picasso.with(DetailKontrakanPemilikActivity.this)
                                .load(ServerConfig.PROFIL_IMAGE + foto_pemilik)
                                .into(iv_foto_pemilik);
                        System.out.println(ServerConfig.PROFIL_IMAGE + foto_pemilik);
                        tv_no_handphone_pemilik.setText("No. Handphone " + no_handphone_pemilik);

                        if (!sessionManager.isLoggedIn()) {
                            fab_edit.setVisibility(View.GONE);
                            fab_trash_1.setVisibility(View.GONE);

                        } else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)) {
                            fab_edit.setVisibility(View.GONE);
                            fab_trash_1.setVisibility(View.GONE);

                        } else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)){
                            fab_edit.setVisibility(View.VISIBLE);
                            fab_trash_1.setVisibility(View.VISIBLE);
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Tidak ada data dengan id" +EXTRA_ID_KONTRAKAN_PEMILIK, Toast.LENGTH_LONG).show();
                    }

                }

//                else {
//                        Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
//                    }

            }

            @Override
            public void onFailure(Call<ResponseKontrakanDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKontrakanPemilikActivity.this);

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda ingin mengedit data kontrakan anda?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                System.out.println("Tampilkan"+id_pemilik);

                                Intent intent = new Intent(DetailKontrakanPemilikActivity.this, EditKontrakanActivity.class);
                                intent.putExtra(EditKontrakanActivity.KEY_ID, id_pemilik);
                                intent.putExtra(EditKontrakanActivity.KEY_NAMA_LENGKAP_PEMILIK, nama_lengkap_pemilik);
                                intent.putExtra(EditKontrakanActivity.KEY_FOTO_PEMILIK, foto_pemilik);
                                intent.putExtra(EditKontrakanActivity.KEY_ID_KONTRAKAN, id_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_NAMA_KONTRAKAN, nama_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_FOTO, foto_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_FOTO_2, foto_kontrakan_2);
                                intent.putExtra(EditKontrakanActivity.KEY_FOTO_3, foto_kontrakan_3);
                                intent.putExtra(EditKontrakanActivity.KEY_STATUS_KONTRAKAN, status_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_ALAMAT_KONTRAKAN, alamat_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_HARGA_KONTRAKAN, harga_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_DESKRIPSI_KONTRAKAN, deskripsi_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_FASILITAS_KONTRAKAN, fasilitas_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_NO_HANDPHONE_PEMILIK, no_handphone_pemilik);
                                intent.putExtra(EditKontrakanActivity.KEY_WAKTU_POST_KONTRAKAN, waktu_post_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_LATITUDE_KONTRAKAN, latitude_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_LONGITUDE_KONTRAKAN, longitude_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_ALTITUDE_KONTRAKAN, altitude_kontrakan);
                                intent.putExtra(EditKontrakanActivity.KEY_RATING_KONTRAKAN, rating_kontrakan);
                                startActivity(intent);
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

        fab_trash_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKontrakanPemilikActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin menghapus pemesanan kos ini?");

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda yakin ingin menghapus kontrakan ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                action_hapus_kontrakan(id_kontrakan);
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

        tv_foto_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKontrakanPemilikActivity.this);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Galeri Foto");
                alertDialogBuilder.setIcon(R.drawable.ic_icon);

                LayoutInflater inflater = getLayoutInflater();

                View dialogView = inflater.inflate(R.layout.foto_full_dialog, null);

                ImageView iv_foto_1 = dialogView.findViewById(R.id.iv_foto_1);
                ImageView iv_foto_2 = dialogView.findViewById(R.id.iv_foto_2);
                ImageView iv_foto_3 = dialogView.findViewById(R.id.iv_foto_3);

                Picasso.with(DetailKontrakanPemilikActivity.this)
                        .load(ServerConfig.KONTRAKAN_IMAGE + foto_kontrakan)
                        .into(iv_foto_1);

                Picasso.with(DetailKontrakanPemilikActivity.this)
                        .load(ServerConfig.KONTRAKAN_IMAGE + foto_kontrakan_2)
                        .into(iv_foto_2);

                Picasso.with(DetailKontrakanPemilikActivity.this)
                        .load(ServerConfig.KONTRAKAN_IMAGE + foto_kontrakan_3)
                        .into(iv_foto_3);

                alertDialogBuilder.setView(dialogView);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void action_hapus_kontrakan(String id_kontrakan) {
        apiInterface.hapusKontrakan(id_kontrakan).enqueue(new Callback<ResponseHapusKontrakan>() {
            @Override
            public void onResponse(Call<ResponseHapusKontrakan> call, Response<ResponseHapusKontrakan> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_kontrakan_pemilik = new Intent(DetailKontrakanPemilikActivity.this, DaftarKontrakanKosPemilikActivity.class);
                        detail_kontrakan_pemilik.putExtra(DaftarKontrakanKosPemilikActivity.TAG_ID, response.body().getData().getIdKontrakan());
                        startActivity(detail_kontrakan_pemilik);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHapusKontrakan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void goToDaftarKontrakanKosPemilikActivity() {
        //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
        Intent intent = new Intent(DetailKontrakanPemilikActivity.this, DaftarKontrakanKosPemilikActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToDaftarKontrakanKosPemilikActivity();
    }
}

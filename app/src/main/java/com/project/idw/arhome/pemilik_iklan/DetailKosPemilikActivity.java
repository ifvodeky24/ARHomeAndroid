package com.project.idw.arhome.pemilik_iklan;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.project.idw.arhome.common.DetailPemesananKosActivity;
import com.project.idw.arhome.common.MessageActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.KosDetail;
import com.project.idw.arhome.response.ResponseBookingKos;
import com.project.idw.arhome.response.ResponseHapusKos;
import com.project.idw.arhome.response.ResponseKosDetail;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKosPemilikActivity extends AppCompatActivity {
    public static final String KEY_ID = "id_pemilik";
    ImageView iv_foto_kos, iv_foto_pemilik;
    TextView tv_nama_kos, tv_harga_kos, tv_jenis_kos, tv_alamat_kos, tv_deskripsi_kos, tv_fasilitas_kos, tv_nama_pemilik, tv_no_handphone_pemilik, tv_waktu_post_kos, tv_stok_kamar_kos;
    FloatingActionButton fab_edit, fab_trash_1;
    TextView tv_foto_full;

    int id_pemilik;
    String nama_lengkap_pemilik, id_kos, nama_kos, harga_kos, foto_kos, jenis_kos, alamat_kos, foto_kos_2, foto_kos_3;
    String foto_pemilik, deskripsi_kos, fasilitas_kos, nama_pemilik;
    String no_handphone_pemilik, waktu_post_kos, stok_kamar_kos, status_kos, latitude_kos, longitude_kos, altitude_kos, rating_kos;

    ApiInterface apiInterface;

    SessionManager sessionManager;

    public static final String TAG_ID = "id_kos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Detail Kos");
        setContentView(R.layout.activity_detail_kos_pemilik);

        iv_foto_kos = findViewById(R.id.iv_foto_kos_1);
        tv_nama_kos = findViewById(R.id.tv_nama_kos_1);
        tv_harga_kos = findViewById(R.id.tv_harga_kos_1);
        tv_jenis_kos = findViewById(R.id.tv_jenis_kos_1);
        tv_alamat_kos = findViewById(R.id.tv_alamat_kos_1);
        tv_deskripsi_kos = findViewById(R.id.tv_deskripsi_kos_1);
        tv_fasilitas_kos = findViewById(R.id.tv_fasilitas_kos_1);
        iv_foto_pemilik = findViewById(R.id.iv_foto_pemilik_1);
        tv_nama_pemilik = findViewById(R.id.tv_nama_pemilik_1);
        tv_no_handphone_pemilik = findViewById(R.id.tv_no_handphone_pemilik_1);
        tv_waktu_post_kos =  findViewById(R.id.tv_waktu_post_kos_1);
        tv_stok_kamar_kos = findViewById(R.id.tv_stok_kamar_kos_1);
        fab_edit = findViewById(R.id.fab_edit_1);
        fab_trash_1 = findViewById(R.id.fab_trash_1);
        tv_foto_full = findViewById(R.id.tv_foto_full_1);

        apiInterface  = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);

        final String EXTRA_ID_KOS_PEMILIK = getIntent().getStringExtra(KosDetail.TAG_ID);

        apiInterface.kosById(String.valueOf(EXTRA_ID_KOS_PEMILIK)).enqueue(new Callback<ResponseKosDetail>() {
            @Override
            public void onResponse(Call<ResponseKosDetail> call, Response<ResponseKosDetail> response) {
                if (response.isSuccessful()){
                    if (response.body().getMaster().size()>0){
                        KosDetail kosDetail = response.body().getMaster().get(0);
                        id_kos = EXTRA_ID_KOS_PEMILIK;
                        System.out.println("id_kos:"+id_kos);
                        nama_kos = kosDetail.getNamaKos();
                        harga_kos = kosDetail.getHargaKos();
                        foto_kos = kosDetail.getFotoKos1();
                        foto_kos_2 = kosDetail.getFotoKos2();
                        foto_kos_3 = kosDetail.getFotoKos3();
                        jenis_kos = kosDetail.getJenisKos();
                        alamat_kos = kosDetail.getAlamatKos();
                        deskripsi_kos = kosDetail.getDeskripsiKos();
                        fasilitas_kos = kosDetail.getFasilitasKos();
                        nama_pemilik = kosDetail.getNamaLengkapPemilik();
                        foto_pemilik = kosDetail.getFotoPemilik();
                        no_handphone_pemilik = kosDetail.getNoHandphonePemilik();
                        waktu_post_kos = kosDetail.getWaktuPost();
                        stok_kamar_kos = kosDetail.getStokKamar();
                        id_pemilik = Integer.parseInt(kosDetail.getIdPemilik());
                        nama_lengkap_pemilik = kosDetail.getNamaLengkapPemilik();
                        status_kos = kosDetail.getStatusKos();
                        latitude_kos = kosDetail.getLatitude();
                        longitude_kos = kosDetail.getLongitude();
                        altitude_kos = kosDetail.getAltitude();
                        rating_kos = kosDetail.getRatingKos();

                        Picasso.with(DetailKosPemilikActivity.this)
                                .load(ServerConfig.KOS_IMAGE+foto_kos)
                                .into(iv_foto_kos);
                        System.out.println(ServerConfig.KOS_IMAGE+foto_kos);
                        tv_nama_kos.setText(nama_kos);
                        tv_harga_kos.setText("Rp. " +harga_kos+" / Bulan");
                        tv_jenis_kos.setText(jenis_kos);
                        tv_alamat_kos.setText(alamat_kos);
                        tv_deskripsi_kos.setText(deskripsi_kos);
                        tv_fasilitas_kos.setText(fasilitas_kos);
                        tv_nama_pemilik.setText("Nama Pemilik: " +nama_pemilik);
                        tv_waktu_post_kos.setText("Update pada " +waktu_post_kos);
                        tv_stok_kamar_kos.setText("Tersedia " +stok_kamar_kos+ " Kamar");
                        Picasso.with(DetailKosPemilikActivity.this)
                                .load(ServerConfig.PROFIL_IMAGE+foto_pemilik)
                                .into(iv_foto_pemilik);
                        System.out.println(ServerConfig.PROFIL_IMAGE+foto_pemilik);
                        tv_no_handphone_pemilik.setText("No. Handphone " +no_handphone_pemilik);

                        if (!sessionManager.isLoggedIn()) {
                            fab_edit.setVisibility(View.GONE);
                            fab_trash_1.setVisibility(View.GONE);

                        }else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)){
                            fab_edit.setVisibility(View.GONE);
                            fab_trash_1.setVisibility(View.GONE);

                        }

                        else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)){
                            fab_edit.setVisibility(View.VISIBLE);
                            fab_trash_1.setVisibility(View.VISIBLE);
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Tidak ada data dengan id" +EXTRA_ID_KOS_PEMILIK, Toast.LENGTH_LONG).show();
                    }

                }

//                else {
//                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<ResponseKosDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKosPemilikActivity.this);

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda ingin mengedit data kos anda?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                System.out.println("Tampilkan"+id_pemilik);

                                Intent intent = new Intent(DetailKosPemilikActivity.this, EditKosActivity.class);
                                intent.putExtra(EditKosActivity.KEY_ID, id_pemilik);
                                intent.putExtra(EditKosActivity.KEY_NAMA_LENGKAP_PEMILIK, nama_lengkap_pemilik);
                                intent.putExtra(EditKosActivity.KEY_FOTO_PEMILIK, foto_pemilik);
                                intent.putExtra(EditKosActivity.KEY_ID_KOS, id_kos);
                                intent.putExtra(EditKosActivity.KEY_NAMA_KOS, nama_kos);
                                intent.putExtra(EditKosActivity.KEY_FOTO, foto_kos);
                                intent.putExtra(EditKosActivity.KEY_FOTO_2, foto_kos_2);
                                intent.putExtra(EditKosActivity.KEY_FOTO_3, foto_kos_3);
                                intent.putExtra(EditKosActivity.KEY_STATUS_KOS, status_kos);
                                intent.putExtra(EditKosActivity.KEY_ALAMAT_KOS, alamat_kos);
                                intent.putExtra(EditKosActivity.KEY_HARGA_KOS, harga_kos);
                                intent.putExtra(EditKosActivity.KEY_DESKRIPSI_KOS, deskripsi_kos);
                                intent.putExtra(EditKosActivity.KEY_FASILITAS_KOS, fasilitas_kos);
                                intent.putExtra(EditKosActivity.KEY_NO_HANDPHONE_PEMILIK, no_handphone_pemilik);
                                intent.putExtra(EditKosActivity.KEY_WAKTU_POST_KOS, waktu_post_kos);
                                intent.putExtra(EditKosActivity.KEY_LATITUDE_KOS, latitude_kos);
                                intent.putExtra(EditKosActivity.KEY_LONGITUDE_KOS, longitude_kos);
                                intent.putExtra(EditKosActivity.KEY_ALTITUDE_KOS, altitude_kos);
                                intent.putExtra(EditKosActivity.KEY_RATING_KOS, rating_kos);
                                intent.putExtra(EditKosActivity.KEY_JENIS_KOS, jenis_kos);
                                intent.putExtra(EditKosActivity.KEY_STOK_KAMAR_KOS, stok_kamar_kos);
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKosPemilikActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin menghapus pemesanan kos ini?");

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda yakin ingin menghapus kos ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                action_hapus_kos(id_kos);
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKosPemilikActivity.this);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Galeri Foto");
                alertDialogBuilder.setIcon(R.drawable.ic_icon);

                LayoutInflater inflater = getLayoutInflater();

                View dialogView = inflater.inflate(R.layout.foto_full_dialog, null);

                ImageView iv_foto_1 = dialogView.findViewById(R.id.iv_foto_1);
                ImageView iv_foto_2 = dialogView.findViewById(R.id.iv_foto_2);
                ImageView iv_foto_3 = dialogView.findViewById(R.id.iv_foto_3);


                Picasso.with(DetailKosPemilikActivity.this)
                        .load(ServerConfig.KOS_IMAGE + foto_kos)
                        .into(iv_foto_1);

                Picasso.with(DetailKosPemilikActivity.this)
                        .load(ServerConfig.KOS_IMAGE + foto_kos_2)
                        .into(iv_foto_2);

                Picasso.with(DetailKosPemilikActivity.this)
                        .load(ServerConfig.KOS_IMAGE + foto_kos_3)
                        .into(iv_foto_3);

                alertDialogBuilder.setView(dialogView);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    private void action_hapus_kos(String id_kos) {
        apiInterface.hapusKos(id_kos).enqueue(new Callback<ResponseHapusKos>() {
            @Override
            public void onResponse(Call<ResponseHapusKos> call, Response<ResponseHapusKos> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_kos_pemilik = new Intent(DetailKosPemilikActivity.this, DaftarKontrakanKosPemilikActivity.class);
                        detail_kos_pemilik.putExtra(DaftarKontrakanKosPemilikActivity.TAG_ID, response.body().getData().getIdKos());
                        startActivity(detail_kos_pemilik);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHapusKos> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void goToDaftarKontrakanKosPemilikActivity() {
        //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
        Intent intent = new Intent(DetailKosPemilikActivity.this, DaftarKontrakanKosPemilikActivity.class);
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

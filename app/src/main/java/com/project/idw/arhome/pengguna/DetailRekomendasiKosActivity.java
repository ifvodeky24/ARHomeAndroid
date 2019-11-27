package com.project.idw.arhome.pengguna;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;

import com.project.idw.arhome.R;
import com.project.idw.arhome.common.DetailPemesananKosActivity;
import com.project.idw.arhome.common.MessageActivity;
import com.project.idw.arhome.common.PemesananKontrakanKosActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.KosDetail;
import com.project.idw.arhome.model.RekomendasiKoses;
import com.project.idw.arhome.response.ResponseBookingKos;
import com.project.idw.arhome.response.ResponseKosDetail;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRekomendasiKosActivity extends AppCompatActivity {

    public static final String TAG_ID ="id_kos";

    ImageView iv_foto_kos;
    TextView tv_stok_kamar_kos, tv_waktu_post_kos, tv_jenis_kos, tv_alamat_kos, tv_nama_kos, tv_harga_kos, tv_deskripsi_kos;
    TextView tv_fasilitas_kos, tv_nama_pemilik, tv_no_handphone_pemilik, tv_rb_rekomendasi_kos;
    CircleImageView iv_foto_pemilik;
    Button btn_booking_kos;
    FloatingActionButton fab_call, fab_chat, fab_maps;
    RatingBar rb_rekomendasi_kos;
    TextView tv_foto_full;

    int id_pemilik;
    String nama_lengkap_pemilik, id_kos, nama_kos, harga_kos, foto_kos, jenis_kos, alamat_kos, foto_kos_2, foto_kos_3;
    String foto_pemilik, deskripsi_kos, fasilitas_kos, nama_pemilik;
    String no_handphone_pemilik, waktu_post_kos, stok_kamar_kos, status_kos, latitude_kos, longitude_kos, altitude_kos, rating_kos, review_kos;

    ApiInterface apiInterface;

    SessionManager sessionManager;

    String goolgeMap = "com.google.android.apps.maps"; // identitas package aplikasi google masps android
    Uri gmmIntentUri;
    Intent mapIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Detail Rekomendasi Kos");
        setContentView(R.layout.activity_detail_rekomendasi_kos);

        iv_foto_kos = findViewById(R.id.iv_foto_kos);
        tv_stok_kamar_kos = findViewById(R.id.tv_stok_kamar_kos);
        tv_waktu_post_kos = findViewById(R.id.tv_waktu_post_kos);
        tv_jenis_kos = findViewById(R.id.tv_jenis_kos);
        tv_alamat_kos = findViewById(R.id.tv_alamat_kos);
        tv_nama_kos = findViewById(R.id.tv_nama_kos);
        tv_harga_kos = findViewById(R.id.tv_harga_kos);
        tv_deskripsi_kos = findViewById(R.id.tv_deskripsi_kos);
        tv_fasilitas_kos = findViewById(R.id.tv_fasilitas_kos);
        tv_nama_pemilik = findViewById(R.id.tv_nama_pemilik);
        tv_no_handphone_pemilik = findViewById(R.id.tv_no_handphone_pemilik);
        iv_foto_pemilik = findViewById(R.id.iv_foto_pemilik);
        btn_booking_kos = findViewById(R.id.btn_booking_kos);
        fab_call = findViewById(R.id.fab_call);
        fab_chat = findViewById(R.id.fab_chat);
        fab_maps = findViewById(R.id.fab_maps_rekomendasi);
        tv_rb_rekomendasi_kos = findViewById(R.id.tv_rb_rekomendasi_kos);
        rb_rekomendasi_kos = findViewById(R.id.rb_rekomendasi_kos);
        tv_foto_full = findViewById(R.id.tv_foto_full);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);

        int EXTRA_ID_KOS = getIntent().getIntExtra(RekomendasiKoses.TAG_ID, 0);

        apiInterface.kosById(String.valueOf(EXTRA_ID_KOS)).enqueue(new Callback<ResponseKosDetail>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call<ResponseKosDetail> call, Response<ResponseKosDetail> response) {
                if (response.isSuccessful()) {
                    KosDetail kosDetail = response.body().getMaster().get(0);
                    id_kos = kosDetail.getIdKos();
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

                    Picasso.with(DetailRekomendasiKosActivity.this)
                            .load(ServerConfig.KOS_IMAGE + foto_kos)
                            .into(iv_foto_kos);
                    System.out.println(ServerConfig.KOS_IMAGE + foto_kos);
                    tv_nama_kos.setText(nama_kos);
                    tv_harga_kos.setText("Rp. " + harga_kos + " / Bulan");
                    tv_jenis_kos.setText(jenis_kos);
                    tv_alamat_kos.setText(alamat_kos);
                    tv_deskripsi_kos.setText(deskripsi_kos);
                    tv_fasilitas_kos.setText(fasilitas_kos);
                    tv_nama_pemilik.setText("Nama Pemilik: " + nama_pemilik);
                    tv_waktu_post_kos.setText("Update pada " + waktu_post_kos);
                    tv_stok_kamar_kos.setText("Tersedia " + stok_kamar_kos + " Kamar");
                    tv_rb_rekomendasi_kos.setText("Skor: " +rating_kos);
                    rb_rekomendasi_kos.setRating(Float.parseFloat(rating_kos));

                    Picasso.with(DetailRekomendasiKosActivity.this)
                            .load(ServerConfig.PROFIL_IMAGE + foto_pemilik)
                            .into(iv_foto_pemilik);
                    System.out.println(ServerConfig.PROFIL_IMAGE + foto_pemilik);
                    tv_no_handphone_pemilik.setText("No. Handphone " + no_handphone_pemilik);

                    if (!sessionManager.isLoggedIn()) {
                        btn_booking_kos.setVisibility(View.GONE);
                        fab_chat.setVisibility(View.GONE);
                        fab_call.setVisibility(View.GONE);
                        fab_maps.setVisibility(View.GONE);

                    } else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)) {
                        btn_booking_kos.setVisibility(View.VISIBLE);
                        fab_chat.setVisibility(View.VISIBLE);
                        fab_call.setVisibility(View.VISIBLE);
                        fab_maps.setVisibility(View.VISIBLE);

                        btn_booking_kos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailRekomendasiKosActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin memesanan kos ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin memesanan kos ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_booking_kos(id_kos);
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
                    } else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)) {
                        btn_booking_kos.setVisibility(View.GONE);
                        fab_chat.setVisibility(View.GONE);
                        fab_call.setVisibility(View.GONE);
                        fab_maps.setVisibility(View.GONE);
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

        fab_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailRekomendasiKosActivity.this);

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda ingin mengechat pemilik ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("Tampilkan" + id_pemilik);
                                Intent intent = new Intent(DetailRekomendasiKosActivity.this, MessageActivity.class);
                                intent.putExtra(MessageActivity.KEY_ID, id_pemilik);
                                intent.putExtra(MessageActivity.KEY_NAMA_LENGKAP_PEMILIK, nama_lengkap_pemilik);
                                intent.putExtra(MessageActivity.KEY_FOTO_PEMILIK, foto_pemilik);
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

        fab_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailRekomendasiKosActivity.this);

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda ingin menelepon pemilik kos ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                System.out.println("Tampilkan saja " + no_handphone_pemilik);

                                Intent intent = new Intent(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + no_handphone_pemilik)));
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

        fab_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailRekomendasiKosActivity.this);

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda ingin melihat rute menuju kontrakan ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Buat Uri dari intent string. Gunakan hasilnya untuk membuat Intent.
                                gmmIntentUri = Uri.parse("google.navigation:q=" + latitude_kos+","+longitude_kos);

                                // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
                                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                                // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
                                mapIntent.setPackage(goolgeMap);

                                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(mapIntent);
                                } else {
                                    Toast.makeText(DetailRekomendasiKosActivity.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                                            Toast.LENGTH_LONG).show();
                                }
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailRekomendasiKosActivity.this);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Galeri Foto");
                alertDialogBuilder.setIcon(R.drawable.ic_icon);

                LayoutInflater inflater = getLayoutInflater();

                View dialogView = inflater.inflate(R.layout.foto_full_dialog, null);

                ImageView iv_foto_1 = dialogView.findViewById(R.id.iv_foto_1);
                ImageView iv_foto_2 = dialogView.findViewById(R.id.iv_foto_2);
                ImageView iv_foto_3 = dialogView.findViewById(R.id.iv_foto_3);


                Picasso.with(DetailRekomendasiKosActivity.this)
                        .load(ServerConfig.KOS_IMAGE + foto_kos)
                        .into(iv_foto_1);

                Picasso.with(DetailRekomendasiKosActivity.this)
                        .load(ServerConfig.KOS_IMAGE + foto_kos_2)
                        .into(iv_foto_2);

                Picasso.with(DetailRekomendasiKosActivity.this)
                        .load(ServerConfig.KOS_IMAGE + foto_kos_3)
                        .into(iv_foto_3);

                alertDialogBuilder.setView(dialogView);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

        private void action_booking_kos(String id_kos) {
            apiInterface.bookingKos(sessionManager.getLoginDetail().get(SessionManager.ID), id_kos).enqueue(new Callback<ResponseBookingKos>() {
                @Override
                public void onResponse(Call<ResponseBookingKos> call, Response<ResponseBookingKos> response) {
                    if (response.isSuccessful()){
                        if (response.body().getCode()==1){
                            Intent detail_pemesanan_kos = new Intent(DetailRekomendasiKosActivity.this, PemesananKontrakanKosActivity.class);
                            detail_pemesanan_kos.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKos());
                            startActivity(detail_pemesanan_kos);
                            finish();
                        }
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBookingKos> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }

        private void goToDaftarKontrakanKosActivity() {
            //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
            Intent intent = new Intent(DetailRekomendasiKosActivity.this, RekomendasiKontrakanKosActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            goToDaftarKontrakanKosActivity();
        }
}

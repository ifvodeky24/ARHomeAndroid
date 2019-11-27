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
import android.widget.Toast;

import android.support.design.widget.FloatingActionButton;

import com.project.idw.arhome.PoiBrowserActivity2;
import com.project.idw.arhome.R;
import com.project.idw.arhome.common.DetailKontrakanActivity;
import com.project.idw.arhome.common.DetailPemesananKontrakanActivity;
import com.project.idw.arhome.common.MessageActivity;
import com.project.idw.arhome.common.PemesananKontrakanKosActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.KontrakanDetail;
import com.project.idw.arhome.model.RekomendasiKontrakans;
import com.project.idw.arhome.response.ResponseBookingKontrakan;
import com.project.idw.arhome.response.ResponseKontrakanDetail;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKontrakanARActivity extends AppCompatActivity {

    public static final String TAG_ID ="id_kontrakan";

    ImageView iv_foto_kontrakan;
    RatingBar rb_rekomendasi_kos;
    TextView tv_rb_rekomendasi_kos, tv_status_kontrakan, tv_waktu_post_kontrakan, tv_alamat_kontrakan, tv_nama_kontrakan;
    TextView tv_harga_kontrakan, tv_deskripsi_kontrakan, tv_fasilitas_kontrakan, tv_nama_pemilik, tv_no_handphone_pemilik;
    CircleImageView iv_foto_pemilik;
    Button btn_booking;
    FloatingActionButton fab_chat, fab_call, fab_maps;
    TextView tv_foto_full;

    ApiInterface apiInterface;

    SessionManager sessionManager;

    String goolgeMap = "com.google.android.apps.maps"; // identitas package aplikasi google masps android
    Uri gmmIntentUri;
    Intent mapIntent;

    int id_pemilik;
    String nama_lengkap_pemilik, id_kontrakan, nama_kontrakan, foto_kontrakan, status_kontrakan, alamat_kontrakan;
    String harga_kontrakan, deskripsi_kontrakan, fasilitas_kontrakan, nama_pemilik, foto_pemilik, no_handphone_pemilik, waktu_post_kontrakan;
    String latitude_kontrakan, longitude_kontrakan, altitude_kontrakan, rating_kontrakan;
    String foto_kontrakan_2, foto_kontrakan_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Detail Rekomendasi Kontrakan");
        setContentView(R.layout.activity_detail_kontrakan_ar);

        iv_foto_kontrakan = findViewById(R.id.iv_foto_kontrakan);
        fab_chat = findViewById(R.id.fab_chat_rekomendasi);
        fab_call = findViewById(R.id.fab_call_rekomendasi);
        fab_maps = findViewById(R.id.fab_maps_rekomendasi);
        rb_rekomendasi_kos = findViewById(R.id.rb_rekomendasi_kos);
        tv_rb_rekomendasi_kos = findViewById(R.id.tv_rb_rekomendasi_kos);
        tv_status_kontrakan = findViewById(R.id.tv_status_kontrakan);
        tv_waktu_post_kontrakan = findViewById(R.id.tv_waktu_post_kontrakan);
        tv_alamat_kontrakan = findViewById(R.id.tv_alamat_kontrakan);
        tv_nama_kontrakan = findViewById(R.id.tv_nama_kontrakan);
        tv_harga_kontrakan = findViewById(R.id.tv_harga_kontrakan);
        tv_deskripsi_kontrakan = findViewById(R.id.tv_deskripsi_kontrakan);
        tv_fasilitas_kontrakan = findViewById(R.id.tv_fasilitas_kontrakan);
        tv_nama_pemilik = findViewById(R.id.tv_nama_pemilik);
        tv_no_handphone_pemilik = findViewById(R.id.tv_no_handphone_pemilik);
        iv_foto_pemilik = findViewById(R.id.iv_foto_pemilik);
        btn_booking = findViewById(R.id.btn_booking);
        tv_foto_full = findViewById(R.id.tv_foto_full);

        apiInterface  = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);

        final String EXTRA_ID_KONTRAKAN = getIntent().getStringExtra(RekomendasiKontrakans.TAG_ID);

        apiInterface.kontrakanById(String.valueOf(EXTRA_ID_KONTRAKAN)).enqueue(new Callback<ResponseKontrakanDetail>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call<ResponseKontrakanDetail> call, Response<ResponseKontrakanDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMaster().size()>0){
                        KontrakanDetail kontrakanDetail = response.body().getMaster().get(0);
                        id_kontrakan = kontrakanDetail.getIdKontrakan();
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
                        id_pemilik = Integer.parseInt(kontrakanDetail.getIdPemilik());
                        nama_lengkap_pemilik = kontrakanDetail.getNamaLengkapPemilik();
                        latitude_kontrakan = kontrakanDetail.getLatitude();
                        longitude_kontrakan = kontrakanDetail.getLongitude();
                        rating_kontrakan = kontrakanDetail.getRatingKontrakan();
                        altitude_kontrakan = kontrakanDetail.getAltitude();

                        Picasso.with(DetailKontrakanARActivity.this)
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
                        Picasso.with(DetailKontrakanARActivity.this)
                                .load(ServerConfig.PROFIL_IMAGE + foto_pemilik)
                                .into(iv_foto_pemilik);
                        System.out.println(ServerConfig.PROFIL_IMAGE + foto_pemilik);
                        tv_no_handphone_pemilik.setText("No. Handphone " + no_handphone_pemilik);
                        tv_rb_rekomendasi_kos.setText("Skor: " + rating_kontrakan);
                        rb_rekomendasi_kos.setRating(Float.parseFloat(rating_kontrakan));

                        if (!sessionManager.isLoggedIn()) {
                            btn_booking.setVisibility(View.GONE);
                            fab_chat.setVisibility(View.GONE);
                            fab_call.setVisibility(View.GONE);
                            fab_maps.setVisibility(View.GONE);

                        } else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)) {
                            btn_booking.setVisibility(View.VISIBLE);
                            fab_chat.setVisibility(View.VISIBLE);
                            fab_call.setVisibility(View.VISIBLE);
                            fab_maps.setVisibility(View.VISIBLE);
                            btn_booking.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKontrakanARActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin memesanan kontrakan ini?");

                                    //set pesan dari dialog
                                    alertDialogBuilder
                                            .setMessage("Apakah anda yakin ingin memesanan kontrakan ini?")
                                            .setCancelable(false)
                                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    action_booking(id_kontrakan);
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
                        } else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)){
                            btn_booking.setVisibility(View.GONE);
                            fab_chat.setVisibility(View.GONE);
                            fab_call.setVisibility(View.GONE);
                            fab_maps.setVisibility(View.GONE);
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Tidak ada data dengan id" +EXTRA_ID_KONTRAKAN, Toast.LENGTH_LONG).show();
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

        fab_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKontrakanARActivity.this);

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda ingin mengechat pemilik ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("Tampilkan ini "+id_pemilik);

                                Intent intent = new Intent(DetailKontrakanARActivity.this, MessageActivity.class);
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKontrakanARActivity.this);

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda ingin menelepon pemilik kontrakan ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                System.out.println("Tampilkan saja "+no_handphone_pemilik);

                                Intent intent = new Intent(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+no_handphone_pemilik)));
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKontrakanARActivity.this);

                //set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Apakah anda ingin melihat rute menuju kontrakan ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Buat Uri dari intent string. Gunakan hasilnya untuk membuat Intent.
                                gmmIntentUri = Uri.parse("google.navigation:q=" + latitude_kontrakan+","+longitude_kontrakan);

                                // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
                                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                                // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
                                mapIntent.setPackage(goolgeMap);

                                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(mapIntent);
                                } else {
                                    Toast.makeText(DetailKontrakanARActivity.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKontrakanARActivity.this);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Galeri Foto");
                alertDialogBuilder.setIcon(R.drawable.ic_icon);

                LayoutInflater inflater = getLayoutInflater();

                View dialogView = inflater.inflate(R.layout.foto_full_dialog, null);

                ImageView iv_foto_1 = dialogView.findViewById(R.id.iv_foto_1);
                ImageView iv_foto_2 = dialogView.findViewById(R.id.iv_foto_2);
                ImageView iv_foto_3 = dialogView.findViewById(R.id.iv_foto_3);


                Picasso.with(DetailKontrakanARActivity.this)
                        .load(ServerConfig.KONTRAKAN_IMAGE + foto_kontrakan)
                        .into(iv_foto_1);

                Picasso.with(DetailKontrakanARActivity.this)
                        .load(ServerConfig.KONTRAKAN_IMAGE + foto_kontrakan_2)
                        .into(iv_foto_2);

                Picasso.with(DetailKontrakanARActivity.this)
                        .load(ServerConfig.KONTRAKAN_IMAGE + foto_kontrakan_3)
                        .into(iv_foto_3);

                alertDialogBuilder.setView(dialogView);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void action_booking(String id_kontrakan) {
//        Toast.makeText(getApplicationContext(), "Melakukan Booking", Toast.LENGTH_SHORT).show();
        apiInterface.bookingKontrakan(sessionManager.getLoginDetail().get(SessionManager.ID), id_kontrakan).enqueue(new Callback<ResponseBookingKontrakan>() {
            @Override
            public void onResponse(Call<ResponseBookingKontrakan> call, Response<ResponseBookingKontrakan> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_pemesanan_kontrakan = new Intent(DetailKontrakanARActivity.this, PemesananKontrakanKosActivity.class);
                        detail_pemesanan_kontrakan.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKontrakan());
                        startActivity(detail_pemesanan_kontrakan);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBookingKontrakan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void goToPoiBrowserActivity2() {
        //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
        Intent intent = new Intent(DetailKontrakanARActivity.this, PoiBrowserActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToPoiBrowserActivity2();
    }
}

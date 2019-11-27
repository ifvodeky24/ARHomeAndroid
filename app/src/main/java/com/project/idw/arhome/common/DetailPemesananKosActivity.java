package com.project.idw.arhome.common;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.idw.arhome.R;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.PemesananKosDetail;
import com.project.idw.arhome.response.ResponseBatalBookingKos;
import com.project.idw.arhome.response.ResponseDalamPemesananKos;
import com.project.idw.arhome.response.ResponseHapusPemesananKos;
import com.project.idw.arhome.response.ResponsePemesananKosDetail;
import com.project.idw.arhome.response.ResponseSelesaiKos;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPemesananKosActivity extends AppCompatActivity {

    Button btn_update_dalam_pemesanan_kos, btn_update_selesai_kos, btn_hapus_pemesanan_kos, btn_batal_pemesanan_kos;
    TextView tv_nama_kos, tv_status_kos, tv_stok_kamar_kos, tv_waktu_pemesanan_kos, tv_jenis_kos, tv_alamat_kos;
    TextView tv_nama_pengguna, tv_no_handphone_pengguna, tv_nama_pemilik, tv_no_handphone_pemilik, tv_harga_kos, tv_id_pemesanan_kos;
    ImageView iv_foto_pemesanan_kos, iv_foto_pengguna, iv_foto_pemilik;

    public static final String TAG_ID = "id";

    ApiInterface apiInterface;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemesanan_kos);

        btn_update_dalam_pemesanan_kos = findViewById(R.id.btn_update_dalam_pemesanan_kos);
        btn_update_selesai_kos = findViewById(R.id.btn_update_selesai_kos);
        btn_batal_pemesanan_kos = findViewById(R.id.btn_batal_pemesanan_kos);
        tv_nama_kos = findViewById(R.id.tv_nama_kos);
        tv_status_kos = findViewById(R.id.tv_status_kos);
        tv_id_pemesanan_kos = findViewById(R.id.tv_id_pemesanan_kos);
        iv_foto_pemesanan_kos = findViewById(R.id.iv_foto_pemesanan_kos);
        btn_hapus_pemesanan_kos = findViewById(R.id.btn_hapus_pemesanan_kos);
        tv_stok_kamar_kos = findViewById(R.id.tv_stok_kamar_kos);
        tv_jenis_kos = findViewById(R.id.tv_jenis_kos);
        tv_alamat_kos = findViewById(R.id.tv_alamat_kos);
        tv_nama_pengguna = findViewById(R.id.tv_nama_pengguna);
        tv_no_handphone_pengguna = findViewById(R.id.tv_no_handphone_pengguna);
        tv_nama_pemilik = findViewById(R.id.tv_nama_pemilik);
        tv_no_handphone_pemilik = findViewById(R.id.tv_no_handphone_pemilik);
        tv_harga_kos = findViewById(R.id.tv_harga_kos);
        iv_foto_pengguna = findViewById(R.id.iv_foto_pengguna);
        iv_foto_pemilik = findViewById(R.id.iv_foto_pemilik);

        apiInterface  = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);

        String EXTRA_ID_PEMESANAN_KOS = getIntent().getStringExtra(PemesananKosDetail.TAG_ID);

//        Toast.makeText(getApplicationContext(), "EXTRA_ID_PEMESANAN_KOS: "+EXTRA_ID_PEMESANAN_KOS, Toast.LENGTH_SHORT).show();
        apiInterface.pemesananKosById(EXTRA_ID_PEMESANAN_KOS).enqueue(new Callback<ResponsePemesananKosDetail>() {
            @Override
            public void onResponse(Call<ResponsePemesananKosDetail> call, Response<ResponsePemesananKosDetail> response) {
                if (response.isSuccessful()){
                    PemesananKosDetail pemesananKosDetail = response.body().getMaster().get(0);
                    final String id_pemesanan_kos = String.valueOf(pemesananKosDetail.getIdPemesananKos());
                    String nama_kos = pemesananKosDetail.getNamaKos();
                    String status_kos = pemesananKosDetail.getStatus();
                    String stok_kamar_kos = pemesananKosDetail.getStokKamarKos();
                    String jenis_kos = pemesananKosDetail.getJenisKos();
                    String alamat_kos = pemesananKosDetail.getAlamatKos();
                    String nama_pengguna = pemesananKosDetail.getNamaLengkapPengguna();
                    String no_handphone_pengguna = pemesananKosDetail.getNoHandphonePengguna();
                    String nama_pemilik = pemesananKosDetail.getNamaLengkapPemilik();
                    String no_handphone_pemilik = pemesananKosDetail.getNoHandphonePemilik();
                    String harga_kos = pemesananKosDetail.getHarga();
                    String foto_pengguna = pemesananKosDetail.getFotoPengguna();
                    String foto_pemilik = pemesananKosDetail.getFotoPemilik();
                    String foto_kos = pemesananKosDetail.getFotoKos();


                    tv_nama_kos.setText(nama_kos);
                    tv_status_kos.setText(status_kos);
                    tv_id_pemesanan_kos.setText("Pemesanan Kos ke- "+id_pemesanan_kos);
                    tv_nama_pengguna.setText(nama_pengguna);
                    tv_no_handphone_pengguna.setText(no_handphone_pengguna);
                    tv_nama_pemilik.setText(nama_pemilik);
                    tv_no_handphone_pemilik.setText(no_handphone_pemilik);
                    tv_harga_kos.setText("Rp. "+harga_kos+" / Bulan");
                    tv_stok_kamar_kos.setText("Tersedia "+stok_kamar_kos+ " Kamar");
                    tv_jenis_kos.setText(jenis_kos);
                    tv_alamat_kos.setText(alamat_kos);

                    Picasso.with(DetailPemesananKosActivity.this)
                            .load(ServerConfig.KOS_IMAGE+foto_kos)
                            .into(iv_foto_pemesanan_kos);
                    System.out.println(ServerConfig.KOS_IMAGE+foto_kos);

                    Picasso.with(DetailPemesananKosActivity.this)
                            .load(ServerConfig.PROFIL_IMAGE+foto_pengguna)
                            .into(iv_foto_pengguna);
                    System.out.println(ServerConfig.PROFIL_IMAGE+foto_pengguna);

                    Picasso.with(DetailPemesananKosActivity.this)
                            .load(ServerConfig.PROFIL_IMAGE+foto_pemilik)
                            .into(iv_foto_pemilik);
                    System.out.println(ServerConfig.PROFIL_IMAGE+foto_pemilik);

                    if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)&&
                            status_kos.equalsIgnoreCase("booking")){

                        //Ketika tombol update dalam pemesanan ditekan
                        btn_update_dalam_pemesanan_kos.setVisibility(View.VISIBLE);
                        btn_update_dalam_pemesanan_kos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKosActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin meneriwa penyewa kos ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin meneriwa penyewa kos ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_dalam_pemesanan_kos(id_pemesanan_kos);
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

                        //Tombol untuk membatalkan booking kos
                        btn_batal_pemesanan_kos.setVisibility(View.VISIBLE);
                        btn_batal_pemesanan_kos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKosActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin iingin membatalkan booking kos dari pemesanan ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin membatalkan booking kos dari pemesanan ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_batal_pemesanan_kos(id_pemesanan_kos);
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

                    }else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)&&
                            status_kos.equalsIgnoreCase("booking")){

                        //Tombol untuk membatalkan booking kos
                        btn_batal_pemesanan_kos.setVisibility(View.VISIBLE);
                        btn_batal_pemesanan_kos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKosActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin iingin membatalkan booking kos dari pemesanan ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin membatalkan booking kos dari pemesanan ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_batal_pemesanan_kos(id_pemesanan_kos);
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

                    }else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)&&
                            status_kos.equalsIgnoreCase("dalam pemesanan")){

                        //Ketika tombol update selesai pemesanan ditekan
                        btn_update_selesai_kos.setVisibility(View.VISIBLE);
                        btn_update_selesai_kos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKosActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin memberhentikan penyewa kos ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin memberhentikan penyewa kos ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_selesai_kos(id_pemesanan_kos);
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

                    }else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)&&
                            status_kos.equalsIgnoreCase("selesai")){

                        //Ketika tombol hapus pemesanan ditekan
                        btn_hapus_pemesanan_kos.setVisibility(View.VISIBLE);
                        btn_hapus_pemesanan_kos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKosActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin menghapus pemesanan kos ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin menghapus pemesanan kos ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_hapus_pemesanan_kos(id_pemesanan_kos);
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

                    }else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)&&
                            status_kos.equalsIgnoreCase("selesai")){

                        //Ketika tombol hapus pemesanan ditekan
                        btn_hapus_pemesanan_kos.setVisibility(View.VISIBLE);
                        btn_hapus_pemesanan_kos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKosActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin menghapus pemesanan kos ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin menghapus pemesanan kos ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_hapus_pemesanan_kos(id_pemesanan_kos);
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

                       //apabila login sebagai pemili dan pengguna tapi tidak dalam kondisi diatas
                    } else{
                        btn_update_dalam_pemesanan_kos.setVisibility(View.GONE);
                        btn_update_selesai_kos.setVisibility(View.GONE);
                        btn_hapus_pemesanan_kos.setVisibility(View.GONE);
                        btn_batal_pemesanan_kos.setVisibility(View.GONE);
                    }


                }

//                else {
//                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<ResponsePemesananKosDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void action_batal_pemesanan_kos(String id_pemesanan_kos) {
        Toast.makeText(getApplicationContext(), "Pemesanan Kos berhasil dibatalkan", Toast.LENGTH_SHORT).show();
        apiInterface.batalBookingKos(id_pemesanan_kos).enqueue(new Callback<ResponseBatalBookingKos>() {
            @Override
            public void onResponse(Call<ResponseBatalBookingKos> call, Response<ResponseBatalBookingKos> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_pemesanan_kontrakan = new Intent(DetailPemesananKosActivity.this, PemesananKontrakanKosActivity.class);
                        detail_pemesanan_kontrakan.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKos());
                        startActivity(detail_pemesanan_kontrakan);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBatalBookingKos> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void action_hapus_pemesanan_kos(String id_pemesanan_kos) {
        Toast.makeText(getApplicationContext(), "Pemesanan Kos berhasil dihapus", Toast.LENGTH_SHORT).show();
        apiInterface.hapusPemesananKos(id_pemesanan_kos).enqueue(new Callback<ResponseHapusPemesananKos>() {
            @Override
            public void onResponse(Call<ResponseHapusPemesananKos> call, Response<ResponseHapusPemesananKos> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_pemesanan_kos = new Intent(DetailPemesananKosActivity.this, PemesananKontrakanKosActivity.class);
                        detail_pemesanan_kos.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKos());
                        startActivity(detail_pemesanan_kos);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHapusPemesananKos> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void action_selesai_kos(String id_pemesanan_kos) {

        Toast.makeText(getApplicationContext(), "Status Pemesanan Menjadi Selesai", Toast.LENGTH_SHORT).show();
        apiInterface.selesaiKos(id_pemesanan_kos).enqueue(new Callback<ResponseSelesaiKos>() {
            @Override
            public void onResponse(Call<ResponseSelesaiKos> call, Response<ResponseSelesaiKos> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_pemesanan_kos = new Intent(DetailPemesananKosActivity.this, PemesananKontrakanKosActivity.class);
                        detail_pemesanan_kos.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKos());
                        startActivity(detail_pemesanan_kos);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSelesaiKos> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void action_dalam_pemesanan_kos(String id_pemesanan_kos) {
        Toast.makeText(getApplicationContext(), "Status Pemesanan Menjadi Dalam Pemesanan", Toast.LENGTH_SHORT).show();
        apiInterface.dalamPemesananKos(id_pemesanan_kos).enqueue(new Callback<ResponseDalamPemesananKos>() {
            @Override
            public void onResponse(Call<ResponseDalamPemesananKos> call, Response<ResponseDalamPemesananKos> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_pemesanan_kontrakan = new Intent(DetailPemesananKosActivity.this, PemesananKontrakanKosActivity.class);
                        detail_pemesanan_kontrakan.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKos());
                        startActivity(detail_pemesanan_kontrakan);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDalamPemesananKos> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void goToPemesananKontrakanKosActivity() {
        //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
        Intent intent = new Intent(DetailPemesananKosActivity.this, PemesananKontrakanKosActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToPemesananKontrakanKosActivity();
    }
}

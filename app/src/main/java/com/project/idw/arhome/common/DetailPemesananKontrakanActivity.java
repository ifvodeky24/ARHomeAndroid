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
import com.project.idw.arhome.model.PemesananKontrakanDetail;
import com.project.idw.arhome.response.ResponseBatalBookingKontrakan;
import com.project.idw.arhome.response.ResponseDalamPemesananKontrakan;
import com.project.idw.arhome.response.ResponseHapusPemesananKontrakan;
import com.project.idw.arhome.response.ResponsePemesananKontrakanDetail;
import com.project.idw.arhome.response.ResponseSelesaiKontrakan;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPemesananKontrakanActivity extends AppCompatActivity {

    Button btn_update_dalam_pemesanan_kontrakan, btn_update_selesai_kontrakan,btn_hapus_pemesanan_kontrakan, btn_batal_pemesanan_kontrakan;
    TextView tv_nama_kontrakan, tv_status_kontrakan, tv_harga_kontrakan,tv_id_pemesanan_kontrakan, tv_nama_pengguna, tv_no_handphone_pengguna, tv_nama_pemilik, tv_no_handphone_pemilik;
    TextView tv_alamat_kontrakan;
    ImageView iv_foto_pemesanan_kontrakan, iv_foto_pengguna, iv_foto_pemilik;

    public static final String TAG_ID = "id";

    ApiInterface apiInterface;

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemesanan_kontrakan);

        btn_update_dalam_pemesanan_kontrakan = findViewById(R.id.btn_update_dalam_pemesanan_kontrakan);
        btn_update_selesai_kontrakan = findViewById(R.id.btn_update_selesai_kontrakan);
        tv_nama_kontrakan = findViewById(R.id.tv_nama_kontrakan);
        tv_status_kontrakan = findViewById(R.id.tv_status_kontrakan);
        iv_foto_pemesanan_kontrakan = findViewById(R.id.iv_foto_pemesanan_kontrakan);
        btn_hapus_pemesanan_kontrakan = findViewById(R.id.btn_hapus_pemesanan_kontrakan);
        btn_batal_pemesanan_kontrakan = findViewById(R.id.btn_batal_pemesanan_kontrakan);
        tv_harga_kontrakan = findViewById(R.id.tv_harga_kontrakan);
        tv_id_pemesanan_kontrakan = findViewById(R.id.tv_id_pemesanan_kontrakan);
        iv_foto_pengguna = findViewById(R.id.iv_foto_pengguna);
        tv_nama_pengguna = findViewById(R.id.tv_nama_pengguna);
        tv_no_handphone_pengguna = findViewById(R.id.tv_no_handphone_pengguna);
        tv_nama_pemilik = findViewById(R.id.tv_nama_pemilik);
        tv_no_handphone_pemilik = findViewById(R.id.tv_no_handphone_pemilik);
        iv_foto_pemilik = findViewById(R.id.iv_foto_pemilik);
        tv_alamat_kontrakan = findViewById(R.id.tv_alamat_kontrakan);

        apiInterface  = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);

        String EXTRA_ID_PEMESANAN_KONTRAKAN = getIntent().getStringExtra(PemesananKontrakanDetail.TAG_ID);

//        Toast.makeText(getApplicationContext(), "EXTRA_ID_PEMESANAN_KONTRAKAN: "+EXTRA_ID_PEMESANAN_KONTRAKAN, Toast.LENGTH_SHORT).show();
        apiInterface.pemesananKontrakanById(EXTRA_ID_PEMESANAN_KONTRAKAN).enqueue(new Callback<ResponsePemesananKontrakanDetail>() {
            @Override
            public void onResponse(Call<ResponsePemesananKontrakanDetail> call, Response<ResponsePemesananKontrakanDetail> response) {
                if (response.isSuccessful()){
                    PemesananKontrakanDetail pemesananKontrakanDetail = response.body().getMaster().get(0);
                    final String id_pemesanan_kontrakan = String.valueOf(pemesananKontrakanDetail.getIdPemesananKontrakan());
                    String status_kontrakan = pemesananKontrakanDetail.getStatus();
                    String nama_kontrakan = pemesananKontrakanDetail.getNamaKontrakan();
                    String harga_kontrakan = pemesananKontrakanDetail.getHarga();
                    String foto_kontrakan = pemesananKontrakanDetail.getFotoKontrakan();
                    String foto_pengguna = pemesananKontrakanDetail.getFotoPengguna();
                    String foto_pemilik = pemesananKontrakanDetail.getFotoPemilik();
                    String nama_pemesan = pemesananKontrakanDetail.getNamaLengkapPengguna();
                    String no_handphone_pemesan = pemesananKontrakanDetail.getNoHandphonePengguna();
                    String nama_pemilik = pemesananKontrakanDetail.getNamaLengkapPemilik();
                    String no_handphone_pemilik = pemesananKontrakanDetail.getNoHandphonePemilik();
                    String alamat_kontrakan = pemesananKontrakanDetail.getAlamatKontrakan();


                    tv_nama_kontrakan.setText(nama_kontrakan);
                    tv_status_kontrakan.setText(status_kontrakan);
                    tv_harga_kontrakan.setText("Rp. "+harga_kontrakan+" / Tahun");
                    tv_id_pemesanan_kontrakan.setText("Pemesanan Kontrakan ke- "+id_pemesanan_kontrakan);
                    tv_nama_pengguna.setText(nama_pemesan);
                    tv_no_handphone_pengguna.setText(no_handphone_pemesan);
                    tv_nama_pemilik.setText(nama_pemilik);
                    tv_no_handphone_pemilik.setText(no_handphone_pemilik);
                    tv_alamat_kontrakan.setText(alamat_kontrakan);

                    Picasso.with(DetailPemesananKontrakanActivity.this)
                            .load(ServerConfig.KONTRAKAN_IMAGE+foto_kontrakan)
                            .into(iv_foto_pemesanan_kontrakan);
                    System.out.println(ServerConfig.KONTRAKAN_IMAGE+foto_kontrakan);

                    Picasso.with(DetailPemesananKontrakanActivity.this)
                            .load(ServerConfig.PROFIL_IMAGE+foto_pengguna)
                            .into(iv_foto_pengguna);
                    System.out.println(ServerConfig.PROFIL_IMAGE+foto_pengguna);

                    Picasso.with(DetailPemesananKontrakanActivity.this)
                            .load(ServerConfig.PROFIL_IMAGE+foto_pemilik)
                            .into(iv_foto_pemilik);
                    System.out.println(ServerConfig.PROFIL_IMAGE+foto_pemilik);
//
                    if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)&&
                            status_kontrakan.equalsIgnoreCase("booking")){

                        //Tombol saat update pemesanan ditekan
                        btn_update_dalam_pemesanan_kontrakan.setVisibility(View.VISIBLE);
                        btn_update_dalam_pemesanan_kontrakan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKontrakanActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin meneriwa penyewa kontrakan ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin meneriwa penyewa kontrakan ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_dalam_pemesanan_kontrakan(id_pemesanan_kontrakan);
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


                        //Tombol untuk membatalkan booking kontrakan
                        btn_batal_pemesanan_kontrakan.setVisibility(View.VISIBLE);
                        btn_batal_pemesanan_kontrakan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKontrakanActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin iingin membatalkan booking kontrakan dari pemesanan ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin membatalkan booking kontrakan dari pemesanan ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_batal_pemesanan_kontrakan(id_pemesanan_kontrakan);
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
                            status_kontrakan.equalsIgnoreCase("booking")){

                        //Tombol untuk membatalkan booking kontrakan
                        btn_batal_pemesanan_kontrakan.setVisibility(View.VISIBLE);
                        btn_batal_pemesanan_kontrakan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKontrakanActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin iingin membatalkan booking kontrakan dari pemesanan ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin membatalkan booking kontrakan dari pemesanan ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_batal_pemesanan_kontrakan(id_pemesanan_kontrakan);
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
                            status_kontrakan.equalsIgnoreCase("dalam pemesanan")){

                        //Tombol saat update selesai ditekan
                        btn_update_selesai_kontrakan.setVisibility(View.VISIBLE);
                        btn_update_selesai_kontrakan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKontrakanActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin memberhentikan penyewa kontrakan ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin memberhentikan penyewa kontrakan ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_selesai_kontrakan(id_pemesanan_kontrakan);
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
                            status_kontrakan.equalsIgnoreCase("selesai")){

                        //Ketika tombol hapus pemesanan ditekan
                        btn_hapus_pemesanan_kontrakan.setVisibility(View.VISIBLE);
                        btn_hapus_pemesanan_kontrakan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKontrakanActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin menghapus pemesanan kontrakan ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin menghapus pemesanan kontrakan ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_hapus_pemesanan_kontrakan(id_pemesanan_kontrakan);
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
                            status_kontrakan.equalsIgnoreCase("selesai")){

                        //Ketika tombol hapus pemesanan ditekan
                        btn_hapus_pemesanan_kontrakan.setVisibility(View.VISIBLE);
                        btn_hapus_pemesanan_kontrakan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailPemesananKontrakanActivity.this);

//                                alertDialogBuilder.setTitle("Apakah anda yakin ingin menghapus pemesanan kontrakan ini?");

                                //set pesan dari dialog
                                alertDialogBuilder
                                        .setMessage("Apakah anda yakin ingin menghapus pemesanan kontrakan ini?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                action_hapus_pemesanan_kontrakan(id_pemesanan_kontrakan);
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
                        btn_update_dalam_pemesanan_kontrakan.setVisibility(View.GONE);
                        btn_update_selesai_kontrakan.setVisibility(View.GONE);
                        btn_hapus_pemesanan_kontrakan.setVisibility(View.GONE);
                        btn_batal_pemesanan_kontrakan.setVisibility(View.GONE);
                    }


                }

//                else {
//                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<ResponsePemesananKontrakanDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void action_batal_pemesanan_kontrakan(String id_pemesanan_kontrakan) {
        Toast.makeText(getApplicationContext(), "Pemesanan Kontrakan berhasil dibatalkan", Toast.LENGTH_SHORT).show();
        apiInterface.batalBookingKontrakan(id_pemesanan_kontrakan).enqueue(new Callback<ResponseBatalBookingKontrakan>() {
            @Override
            public void onResponse(Call<ResponseBatalBookingKontrakan> call, Response<ResponseBatalBookingKontrakan> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_pemesanan_kontrakan = new Intent(DetailPemesananKontrakanActivity.this, PemesananKontrakanKosActivity.class);
                        detail_pemesanan_kontrakan.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKontrakan());
                        startActivity(detail_pemesanan_kontrakan);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBatalBookingKontrakan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void action_hapus_pemesanan_kontrakan(String id_pemesanan_kontrakan) {
        Toast.makeText(getApplicationContext(), "Pemesanan Kontrakan berhasil dihapus", Toast.LENGTH_SHORT).show();
        apiInterface.hapusPemesananKontrakan(id_pemesanan_kontrakan).enqueue(new Callback<ResponseHapusPemesananKontrakan>() {
            @Override
            public void onResponse(Call<ResponseHapusPemesananKontrakan> call, Response<ResponseHapusPemesananKontrakan> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_pemesanan_kontrakan = new Intent(DetailPemesananKontrakanActivity.this, PemesananKontrakanKosActivity.class);
                        detail_pemesanan_kontrakan.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKontrakan());
                        startActivity(detail_pemesanan_kontrakan);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHapusPemesananKontrakan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void action_selesai_kontrakan(String id_pemesanan_kontrakan) {
        Toast.makeText(getApplicationContext(), "Melakukan update selesai", Toast.LENGTH_SHORT).show();
        apiInterface.selesaiKontrakan(id_pemesanan_kontrakan).enqueue(new Callback<ResponseSelesaiKontrakan>() {
            @Override
            public void onResponse(Call<ResponseSelesaiKontrakan> call, Response<ResponseSelesaiKontrakan> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_pemesanan_kontrakan = new Intent(DetailPemesananKontrakanActivity.this, PemesananKontrakanKosActivity.class);
                        detail_pemesanan_kontrakan.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKontrakan());
                        startActivity(detail_pemesanan_kontrakan);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSelesaiKontrakan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void action_dalam_pemesanan_kontrakan(String id_pemesanan_kontrakan) {
//        Toast.makeText(getApplicationContext(), "Melakukan update dalam pemesanan", Toast.LENGTH_SHORT).show();
        apiInterface.dalamPemesananKontrakan(id_pemesanan_kontrakan).enqueue(new Callback<ResponseDalamPemesananKontrakan>() {
            @Override
            public void onResponse(Call<ResponseDalamPemesananKontrakan> call, Response<ResponseDalamPemesananKontrakan> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Intent detail_pemesanan_kontrakan = new Intent(DetailPemesananKontrakanActivity.this, PemesananKontrakanKosActivity.class);
                        detail_pemesanan_kontrakan.putExtra(PemesananKontrakanKosActivity.TAG_ID, response.body().getData().getIdPemesananKontrakan());
                        startActivity(detail_pemesanan_kontrakan);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDalamPemesananKontrakan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void goToPemesananKontrakanKosActivity() {
        //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
        Intent intent = new Intent(DetailPemesananKontrakanActivity.this, PemesananKontrakanKosActivity.class);
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

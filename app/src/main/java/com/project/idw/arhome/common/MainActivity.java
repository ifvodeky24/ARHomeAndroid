package com.project.idw.arhome.common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.idw.arhome.PoiBrowserActivity;
import com.project.idw.arhome.PoiBrowserActivity2;
import com.project.idw.arhome.R;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.fragment.BerandaFragment;
import com.project.idw.arhome.fragment.ChatFragment;
import com.project.idw.arhome.fragment.PengaturanFragment;
import com.project.idw.arhome.fragment.ProfilFragment;
import com.project.idw.arhome.pemilik_iklan.LoginPemilikActivity;
import com.project.idw.arhome.pemilik_iklan.TambahKontrakanActivity;
import com.project.idw.arhome.pemilik_iklan.TambahKosActivity;
import com.project.idw.arhome.pengguna.LoginPenggunaActivity;
import com.project.idw.arhome.pengguna.RekomendasiKontrakanKosActivity;
import com.project.idw.arhome.response.ResponseUncheckRatingKontrakan;
import com.project.idw.arhome.response.ResponseUncheckRatingKos;
import com.project.idw.arhome.response.ResponseUpdateRatingReviewKontrakan;
import com.project.idw.arhome.response.ResponseUpdateRatingReviewKos;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG_ID = "id";
    private static final int PERMISSION_REQUEST_CODE = 200;

    public SessionManager sessionManager;

    MenuItem lacak_lokasi, daftar_kontrakan_kos, rekomendasi, tambah_kontrakan_kos, riwayat_pemesanan, tentang;
    MenuItem navigation_beranda, navigation_profil, navigation_chat, navigation_pengaturan;
    CircleImageView foto_profil;
    TextView tv_nama_lengkap, tv_email;
    Menu menu, menu_bottom;
    ApiInterface apiInterface;

    String email;
    Uri foto;

    private FirebaseAuth mAuth;

    ApiInterface apiService;

    public String nama_kos, nama_kontrakan;
    public String id_pemesanan_kos, id_pemesanan_kontrakan;

    EditText edt_komentar;
    TextView tv_rate;
    TextView tv_nama_kontrakan_kos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("       ARHome");
        setContentView(R.layout.activity_main_activity);

        //fungsi dari firebase
        mAuth = FirebaseAuth.getInstance();

        apiService = ApiClient.getClient().create(ApiInterface.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Fungsi untuk Bottom Navigation
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new BerandaFragment());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.ic_icon);
        }
        toolbar.setSubtitle("Pencarian Kontrakan & Kos");

        //Fungsi untuk Navigation Drawer
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        foto_profil = header.findViewById(R.id.iv_foto_profil);
        tv_nama_lengkap = header.findViewById(R.id.tv_nama_lengkap);
        tv_email = header.findViewById(R.id.tv_email);

        sessionManager = new SessionManager(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        if (!sessionManager.isLoggedIn() & mAuth.getCurrentUser() == null){
            //fungsi saat klik dari tulisan klik disini login header navigation view
            tv_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_login_dialog();
                }
            });

            //fungsi saat tulisan anda belum login di klik
            tv_nama_lengkap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_login_dialog();
                }
            });

            //fungsi saat foto profil di klik
            foto_profil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_login_dialog();
                }
            });
        }

        menu = navigationView.getMenu();

        lacak_lokasi = menu.findItem(R.id.nav_lacak_lokasi);
        daftar_kontrakan_kos = menu.findItem(R.id.nav_daftar_kontrakan_kos);
        rekomendasi = menu.findItem(R.id.nav_rekomendasi);
        tambah_kontrakan_kos = menu.findItem(R.id.nav_tambah_kontrakan_kos);
        riwayat_pemesanan = menu.findItem(R.id.nav_riwayat_pemesanan);
        tentang = menu.findItem(R.id.nav_tentang);

        menu_bottom = navigation.getMenu();

        navigation_beranda = menu_bottom.findItem(R.id.navigation_beranda);
        navigation_profil = menu_bottom.findItem(R.id.navigation_profil);
        navigation_chat  = menu_bottom.findItem(R.id.navigation_chat);
//        navigation_notifikasi = menu_bottom.findItem(R.id.navigation_notifikasi);
        navigation_pengaturan = menu_bottom.findItem(R.id.navigation_pengaturan);

        if (sessionManager.isLoggedIn()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (Objects.requireNonNull(sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN)).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)) {
                    displayMenuPengguna();

                    String id_pengguna = sessionManager.getLoginDetail().get("id");

                    System.out.println("cek id" + id_pengguna);

    //                tampilkanRatingReview(id_pengguna);
                    System.out.println("Sudah Login, Tampilkan menu pengguna");
                } else if (Objects.requireNonNull(sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN)).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)) {
                    displayMenuPemilik();
                    System.out.println("Sudah Login, Tampilkan menu pemilik");
                }
            }
        } else {
            displayMenuBelumLogin();
            System.out.println("Belum Login");
        }

        //fungsi dari firebase
        if (mAuth.getCurrentUser() != null) {
            displayMenuPenggunaFirebase();
            sessionManager.isLoggedIn();
            sessionManager.getLoginDetail().get(SessionManager.LEVEL_PENGGUNA);

            System.out.println("Sudah Login, Tampilkan menu pengguna");
        }
    }

    private void tampilkanRatingReview(String id_pengguna) {
        apiService.checkUnratingKontrakan(id_pengguna).enqueue(new Callback<ResponseUncheckRatingKontrakan>() {
            @Override
            public void onResponse(Call<ResponseUncheckRatingKontrakan> call, Response<ResponseUncheckRatingKontrakan> response) {
            if (response.isSuccessful()){

                if (response.body() != null) {
                    if (response.body().getCode()==1){

                        nama_kontrakan = response.body().getData().getNamaKontrakan();
                        id_pemesanan_kontrakan = String.valueOf(response.body().getData().getIdPemesananKontrakan());

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Rating & Review");
                        alertDialogBuilder.setIcon(R.drawable.ic_icon);

                        LayoutInflater inflater = getLayoutInflater();

                        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.rating_dialog, null);

                        alertDialogBuilder.setView(dialogView);

                        edt_komentar = dialogView.findViewById(R.id.edt_komentar);
                        RatingBar rt_bar= dialogView.findViewById(R.id.rt_bar);
                        tv_rate = dialogView.findViewById(R.id.tv_rate);
                        tv_nama_kontrakan_kos = dialogView.findViewById(R.id.tv_nama_kontrakan_kos);

                        tv_nama_kontrakan_kos.setText(nama_kontrakan);


                        rt_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            @Override
                            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                tv_rate.setText(String.valueOf(rating));
                            }
                        });

                        alertDialogBuilder.setPositiveButton("Kirim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Rating : "+tv_rate.getText()+ " Komentar :" + edt_komentar.getText(), Toast.LENGTH_LONG).show();
                                kirim_rating_review_kontrakan(id_pemesanan_kontrakan);
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }else {
//                        Toast.makeText(getApplicationContext(), "Tidak Ada review dan rating Kontrakan yang belum ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }

            }else {
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUncheckRatingKontrakan> call, Throwable t) {
                t.printStackTrace();
            }
        });

        apiService.checkUnratingKos(id_pengguna).enqueue(new Callback<ResponseUncheckRatingKos>() {
            @Override
            public void onResponse(Call<ResponseUncheckRatingKos> call, Response<ResponseUncheckRatingKos> response) {
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if (response.body().getCode()==1){

                            nama_kos = response.body().getData().getNamaKos();
                            id_pemesanan_kos = String.valueOf(response.body().getData().getIdPemesananKos());

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            alertDialogBuilder.setCancelable(true);
                            alertDialogBuilder.setTitle("Rating & Review");
                            alertDialogBuilder.setIcon(R.drawable.ic_icon);

                            LayoutInflater inflater = getLayoutInflater();

                            View dialogView = inflater.inflate(R.layout.rating_dialog, null);

                            alertDialogBuilder.setView(dialogView);

                            edt_komentar = dialogView.findViewById(R.id.edt_komentar);
                            RatingBar rt_bar= dialogView.findViewById(R.id.rt_bar);
                            tv_rate = dialogView.findViewById(R.id.tv_rate);
                            tv_nama_kontrakan_kos = dialogView.findViewById(R.id.tv_nama_kontrakan_kos);

                            tv_nama_kontrakan_kos.setText(nama_kos);

                            rt_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                @Override
                                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                    tv_rate.setText("Skor: "+ String.valueOf(rating));
                                }
                            });

                            alertDialogBuilder.setPositiveButton("Kirim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "Rating : "+tv_rate.getText()+ " Komentar :" + edt_komentar.getText()+ "id"+ id_pemesanan_kos, Toast.LENGTH_LONG).show();
                                    kirim_rating_review_kos(id_pemesanan_kos);
                                }

                            });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }else {
    //                        Toast.makeText(getApplicationContext(), "Tidak Ada review dan rating kos yang belum ditambahkan", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUncheckRatingKos> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void kirim_rating_review_kos(String id_pemesanan_kos) {

        String review = edt_komentar.getText().toString();
        String rating = tv_rate.getText().toString();

        apiService.updateRatingReviewKos(id_pemesanan_kos, review, rating).enqueue(new Callback<ResponseUpdateRatingReviewKos>() {
            @Override
            public void onResponse(Call<ResponseUpdateRatingReviewKos> call, Response<ResponseUpdateRatingReviewKos> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        if (response.body().getCode()==1){
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateRatingReviewKos> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void kirim_rating_review_kontrakan(String id_pemesanan_kontrakan) {

        String review = edt_komentar.getText().toString();
        String rating = tv_rate.getText().toString();

        apiService.updateRatingReviewKontrakan(id_pemesanan_kontrakan, review, rating).enqueue(new Callback<ResponseUpdateRatingReviewKontrakan>() {
            @Override
            public void onResponse(Call<ResponseUpdateRatingReviewKontrakan> call, Response<ResponseUpdateRatingReviewKontrakan> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateRatingReviewKontrakan> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    //Fungsi saat foto dan teks untuk login di klik
    private void show_login_dialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle("ARHome Login");
        alertDialogBuilder.setIcon(R.drawable.ic_icon);

        LayoutInflater inflater = getLayoutInflater();

        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.kategori_login_dialog, null);

        alertDialogBuilder.setView(dialogView);

        Button btn_pemilik = dialogView.findViewById(R.id.btn_pemilik);
        Button btn_pengguna = dialogView.findViewById(R.id.btn_pengguna);

        btn_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login_pemilik = new Intent(MainActivity.this, LoginPemilikActivity.class);
                finish();
                startActivity(intent_login_pemilik);
            }
        });

        btn_pengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login_pengguna = new Intent(MainActivity.this, LoginPenggunaActivity.class);
                finish();
                startActivity(intent_login_pengguna);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //menu awal saat sebelum login
    private void displayMenuBelumLogin() {
        lacak_lokasi.setVisible(true);
        daftar_kontrakan_kos.setVisible(true);
        rekomendasi.setVisible(false);
        tambah_kontrakan_kos.setVisible(false);
        riwayat_pemesanan.setVisible(false);
        tentang.setVisible(true);
        tv_nama_lengkap.setText("Anda Belum Login");
        tv_email.setText("Klik disini untuk login");

        System.out.println("load foto " + ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO));
        Picasso.with(MainActivity.this)
                .load(R.drawable.ic_akun_image)
                .into(foto_profil);

        //fungsi menu bagian bottom navigation
        navigation_profil.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                harus_login();

                return true;
            }
        });

        navigation_chat.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                harus_login();

                return true;
            }
        });

        navigation_pengaturan.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                harus_login();

                return true;
            }
        });
    }


    //Tampilkan dialog harus login
    private void harus_login() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        //set pesan dari dialog
        alertDialogBuilder
                .setMessage("Anda Harus Login Terlebih Dahulu")
                .setCancelable(false)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        show_login_dialog();
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

    //menu setelah login sebagai pengguna Firebase
    private void displayMenuPenggunaFirebase() {
        lacak_lokasi.setVisible(true);
        daftar_kontrakan_kos.setVisible(true);
        rekomendasi.setVisible(true);
        tambah_kontrakan_kos.setVisible(false);
        riwayat_pemesanan.setVisible(true);
        tentang.setVisible(true);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            foto = user.getPhotoUrl();

            tv_email.setText(user.getEmail());
            tv_nama_lengkap.setText(user.getDisplayName());

            Picasso.with(MainActivity.this)
                    .load(foto)
                    .into(foto_profil);
        }


    }


    //menu setelah login sebagai pengguna
    private void displayMenuPengguna() {
        lacak_lokasi.setVisible(true);
        daftar_kontrakan_kos.setVisible(true);
        rekomendasi.setVisible(true);
        tambah_kontrakan_kos.setVisible(false);
        riwayat_pemesanan.setVisible(true);
        tentang.setVisible(true);
        tv_nama_lengkap.setText(sessionManager.getLoginDetail().get(SessionManager.NAMA_LENGKAP));
        tv_email.setText(sessionManager.getLoginDetail().get(SessionManager.EMAIL));

        System.out.println("load foto " + ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO));
        Picasso.with(MainActivity.this)
                .load(ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO))
                .into(foto_profil);

    }

    //menu setelah login sebagai pemilik
    private void displayMenuPemilik() {
        lacak_lokasi.setVisible(false);
        daftar_kontrakan_kos.setVisible(true);
        rekomendasi.setVisible(false);
        tambah_kontrakan_kos.setVisible(true);
        riwayat_pemesanan.setVisible(true);
        tentang.setVisible(true);
        tv_nama_lengkap.setText(sessionManager.getLoginDetail().get(SessionManager.NAMA_LENGKAP));
        tv_email.setText(sessionManager.getLoginDetail().get(SessionManager.EMAIL));

        System.out.println("load foto " + ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO));
        Picasso.with(MainActivity.this)
                .load(ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO))
                .into(foto_profil);
    }

    //Fungsi saat tombol back di handphone ditekan
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Fungsi untuk menu titik tiga di sebelah kanan atas
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity, menu);
        if (sessionManager.isLoggedIn()) {
            // visible
            menu.findItem(R.id.action_logout).setVisible(true);
        } else if (mAuth.getCurrentUser() != null) {
            menu.findItem(R.id.action_logout_firebase).setVisible(true);
        }else {
            // gone
            menu.findItem(R.id.action_logout).setVisible(false);
            menu.findItem(R.id.action_logout_firebase).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //fungsi logout pada menu bagian titik 3 kanan atas
        if (id == R.id.action_logout) {
            sessionManager.logout();
            Intent intent_logout = new Intent(MainActivity.this, MainActivity.class);
            intent_logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            finish();
            startActivity(intent_logout);
            displayMenuBelumLogin();
            Toast.makeText(getApplicationContext(), "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();

            //fungsi logout pada menu bagian titik 3 kanan atas dari firebase
        } else if (id == R.id.action_logout_firebase) {
            mAuth.signOut();
            sessionManager.logout();

            Intent intent_logout = new Intent(MainActivity.this, MainActivity.class);
            intent_logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            finish();
            startActivity(intent_logout);
            displayMenuBelumLogin();
            Toast.makeText(getApplicationContext(), "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    //Fungsi untuk Bottom Navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()){
                case R.id.navigation_beranda:
                    fragment = new BerandaFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_profil:
                    fragment = new ProfilFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_chat:
                    fragment = new ChatFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_pengaturan:
                    fragment = new PengaturanFragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };


    //Fungsi untuk Navigation Drawer
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Fungsi menu pada navigasi drawer
        int id = item.getItemId();

         if (id == R.id.nav_lacak_lokasi) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setCancelable(true);
            alertDialogBuilder.setTitle("ARHome");
            alertDialogBuilder.setIcon(R.drawable.ic_icon);

            final LayoutInflater inflater = getLayoutInflater();

            @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.kategori_lacak_lokasi_dialog, null);

            alertDialogBuilder.setView(dialogView);

            Button btn_lacak_augmented_reality_kontrakan = dialogView.findViewById(R.id.btn_lacak_augmented_reality_kontrakan);
            Button btn_lacak_augmented_reality_kos = dialogView.findViewById(R.id.btn_lacak_augmented_reality_kos);
            Button btn_lacak_maps = dialogView.findViewById(R.id.btn_lacak_maps);

            btn_lacak_augmented_reality_kontrakan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PackageManager pm = getPackageManager();
                    boolean compass = pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS);
                    boolean accelerometer = pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER);

                    if (!compass && !accelerometer) {
                        Intent intent_lacak_maps = new Intent(MainActivity.this, LacakMapsActivity.class);
                        startActivity(intent_lacak_maps);
                        Toast.makeText(getApplicationContext(), "Tidak dapat menjalankan AR karena handphone anda tidak memiliki kompas dan akselerometer", Toast.LENGTH_LONG).show();

                    } else if (!compass) {
                        Intent intent_lacak_maps = new Intent(MainActivity.this, LacakMapsActivity.class);
                        startActivity(intent_lacak_maps);
                        Toast.makeText(getApplicationContext(), "Tidak dapat menjalankan AR karena handphone anda tidak memiliki kompas", Toast.LENGTH_LONG).show();

                    } else if (!accelerometer) {
                        Intent intent_lacak_maps = new Intent(MainActivity.this, LacakMapsActivity.class);
                        startActivity(intent_lacak_maps);
                        Toast.makeText(getApplicationContext(), "Tidak dapat menjalankan AR karena handphone anda tidak memiliki akselerometer", Toast.LENGTH_LONG).show();

                    } else {
                        if (checkpermission()){
                            Intent intent_lacak_kontrakan = new Intent(MainActivity.this, PoiBrowserActivity2.class);
                            startActivity(intent_lacak_kontrakan);
                        }else {
                            requestPermission();
                        }
                    }
                }
            });

             btn_lacak_augmented_reality_kos.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     PackageManager pm = getPackageManager();
                     boolean compass = pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS);
                     boolean accelerometer = pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER);

                     if (!compass && !accelerometer) {
                         Intent intent_lacak_maps = new Intent(MainActivity.this, LacakMapsActivity.class);
                         startActivity(intent_lacak_maps);
                         Toast.makeText(getApplicationContext(), "Tidak dapat menjalankan AR karena handphone anda tidak memiliki kompas dan akselerometer", Toast.LENGTH_LONG).show();

                     } else if (!compass) {
                         Intent intent_lacak_maps = new Intent(MainActivity.this, LacakMapsActivity.class);
                         startActivity(intent_lacak_maps);
                         Toast.makeText(getApplicationContext(), "Tidak dapat menjalankan AR karena handphone anda tidak memiliki kompas", Toast.LENGTH_LONG).show();

                     } else if (!accelerometer) {
                         Intent intent_lacak_maps = new Intent(MainActivity.this, LacakMapsActivity.class);
                         startActivity(intent_lacak_maps);
                         Toast.makeText(getApplicationContext(), "Tidak dapat menjalankan AR karena handphone anda tidak memiliki akselerometer", Toast.LENGTH_LONG).show();

                     } else {
                         if (checkpermission()){
                             Intent intent_lacak_kos = new Intent(MainActivity.this, PoiBrowserActivity.class);
                             startActivity(intent_lacak_kos);
                         }else {
                             requestPermission();
                         }
                     }
                 }
             });

            btn_lacak_maps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_lacak_maps = new Intent(MainActivity.this, LacakMapsActivity.class);
                    startActivity(intent_lacak_maps);
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else if (id == R.id.nav_daftar_kontrakan_kos) {
            Intent intent_daftar_kontrakan_kos = new Intent(MainActivity.this, DaftarKontrakanKosActivity.class);
            startActivity(intent_daftar_kontrakan_kos);

        }else if (id == R.id.nav_rekomendasi){
            Intent intent_rekomendasi_kontrakan_kos = new Intent(MainActivity.this, RekomendasiKontrakanKosActivity.class);
            startActivity(intent_rekomendasi_kontrakan_kos);

        } else if (id == R.id.nav_tambah_kontrakan_kos) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setCancelable(true);
            alertDialogBuilder.setTitle("ARHome");
            alertDialogBuilder.setIcon(R.drawable.ic_icon);

            LayoutInflater inflater = getLayoutInflater();

            @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.kategori_tambah_kontrakan_kos_dialog, null);

            alertDialogBuilder.setView(dialogView);

            Button btn_tambah_kontrakan = dialogView.findViewById(R.id.btn_tambah_kontrakan);
            Button btn_tambah_kos = dialogView.findViewById(R.id.btn_tambah_kos);

            btn_tambah_kontrakan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_tambah_kontrakan = new Intent(MainActivity.this, TambahKontrakanActivity.class);
                    startActivity(intent_tambah_kontrakan);
                }
            });

            btn_tambah_kos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_tambah_kos = new Intent(MainActivity.this, TambahKosActivity.class);
                    startActivity(intent_tambah_kos);
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else if (id == R.id.nav_riwayat_pemesanan){
            Intent intent_pemesanan_kontrakan_kos = new Intent(MainActivity.this, PemesananKontrakanKosActivity.class);
            startActivity(intent_pemesanan_kontrakan_kos);

        }  else if (id == R.id.nav_tentang){
            Intent intent_tentang = new Intent(MainActivity.this, TentangActivity.class);
            startActivity(intent_tentang);
        }else if (id == R.id.nav_petunjuk){
             Intent intent = new Intent(MainActivity.this, PetunjukPenggunaanActivity.class);
             startActivity(intent);
         }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean checkpermission() {
        // Permission is not granted
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                // main logic
            } else {
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        showMessageOKCancel(
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermission();
                                    }
                                });
                    }
                }
            }
        }
    }

    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("You need to allow access permissions")
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}


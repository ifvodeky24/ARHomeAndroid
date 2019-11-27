package com.project.idw.arhome.pemilik_iklan;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.project.idw.arhome.R;
import com.project.idw.arhome.common.MainActivity;
import com.project.idw.arhome.config.ConnectionDetector;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class EditKosActivity extends AppCompatActivity implements
        OnMapReadyCallback {
    private SessionManager sessionManager;
    ProgressDialog progressDialog;
    private File file;
    Bitmap FixBitmap,FixBitmap2, FixBitmap3;
    ByteArrayOutputStream byteArrayOutputStream, byteArrayOutputStream2, byteArrayOutputStream3;

    String ServerUploadPath = ServerConfig.UPDATE_FOTO_KOS_TEST;

    byte[] byteArray, byteArray2, byteArray3;

    String ConvertImage, ConvertImage2, ConvertImage3;

    HttpURLConnection httpURLConnection ;

    URL url;

    OutputStream outputStream;

    BufferedWriter bufferedWriter ;

    int RC ;

    BufferedReader bufferedReader ;

    StringBuilder stringBuilder;

    boolean check = true;

    private ConnectionDetector cd;
    ApiInterface apiInterface;

    GoogleMap mMap;

    private final int PLACE_PICKER_REQUEST = 12;
    final static private String TAG="EditKosActivity";
    private LatLng latLng;

    EditText et_nama_kos, et_deskripsi_kos, et_alamat_kos, et_fasilitas_kos;
    EditText et_harga_kos, et_stok_kamar_kos;
    Button btn_update_kos;
    Button btn_hide_map, btn_show_map;
    ImageView iv_preview_1, iv_preview_2, iv_preview_3;
    TextView tv_latitude_kos, tv_longitude_kos;
    RadioGroup rg_jenis_kos;
    RadioButton rb_pria, rb_wanita;
    ImageButton btn_foto_kos, btn_foto_kos_2, btn_foto_kos_3;

    public static final String KEY_ID = "id_pemilik";
    public static final String KEY_NAMA_LENGKAP_PEMILIK = "nama_lengkap_pemilik";
    public static final String KEY_FOTO_PEMILIK = "foto_pemilik";
    public static final String KEY_ID_KOS = "id_kos";
    public static final String KEY_NAMA_KOS = "nama";
    public static final String KEY_ALAMAT_KOS = "alamat";
    public static final String KEY_STATUS_KOS = "status";
    public static final String KEY_HARGA_KOS = "harga";
    public static final String KEY_DESKRIPSI_KOS = "deskripsi";
    public static final String KEY_FASILITAS_KOS = "fasilitas";
    public static final String KEY_NO_HANDPHONE_PEMILIK = "no_handphone_pemilik";
    public static final String KEY_WAKTU_POST_KOS = "waktu_post_kontrakan";
    public static final String KEY_LATITUDE_KOS = "latitude";
    public static final String KEY_LONGITUDE_KOS = "longitude";
    public static final String KEY_ALTITUDE_KOS = "altitude";
    public static final String KEY_RATING_KOS = "rating";
    public static final String KEY_FOTO = "foto";
    public static final String KEY_FOTO_2 = "foto_2";
    public static final String KEY_FOTO_3 = "foto_3";
    public static final String KEY_JENIS_KOS = "jenis_kos";
    public static final String KEY_STOK_KAMAR_KOS = "stok_kamar";

    Intent intent;

    String id_pemilik, nama_lengkap_pemilik, foto_pemilik, id_kos, nama_kos, foto_kos, foto_kos_2, foto_kos_3;
    String alamat_kos, status_kos, harga_kos, deskripsi_kos, fasilitas_kos, no_handphone_pemilik;
    String waktu_post_kos, latitude_kos, longitude_kos, altitude_kos, rating_kos, jenis_kos, stok_kamar;
    int selectedRadioButtonID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kos);

        et_nama_kos = findViewById(R.id.et_nama_kos);
        et_deskripsi_kos = findViewById(R.id.et_deskripsi_kos);
        et_alamat_kos = findViewById(R.id.et_alamat_kos);
        et_fasilitas_kos = findViewById(R.id.et_fasilitas_kos);
        btn_foto_kos = findViewById(R.id.btn_foto_kos);
        iv_preview_1 = findViewById(R.id.iv_preview_1);
        btn_foto_kos_2 = findViewById(R.id.btn_foto_kos_2);
        iv_preview_2 = findViewById(R.id.iv_preview_2);
        btn_foto_kos_3 = findViewById(R.id.btn_foto_kos_3);
        iv_preview_3 = findViewById(R.id.iv_preview_3);
        tv_latitude_kos = findViewById(R.id.tv_latitude_kos);
        tv_longitude_kos = findViewById(R.id.tv_longitude_kos);
        et_harga_kos = findViewById(R.id.et_harga_kos);
        et_stok_kamar_kos = findViewById(R.id.et_stok_kamar_kos);
        btn_update_kos = findViewById(R.id.btn_update_kos);
        rg_jenis_kos = findViewById(R.id.rg_jenis_kos);
        rb_pria = findViewById(R.id.rb_pria);
        rb_wanita = findViewById(R.id.rb_wanita);
        btn_hide_map = findViewById(R.id.btn_hide_map);
        btn_show_map = findViewById(R.id.btn_show_map);

        byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream2 = new ByteArrayOutputStream();
        byteArrayOutputStream3 = new ByteArrayOutputStream();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);

        intent = getIntent();

        id_pemilik = sessionManager.getLoginDetail().get(SessionManager.ID);
        nama_lengkap_pemilik = intent.getStringExtra(KEY_NAMA_LENGKAP_PEMILIK);
        foto_pemilik = intent.getStringExtra(KEY_FOTO_PEMILIK);
        id_kos = intent.getStringExtra(KEY_ID_KOS);
        nama_kos = intent.getStringExtra(KEY_NAMA_KOS);
//        foto_kos = intent.getStringExtra(KEY_FOTO);
//        foto_kos_2 = intent.getStringExtra(KEY_FOTO_2);
//        foto_kos_3 = intent.getStringExtra(KEY_FOTO_3);
        alamat_kos = intent.getStringExtra(KEY_ALAMAT_KOS);
        status_kos = intent.getStringExtra(KEY_STATUS_KOS);
        harga_kos = intent.getStringExtra(KEY_HARGA_KOS);
        deskripsi_kos = intent.getStringExtra(KEY_DESKRIPSI_KOS);
        fasilitas_kos = intent.getStringExtra(KEY_FASILITAS_KOS);
        no_handphone_pemilik = intent.getStringExtra(KEY_NO_HANDPHONE_PEMILIK);
        waktu_post_kos = intent.getStringExtra(KEY_WAKTU_POST_KOS);
        latitude_kos = intent.getStringExtra(KEY_LATITUDE_KOS);
        longitude_kos = intent.getStringExtra(KEY_LONGITUDE_KOS);
        altitude_kos = intent.getStringExtra(KEY_ALTITUDE_KOS);
        rating_kos = intent.getStringExtra(KEY_RATING_KOS);
        jenis_kos = intent.getStringExtra(KEY_JENIS_KOS);
        stok_kamar = intent.getStringExtra(KEY_STOK_KAMAR_KOS);


        System.out.println("testing-1 " + id_pemilik + " " + nama_lengkap_pemilik + " " + foto_pemilik + " " + id_kos + " " + nama_kos
                + " " + alamat_kos + " " + status_kos + " " + harga_kos
                + " " + deskripsi_kos + " " + fasilitas_kos + " " + no_handphone_pemilik
                + " " + waktu_post_kos + " " + latitude_kos + " " + longitude_kos + " " + altitude_kos
                + " " + rating_kos + " " + jenis_kos);

        et_nama_kos.setText(nama_kos);
        et_deskripsi_kos.setText(deskripsi_kos);
        et_alamat_kos.setText(alamat_kos);
        et_fasilitas_kos.setText(fasilitas_kos);
        et_stok_kamar_kos.setText(stok_kamar);
        et_harga_kos.setText(harga_kos);
        tv_latitude_kos.setText(latitude_kos);
        tv_longitude_kos.setText(longitude_kos);
//        Picasso.with(EditKosActivity.this)
//                .load(ServerConfig.KOS_IMAGE + foto_kos)
//                .into(iv_preview_1);
//        Picasso.with(EditKosActivity.this)
//                .load(ServerConfig.KOS_IMAGE + foto_kos_2)
//                .into(iv_preview_2);
//        Picasso.with(EditKosActivity.this)
//                .load(ServerConfig.KOS_IMAGE + foto_kos_3)
//                .into(iv_preview_3);

        //saat tombol tambah di klik
        btn_update_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_kos();
            }
        });

        //saat button foto pertama di klik
        btn_foto_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGaleri();
            }
        });

        btn_foto_kos_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGaleri2();
            }
        });

        btn_foto_kos_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGaleri3();
            }
        });

        //Maps
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps_tambah_kos);
        mapFragment.getMapAsync(this);

        //fungsi awal map dalam keadaan visible
        btn_show_map.setVisibility(View.GONE);
        btn_hide_map.setVisibility(View.VISIBLE);

        //fungsi sembunyikan map di klik
        btn_hide_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragment.getView().setVisibility(View.GONE);
                btn_hide_map.setVisibility(View.GONE);
                btn_show_map.setVisibility(View.VISIBLE);
            }
        });

        //fungsi tampilkan map di klik
        btn_show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragment.getView().setVisibility(View.VISIBLE);
                btn_hide_map.setVisibility(View.VISIBLE);
                btn_show_map.setVisibility(View.GONE);
            }
        });
    }

    private void update_kos() {
        id_pemilik = sessionManager.getLoginDetail().get(SessionManager.ID);
        nama_kos = et_nama_kos.getText().toString();
        deskripsi_kos = et_deskripsi_kos.getText().toString();
        alamat_kos = et_alamat_kos.getText().toString();
        fasilitas_kos = et_fasilitas_kos.getText().toString();
        latitude_kos = tv_latitude_kos.getText().toString();
        longitude_kos = tv_longitude_kos.getText().toString();
        altitude_kos = intent.getStringExtra(KEY_ALTITUDE_KOS);
        harga_kos = et_harga_kos.getText().toString();
        rating_kos = intent.getStringExtra(KEY_RATING_KOS);
        status_kos = intent.getStringExtra(KEY_STATUS_KOS);
        stok_kamar = et_stok_kamar_kos.getText().toString();

        selectedRadioButtonID = rg_jenis_kos.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);
        jenis_kos = selectedRadioButton.getText().toString();

        System.out.println("testing1"+id_pemilik+" "+ nama_kos + " " + deskripsi_kos+" "+alamat_kos+" "+
                fasilitas_kos+" "+longitude_kos+" "+latitude_kos+" "+altitude_kos+" "+harga_kos+" "+rating_kos
                +" "+status_kos+" "+stok_kamar+" "+jenis_kos);

        boolean isEmpty = false;

        if (nama_kos.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Nama Kontrakan masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (deskripsi_kos.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Deskripsi Kontrakan masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (alamat_kos.length() < 6){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Alamat Kontrakan masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (fasilitas_kos.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Fasilitas Kontrakan masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (iv_preview_1.getDrawable() == null){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Foto Kamar Kontrakan masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (iv_preview_2.getDrawable() == null){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Foto Halaman Kontrakan masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (iv_preview_3.getDrawable() == null){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Foto WC Kontrakan masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (harga_kos.equalsIgnoreCase("") ){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Harga Kontrakan masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (latitude_kos.equalsIgnoreCase("") || longitude_kos.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Lokasi anda masih kosong kosong", Toast.LENGTH_SHORT).show();
        }

        if (isEmpty == false){

            UploadImageToServer();

        }

    }

    public void UploadImageToServer(){

        FixBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        FixBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
        FixBitmap3.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream3);

        byteArray = byteArrayOutputStream.toByteArray();
        byteArray2 = byteArrayOutputStream2.toByteArray();
        byteArray3 = byteArrayOutputStream3.toByteArray();

        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        ConvertImage2 = Base64.encodeToString(byteArray2, Base64.DEFAULT);
        ConvertImage3 = Base64.encodeToString(byteArray3, Base64.DEFAULT);

        System.out.println("hasil "+ConvertImage2);
        System.out.println("hasil2 "+ConvertImage2);
        System.out.println("hasil3 "+ConvertImage3);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                progressDialog = ProgressDialog.show(EditKosActivity.this,"Sedang mengupload data","Mohon menunggu",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                progressDialog.dismiss();

                Toast.makeText(EditKosActivity.this,string1,Toast.LENGTH_LONG).show();

                if (string1 == string1) {
                    Intent intent = new Intent(EditKosActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            protected String doInBackground(Void... params) {

                EditKosActivity.ImageProcessClass imageProcessClass = new EditKosActivity.ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(KEY_ID_KOS, id_kos);
                HashMapParams.put(KEY_NAMA_KOS, nama_kos);
                HashMapParams.put(KEY_DESKRIPSI_KOS, deskripsi_kos);
                HashMapParams.put(KEY_ALAMAT_KOS, alamat_kos);
                HashMapParams.put(KEY_FASILITAS_KOS, fasilitas_kos);
                HashMapParams.put(KEY_LATITUDE_KOS, latitude_kos);
                HashMapParams.put(KEY_LONGITUDE_KOS, longitude_kos);
                HashMapParams.put(KEY_ALTITUDE_KOS, altitude_kos);
                HashMapParams.put(KEY_HARGA_KOS, harga_kos);
                HashMapParams.put(KEY_RATING_KOS, rating_kos);
                HashMapParams.put(KEY_STATUS_KOS, status_kos);
                HashMapParams.put(KEY_FOTO, ConvertImage);
                HashMapParams.put(KEY_FOTO_2, ConvertImage2);
                HashMapParams.put(KEY_FOTO_3, ConvertImage3);
                HashMapParams.put(KEY_ID, id_pemilik);
                HashMapParams.put(KEY_STOK_KAMAR_KOS, stok_kamar);
                HashMapParams.put(KEY_JENIS_KOS, jenis_kos);

                System.out.println("testing2"+id_pemilik+" "+ nama_kos + " " + deskripsi_kos+" "+alamat_kos+" "+
                        fasilitas_kos+" "+latitude_kos+" "+longitude_kos+" "+altitude_kos+" "+harga_kos+" "+rating_kos
                        +" "+status_kos+" "+ConvertImage+" "+ConvertImage2+" "+ConvertImage3
                        +" "+id_pemilik+" "+stok_kamar+" "+jenis_kos);

                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);


                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();

    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {
                url = new URL(requestURL);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(20000);

                httpURLConnection.setConnectTimeout(20000);

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);

                httpURLConnection.setDoOutput(true);

                outputStream = httpURLConnection.getOutputStream();

                bufferedWriter = new BufferedWriter(

                        new OutputStreamWriter(outputStream, "UTF-8"));

                bufferedWriter.write(bufferedWriterDataFN(PData));

                bufferedWriter.flush();

                bufferedWriter.close();

                outputStream.close();

                RC = httpURLConnection.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReader.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.getLocalizedMessage();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            stringBuilder = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check)
                    check = false;
                else
                    stringBuilder.append("&");

                stringBuilder.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilder.append("=");

                stringBuilder.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilder.toString();
        }

    }

    //fungsi membuka galeri untuk foto pertama
    private void openGaleri() {

        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar dari Galeri"), 102);
    }

    //fungsi membuka galeri untuk foto pertama
    private void openGaleri2() {

        Intent intent2 = new Intent();

        intent2.setType("image/*");

        intent2.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent2, "Pilih Gambar dari Galeri"), 103);
    }

    //fungsi membuka galeri untuk foto pertama
    private void openGaleri3() {

        Intent intent3 = new Intent();

        intent3.setType("image/*");

        intent3.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent3, "Pilih Gambar dari Galeri"), 104);
    }

    @Override
    protected void onActivityResult(int RC, int RQC, @Nullable Intent I) {

        switch(RC){
            case 102: // Do your stuff here...
                Uri uri = I.getData();
                try {

                    FixBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    iv_preview_1.setImageBitmap(FixBitmap);

                } catch (IOException e) {

                    e.printStackTrace();
                }
                break;
            case 103: // Do your other stuff here...
                Uri uri2 = I.getData();
                try {

                    FixBitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), uri2);

                    iv_preview_2.setImageBitmap(FixBitmap2);

                } catch (IOException e) {

                    e.printStackTrace();
                }
                break;

            case 104: // Do your other stuff here...
                Uri uri3 = I.getData();
                try {

                    FixBitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), uri3);

                    iv_preview_3.setImageBitmap(FixBitmap3);

                } catch (IOException e) {

                    e.printStackTrace();
                }
                break;
        }

        if (RC == PLACE_PICKER_REQUEST) {
            if (RQC == RESULT_OK) {
                Place place = PlacePicker.getPlace(I, this);
//                    String srcRes = String.format("%s", place.getName());
//                    sourceResultText.setText(srcRes);
                latLng=place.getLatLng();
                tv_latitude_kos.setText(String.valueOf(latLng.latitude));

                tv_longitude_kos.setText(String.valueOf(latLng.longitude));
//                    Toast.makeText(this, srcRes, Toast.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(RC, RQC, I);
    }

    //fungsi saat tombol back di handphone ditekan
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EditKosActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(10);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0.507068,101.447779)));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

//                //tempat memasukkan marker custom
//                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_rekomendasi);
//                // Membuat marker
//                MarkerOptions markerOptions = new MarkerOptions()
//
//                        // Mengatur posisi marker
//                        .position(latLng)
//                        // Mengatur gambar ikon marker
//                        .icon(icon)
//                        // Mengatur judul dari marker
//                        // Akan ditampilkan saat marker di klik
//                        .title(latLng.latitude + " : " + latLng.longitude);
//
//                // Clears the previously touched position
//                googleMap.clear();
//
//                // Placing a marker on the touched position
//                googleMap.addMarker(markerOptions);
//
//                et_latitude_kontrakan.setText(String.valueOf(latLng.latitude));
//
//                et_longitude_kontrakan.setText(String.valueOf(latLng.longitude));

                try {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    startActivityForResult(builder.build(EditKosActivity.this), PLACE_PICKER_REQUEST);
                }catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e){
                    Log.d(TAG, "onClick: "+e.getMessage());
                }
            }
        });

        //tambahan untuk permission
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now
                            mMap.setMyLocationEnabled(true);
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();

    }
}

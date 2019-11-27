package com.project.idw.arhome.pemilik_iklan;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.idw.arhome.R;
import com.project.idw.arhome.common.MainActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.fragment.ProfilFragment;
import com.project.idw.arhome.response.ResponseUpdatePemilik;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.HttpFileUpload;
import com.project.idw.arhome.util.SessionManager;
import com.squareup.picasso.Picasso;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilPemilikActivity extends AppCompatActivity {

    public static final String TAG_ID = "id_pemilik";
    public static final String TAG_NAMA_LENGKAP = "nama_lengkap";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_NO_HANDPHONE = "no_handphone";
    public static final String TAG_FOTO_PROFIL = "foto_profil";
    public static final String TAG_PASSWORD = "password";
    public static final String TAG_NO_KK = "no_kk";

    String nama_lengkap, username, email, alamat, no_handphone, no_kk, foto;
    String id_pemilik;

    SessionManager sessionManager;
    ApiInterface apiService;

    Intent intent;

    CircleImageView iv_foto_pemilik;
    Button btn_pilih_foto_pemilik, btn_update_pemilik;
    EditText et_username, et_email,
            et_nama_lengkap, et_no_kk, et_alamat, et_no_handphone;

    private Dialog myDialog;
    public static final String IMAGE_DIRECTORY = "GaleriARHome";
    public String finalPhotoName, fname;
    private ProgressDialog pDialog;
    private Uri imageCaptureUri;
    private File destFile, file;
    private Boolean upflag = false;
    private Bitmap bmp;
    private File sourceFile;
    private SimpleDateFormat dateFormatter;
    public static final String DATE_FORMAT = "yyyyMMdd_HHmmss";
    //max allowed size for uploading is 100 KB
    private int max_allowed_size;
    //    private ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil_pemilik);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(getApplicationContext());

        //create ARHome Folder
        dateFormatter = new SimpleDateFormat(
                DATE_FORMAT, Locale.US);

        file = new File(Environment.getExternalStorageDirectory()
                + "/" + IMAGE_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }

        //max allowed file size for uploading
        max_allowed_size = 150;
        sessionManager = new SessionManager(this);
        Date currentTime = Calendar.getInstance().getTime();
        finalPhotoName = "pemilik_" + dateFormatter.format(new Date()) + ".jpg";

        iv_foto_pemilik = findViewById(R.id.iv_foto_pemilik);
        btn_pilih_foto_pemilik = findViewById(R.id.btn_pilih_foto_pemilik);
        btn_update_pemilik = findViewById(R.id.btn_update_pemilik);
        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_nama_lengkap = findViewById(R.id.et_nama_lengkap);
        et_alamat = findViewById(R.id.et_alamat);
        et_no_handphone = findViewById(R.id.et_no_handphone);
        et_no_kk = findViewById(R.id.et_no_kk);

        intent = getIntent();

        id_pemilik = intent.getStringExtra(TAG_ID);
        nama_lengkap = intent.getStringExtra(TAG_NAMA_LENGKAP);
        username = intent.getStringExtra(TAG_USERNAME);
        email = intent.getStringExtra(TAG_EMAIL);
        alamat = intent.getStringExtra(TAG_ALAMAT);
        no_handphone = intent.getStringExtra(TAG_NO_HANDPHONE);
        no_kk = intent.getStringExtra(TAG_NO_KK);
        foto = intent.getStringExtra(TAG_FOTO_PROFIL);

        System.out.println("testing-1"+id_pemilik + " "+nama_lengkap+ " "+username+ " "+email+ " "+alamat
                + " "+no_handphone+ " "+no_kk+ " "+foto);

        et_username.setText(username);
        et_nama_lengkap.setText(nama_lengkap);
        et_email.setText(email);
        et_alamat.setText(alamat);
        et_no_handphone.setText(no_handphone);
        et_no_kk.setText(no_kk);
        Picasso.with(EditProfilPemilikActivity.this)
                .load(ServerConfig.PROFIL_IMAGE + sessionManager.getLoginDetail().get(SessionManager.FOTO))
                .into(iv_foto_pemilik);


        //saat tombol pilih foto di klik
        btn_pilih_foto_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGaleri();
            }
        });

        btn_update_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_pemilik();
            }
        });
    }

    private void upload_foto() {
        final String foto_profil = finalPhotoName;

        apiService.uploadFotoPemilik(id_pemilik, foto_profil).enqueue(new Callback<ResponseUpdatePemilik>() {
            @Override
            public void onResponse(Call<ResponseUpdatePemilik> call, Response<ResponseUpdatePemilik> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==1){
                        //lakukan uplad foto
                        new UploadFoto().execute();

                        Intent intent = new Intent(EditProfilPemilikActivity.this, MainActivity.class);
                        intent.putExtra(MainActivity.TAG_ID, response.body().getData().getIdPemilik());

                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        sessionManager.logout();
                        finish();
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Silahkan login kembali", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast
                                .LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast
                            .LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdatePemilik> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void update_pemilik() {
        String username = et_username.getText().toString();
        String email = et_email.getText().toString();
        String nama_lengkap = et_nama_lengkap.getText().toString();
        String no_kk = et_no_kk.getText().toString();
        String alamat = et_alamat.getText().toString();
        String no_handphone = et_no_handphone.getText().toString();

        boolean isEmpty = false;

        System.out.println("testing-2"+id_pemilik + " "+nama_lengkap+ " "+username+ " "+email+ " "+alamat
                + " "+no_handphone+ " "+no_kk);

        if (username.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Username masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (email.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Email masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (nama_lengkap.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Nama Lengkap masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (no_kk.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "No KK masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (alamat.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Alamat masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (no_handphone.equalsIgnoreCase("") ){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "No Handphone masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (isEmpty == false){

            apiService.updatePemilik(id_pemilik, username,
                    email,
                    nama_lengkap,
                    no_kk,
                    alamat,
                    no_handphone).enqueue(new Callback<ResponseUpdatePemilik>() {
                @Override
                public void onResponse(Call<ResponseUpdatePemilik> call, Response<ResponseUpdatePemilik> response) {
                    System.out.println("boy "+response.toString());

                    if (response.isSuccessful()){
                        System.out.println("boy2 "+response.body().toString());
                        if (response.body().getCode()==1){

                            Intent intent = new Intent(EditProfilPemilikActivity.this, MainActivity.class);
                            intent.putExtra(MainActivity.TAG_ID, response.body().getData().getIdPemilik());

                            System.out.println("boy3 "+response.body().getData().getIdPemilik()
                                    +" "+response.body().getData().getFoto());

                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                            sessionManager.logout();
                            finish();
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast
                                    .LENGTH_SHORT).show();

                            Toast.makeText(getApplicationContext(), "Silahkan login kembali", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast
                                    .LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast
                                .LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseUpdatePemilik> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Gagal Koneksi Server", Toast
                            .LENGTH_SHORT).show();
                }
            });
        }
    }

    class UploadFoto extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(EditProfilPemilikActivity.this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Mohon menunggu, sedang mengupload gambar..");
            pDialog.show();
        }



        @Override
        protected String doInBackground(String... strings) {
            try {
                // Set your file path here
                FileInputStream fstrm = new FileInputStream(destFile);
                // Set your server page url (and the file title/description)
                HttpFileUpload hfu = new HttpFileUpload(ServerConfig.UPLOAD_FOTO_PEMILIK, "ftitle", "fdescription", finalPhotoName);
                upflag = hfu.Send_Now(fstrm);

            } catch (FileNotFoundException e) {
                // Error: File not found
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (upflag) {
                // selesaikan activity
                finish();
//                restartThisActivity();
            } else {
                Toast.makeText(getApplicationContext(), "Sayangnya gambar tidak bisa diupload..", Toast.LENGTH_LONG).show();
            }
        }

        private void restartThisActivity() {
            startActivity(getIntent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    private void openGaleri() {
        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar dari Galeri"), 102);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        try {
            switch (requestCode){
                case 102:
                    if (resultCode == Activity.RESULT_OK){
                        upflag = true;

                        Uri uriPhoto = data.getData();
                        Log.d("Pick Galeri Image", "Selected Image Uri path : " +uriPhoto.toString());

                        iv_foto_pemilik.setVisibility(View.VISIBLE);
                        iv_foto_pemilik.setImageURI(uriPhoto);

                        sourceFile = new File(getPathFromGooglePhotosUri(uriPhoto));
                        fname = finalPhotoName;

                        destFile = new File(file, fname);

                        //size in KB
                        final int[] file_size = {Integer.parseInt(String.valueOf(sourceFile.length() / 1024))};

                        System.out.println("file size :" + file_size[0]);

                        Log.d("Source File Path :", sourceFile.toString());

                        try {
                            copyFile(sourceFile, destFile);
                        } catch (IOException e){
                            e.printStackTrace();
                        }

                        Picasso.with(getApplicationContext())
                                .load("file:///storage/emulated/0/"+IMAGE_DIRECTORY+"/"+fname)
                                .into(iv_foto_pemilik);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditProfilPemilikActivity.this);

                        //set pesan dari dialog
                        alertDialogBuilder
                                .setMessage("Apakah anda yakin menyimpan foto ini?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        upload_foto();
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
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //fungsi saat tombol back di handphone ditekan
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToMainActivity();
    }

    //Fungsi balik ke Login Main activity
    private void goToMainActivity() {
        Intent intent = new Intent(EditProfilPemilikActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }


    private Bitmap decodeFile(File f, String final_photo_name) {
        Bitmap b = null;

        //Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int IMAGE_MAX_SIZE = 1024;
        int scale = 1;
        if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
            scale = (int) Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE /
                    (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        //Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        try {
            fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("TAG", "Width :" + b.getWidth() + " Height :" + b.getHeight());

        fname = final_photo_name;
        destFile = new File(file, fname);

        return b;
    }

    public String getPathFromGooglePhotosUri(Uri uriPhoto) {
        if (uriPhoto == null)
            return null;

        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(uriPhoto, "r");
            FileDescriptor fd = pfd.getFileDescriptor();
            input = new FileInputStream(fd);

            String tempFilename = getTempFilename(this);
            output = new FileOutputStream(tempFilename);

            int read;
            byte[] bytes = new byte[4096];
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
            return tempFilename;
        } catch (IOException ignored) {
            // Nothing we can do
        } finally {
            closeSilently(input);
            closeSilently(output);
        }
        return null;
    }

    public static void closeSilently(Closeable c) {
        if (c == null)
            return;
        try {
            c.close();
        } catch (Throwable t) {
            // Do nothing
        }
    }

    private static String getTempFilename(Context context) throws IOException {
        File outputDir = context.getCacheDir();
        File outputFile = File.createTempFile("image", "tmp", outputDir);
        return outputFile.getAbsolutePath();
    }

    private void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }

        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }
}

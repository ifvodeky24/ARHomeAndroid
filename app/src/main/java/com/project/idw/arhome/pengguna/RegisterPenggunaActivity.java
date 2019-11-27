package com.project.idw.arhome.pengguna;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.idw.arhome.R;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.RegisterPengguna;
import com.project.idw.arhome.pemilik_iklan.LoginPemilikActivity;
import com.project.idw.arhome.response.ResponseRegisterPengguna;
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
import java.util.HashMap;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPenggunaActivity extends AppCompatActivity {
    private Dialog myDialog;
    private SessionManager sessionManager;
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

    CircleImageView iv_foto_pengguna;
    Button btn_pilih_foto_pengguna, btn_register_pengguna;
    EditText et_username, et_password, et_email,
            et_nama_lengkap, et_alamat, et_no_handphone;

    ApiInterface apiInterface;

    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Register Pengguna");
        setContentView(R.layout.activity_register_pengguna);

        //create ARHome Folder
        dateFormatter = new SimpleDateFormat(
                DATE_FORMAT, Locale.US);

        file = new File(Environment.getExternalStorageDirectory()
                + "/" + IMAGE_DIRECTORY);
        if (!file.exists()){
            file.mkdirs();
        }

        //max allowed file size for uploading
        max_allowed_size = 150;
        sessionManager = new SessionManager(this);
        Date currentTime = Calendar.getInstance().getTime();

        auth = FirebaseAuth.getInstance();

        iv_foto_pengguna = findViewById(R.id.iv_foto_pengguna_register);
        btn_pilih_foto_pengguna = findViewById(R.id.btn_pilih_foto_pengguna);
        btn_register_pengguna = findViewById(R.id.btn_register_pengguna);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_email = findViewById(R.id.et_email);
        et_nama_lengkap = findViewById(R.id.et_nama_lengkap);
        et_alamat = findViewById(R.id.et_alamat);
        et_no_handphone = findViewById(R.id.et_no_handphone);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);

        btn_register_pengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_pengguna();
            }
        });

        btn_pilih_foto_pengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGaleri();
            }
        });
    }

    private void openGaleri() {

        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar dari Galeri"), 102);
    }

    private void register_pengguna() {
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        final String email = et_email.getText().toString();
        final String nama_lengkap = et_nama_lengkap.getText().toString();
        final String alamat = et_alamat.getText().toString();
        final String foto = finalPhotoName;
        final String no_handphone = et_no_handphone.getText().toString();

        boolean isEmpty = false;

        if (username.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Username masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (password.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Password masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (password.length() < 6){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
        }

        if (email.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Email masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (nama_lengkap.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Nama Lengkap masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (alamat.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Alamat masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (foto == null){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Foto masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (no_handphone.equalsIgnoreCase("") ){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "No Handphone masih kosong", Toast.LENGTH_SHORT).show();
        }

        System.out.println("nilai-foto"+foto);

        if (isEmpty ==false && foto != null){
            apiInterface.registerPengguna(username,
                    password,
                    email,
                    nama_lengkap,
                    alamat,
                    foto,
                    no_handphone).enqueue(new Callback<ResponseRegisterPengguna>() {
                @Override
                public void onResponse(Call<ResponseRegisterPengguna> call, Response<ResponseRegisterPengguna> response) {
                    System.out.println(response.toString());
                    if (response.isSuccessful()){
                        System.out.println(response.body().toString());
                        if (response.body().getCode()==1){
                            //lakukan uplad foto
                            new UploadFoto().execute();

//                            auth.createUserWithEmailAndPassword(email, password)
//                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<AuthResult> task) {
//                                            if (task.isSuccessful()){
//                                                FirebaseUser firebaseUser = auth.getCurrentUser();
//
//                                                String userid = firebaseUser.getUid();
//
//                                                reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
//
//                                                HashMap<String, String> hashMap = new HashMap<>();
//                                                hashMap.put("id", userid);
//                                                hashMap.put("username", username);
//                                                hashMap.put("password", password);
//                                                hashMap.put("email", email);
//                                                hashMap.put("nama_lengkap", nama_lengkap);
//                                                hashMap.put("alamat", alamat);
//                                                hashMap.put("foto", foto);
//                                                hashMap.put("no_handphone", no_handphone);
//
//                                                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<Void> task) {
//                                                        if (task.isSuccessful()){
//                                                            Toast.makeText(getApplicationContext(), "Registrasi Berhasil", Toast
//                                                                    .LENGTH_SHORT).show();
//
//                                                        }else {
//                                                            Toast.makeText(getApplicationContext(), "Registrasi Gagal", Toast
//                                                                    .LENGTH_SHORT).show();
//                                                        }
//                                                    }
//                                                });
//
//                                            }
//                                        }
//                                    });

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterPenggunaActivity.this, LoginPenggunaActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

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
                public void onFailure(Call<ResponseRegisterPengguna> call, Throwable t) {
                    t.printStackTrace();

                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {
            switch (requestCode){
                case 102:
                    if (resultCode == Activity.RESULT_OK){
                        finalPhotoName = "pengguna_" +dateFormatter.format(new Date())+ ".jpg";

                        upflag = true;

                        Uri uriPhoto = data.getData();
                        Log.d("Pick Galeri Image", "Selected Image Uri path : " +uriPhoto.toString());

                        iv_foto_pengguna.setVisibility(View.VISIBLE);
                        iv_foto_pengguna.setImageURI(uriPhoto);

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
                                .into(iv_foto_pengguna);
                    }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    class UploadFoto extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(RegisterPenggunaActivity.this);
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
                HttpFileUpload hfu = new HttpFileUpload(ServerConfig.UPLOAD_FOTO_PENGGUNA, "ftitle", "fdescription", finalPhotoName);
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

    //fungsi saat tombol back di handphone ditekan
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToMainActivity();
    }

    //Fungsi balik ke Login Pengguna activity
    private void goToMainActivity() {
        Intent intent = new Intent(RegisterPenggunaActivity.this, LoginPenggunaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
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

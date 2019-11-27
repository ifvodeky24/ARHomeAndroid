package com.project.idw.arhome.pemilik_iklan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.idw.arhome.R;
import com.project.idw.arhome.common.MainActivity;
import com.project.idw.arhome.response.ResponseLoginPemilik;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPemilikActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    TextView tv_register_pemilik;
    Button btnLoginPemilik;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    public static final String TAG_ID = "id";

    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pemilik);

        auth = FirebaseAuth.getInstance();

        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn()==true){
            Intent intent = new Intent(LoginPemilikActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tv_register_pemilik= findViewById(R.id.tv_register_pemilik);
        btnLoginPemilik = findViewById(R.id.btnLoginPemilik);

        tv_register_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPemilikActivity.this, RegisterPemilikActivity.class);
                startActivity(intent);
            }
        });

        btnLoginPemilik.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLoginPemilik){
            final String email = etEmail.getText().toString();
            final String password = etPassword.getText().toString();
            boolean isEmpty = false;

            if (email.equalsIgnoreCase("")){
                isEmpty = true;
                Toast.makeText(getApplicationContext(), "Email tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            }

            if (password.equalsIgnoreCase("")){
                isEmpty = true;
                Toast.makeText(getApplicationContext(), "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            }

            if (isEmpty==false){

                    apiInterface.loginPemilik(email, password).enqueue(new Callback<ResponseLoginPemilik>() {
                        @Override
                        public void onResponse(Call<ResponseLoginPemilik> call, Response<ResponseLoginPemilik> response) {
                            System.out.println(response.toString());
                            if (response.isSuccessful()){
                                System.out.println(response.body().toString());
                                if (response.body().getCode() == 1){

                                    final Integer strId = response.body().getData().getIdPemilik();
                                    String strUsername = response.body().getData().getUsername();
                                    String strPassword = response.body().getData().getPassword();
                                    final String strEmail = response.body().getData().getEmail();
                                    String strNamaLengkap = response.body().getData().getNamaLengkap();
                                    String strAlamat = response.body().getData().getAlamat();
                                    String strFoto = response.body().getData().getFoto();
                                    String strNoHandphone = response.body().getData().getNoHandphone();

                                    sessionManager.createLoginSession(String.valueOf(strId),
                                            strUsername,
                                            strPassword,
                                            strEmail,
                                            strNamaLengkap,
                                            strAlamat,
                                            strFoto,
                                            strNoHandphone,
                                            SessionManager.LEVEL_PEMILIK);

                                    Intent intent = new Intent(LoginPemilikActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);
                                    finish();


                                }else {
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLoginPemilik> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });

            }

        }
    }

    //fungsi saat tombol back di handphone ditekan
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToMainActivity();
    }

    //fungsi untuk balik ke main activity
    private void goToMainActivity() {
        Intent intent = new Intent(LoginPemilikActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}

package com.project.idw.arhome.pengguna;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.project.idw.arhome.R;
import com.project.idw.arhome.common.MainActivity;
import com.project.idw.arhome.response.ResponseLoginPengguna;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPenggunaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    TextView tv_register_pengguna;
    Button btnLoginPengguna;
    ApiInterface apiInterface;
    SessionManager sessionManager;

//    SignInButton signIn;
//    static final int GOOGLE_SIGN = 101;
    public final String TAG = LoginPenggunaActivity.class.getSimpleName();
//    private FirebaseAuth mAuth;
//    GoogleSignInClient mGoogleSignInClient;

    public static final String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pengguna);

        //Get Firebase auth instance
//        mAuth = FirebaseAuth.getInstance();

//        if (mAuth.getCurrentUser() != null) {
//            startActivity(new Intent(LoginPenggunaActivity.this, MainActivity.class));
//            finish();
//        }

        apiInterface= ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn()==true){
            Intent intent = new Intent(LoginPenggunaActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLoginPengguna = findViewById(R.id.btnLoginPengguna);
        tv_register_pengguna = findViewById(R.id.tv_register_pengguna);
//        signIn = findViewById(R.id.sign_in_button);

//        mAuth = FirebaseAuth.getInstance();

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnLoginPengguna.setOnClickListener(this);
//        signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SignInGoogle();
//            }
//        });

        tv_register_pengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPenggunaActivity.this, RegisterPenggunaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

//    private void SignInGoogle(){
//        Intent signIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signIntent, GOOGLE_SIGN);
//    }

//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                            Toast.makeText(getApplicationContext(), "Login sebagai pengguna berhasil", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toast.makeText(getApplicationContext(), "Login sebagai pengguna gagal", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 101) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                if (account != null) firebaseAuthWithGoogle(account);
//            } catch (ApiException e) {
//                Log.w("TAG", "Google sign in gagal", e);
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLoginPengguna){
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
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
                apiInterface.loginPengguna(email, password).enqueue(new Callback<ResponseLoginPengguna>() {
                    @Override
                    public void onResponse(Call<ResponseLoginPengguna> call, Response<ResponseLoginPengguna> response) {
                        System.out.println(response.toString());
                        if (response.isSuccessful()){
                            System.out.println(response.body().toString());
                            if (response.body().getCode() == 1){
                                Integer strId = response.body().getData().getIdPengguna();
                                String strUsername = response.body().getData().getUsername();
                                String strPassword = response.body().getData().getPassword();
                                String strEmail = response.body().getData().getEmail();
                                String strNamaLengkap = response.body().getData().getNamaLengkap();
                                System.out.println("Nama Lengkap: "+strNamaLengkap);
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
                                        SessionManager.LEVEL_PENGGUNA);

                                Intent intent = new Intent(LoginPenggunaActivity.this, MainActivity.class);
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
                    public void onFailure(Call<ResponseLoginPengguna> call, Throwable t) {
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
        Intent intent = new Intent(LoginPenggunaActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}


package com.project.idw.arhome.pengguna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.idw.arhome.R;
import com.project.idw.arhome.common.MainActivity;
import com.project.idw.arhome.fragment.PengaturanFragment;
import com.project.idw.arhome.pemilik_iklan.UbahPasswordPemilikActivity;
import com.project.idw.arhome.response.ResponseUpdatePengguna;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPasswordPenggunaActivity extends AppCompatActivity {

    public static final String TAG_ID = "id";

    String id, password;

    SessionManager sessionManager;
    ApiInterface apiService;

    Intent intent;

    EditText et_password;
    Button btn_ubah_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password_pengguna);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(getApplicationContext());

        intent = getIntent();

        id = intent.getStringExtra(TAG_ID);
        password = sessionManager.getLoginDetail().get(SessionManager.PASSWORD);


        System.out.println("id nya= " +id + " password= " +password);

        et_password = findViewById(R.id.et_password);
        btn_ubah_password = findViewById(R.id.btn_ubah_password);

        et_password.setText(password);

        btn_ubah_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_password();
            }
        });
    }

    private void update_password() {
        String password = et_password.getText().toString();

        boolean isEmpty = false;

        if (password.equalsIgnoreCase("")){
            isEmpty = true;
            Toast.makeText(getApplicationContext(), "Password masih kosong", Toast.LENGTH_SHORT).show();
        }

        if (isEmpty == false){
            apiService.updatePasswordPengguna(id,
                    password).enqueue(new Callback<ResponseUpdatePengguna>() {
                @Override
                public void onResponse(Call<ResponseUpdatePengguna> call, Response<ResponseUpdatePengguna> response) {
                    System.out.println("boy "+response.toString());

                    if (response.isSuccessful()){
                        System.out.println("boy2 "+response.body().toString());

                        if (response.body().getCode()==1){
                            Intent intent = new Intent(UbahPasswordPenggunaActivity.this, MainActivity.class);
                            intent.putExtra(MainActivity.TAG_ID, response.body().getData().getIdPengguna());

                            System.out.println("boy3 "+response.body().getData().getIdPengguna()
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
                public void onFailure(Call<ResponseUpdatePengguna> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Gagal Koneksi Server", Toast
                            .LENGTH_SHORT).show();
                }
            });
        }
    }

    private void goToMainActivity() {
        //Fungsi kembali ke main activity dan tidak kembali lagi ke activity ini
        Intent intent = new Intent(UbahPasswordPenggunaActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToMainActivity();
    }
}

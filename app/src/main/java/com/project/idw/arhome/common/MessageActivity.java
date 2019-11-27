package com.project.idw.arhome.common;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.idw.arhome.R;
import com.project.idw.arhome.adapter.MessageAdapter;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.Chat;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {
    public static final String KEY_ID = "id_pemilik";
    public static final String KEY_NAMA_LENGKAP_PEMILIK = "nama_lengkap_pemilik";
    public static final String KEY_FOTO_PEMILIK = "foto_pemilik";
    public static final String KEY_NAMA_LENGKAP_PENGGUNA = "nama_lengkap_pengguna";
    public static final String KEY_FOTO_PENGGUNA = "foto_pengguna";
    SessionManager sessionManager;
    CircleImageView profile_image;
    TextView nama_lengkap;
    String strNamaLengkap;
    String strFoto;
    int userId;
    String id;
    Intent intent;
    ImageButton btn_send;
    EditText text_send;
    private MessageAdapter messageAdapter;
    private List<Chat> mChat;
    private RecyclerView recyclerView;
    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        sessionManager = new SessionManager(MessageActivity.this);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        id = sessionManager.getLoginDetail().get("id");

        System.out.println("Hasil" + id);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        mChat = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        profile_image = findViewById(R.id.profile_image);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);

        intent = getIntent();

        userId = intent.getIntExtra(KEY_ID, 0);

        System.out.println("User id: " + userId);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = text_send.getText().toString();
                if (!msg.equals("")) {
                    sendMessage(id, String.valueOf(userId), msg);
                } else {
                    Toast.makeText(MessageActivity.this, "Pesanmu Kosong, Silahkan isi terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                text_send.setText("");
            }
        });

        intent = getIntent();
        userId = intent.getIntExtra(KEY_ID, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.requireNonNull(sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN)).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)) {
                strNamaLengkap = intent.getStringExtra(KEY_NAMA_LENGKAP_PEMILIK);
                strFoto = intent.getStringExtra(KEY_FOTO_PEMILIK);
            }else if (Objects.requireNonNull(sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN)).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)) {
                strNamaLengkap = intent.getStringExtra(KEY_NAMA_LENGKAP_PENGGUNA);
                strFoto = intent.getStringExtra(KEY_FOTO_PENGGUNA);
            }
        }

        nama_lengkap.setText(strNamaLengkap);
        Picasso.with(MessageActivity.this)
                .load(ServerConfig.PROFIL_IMAGE + strFoto)
                .into(profile_image);

//        System.out.println(username);

        readMessages(id, String.valueOf(userId));
    }

    private void sendMessage(String sender, String receiver, String message) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sender", String.valueOf(sender));
        hashMap.put("receiver", String.valueOf(receiver));
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);

        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chatlist")
                .child(id)
                .child(String.valueOf(userId));

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    String id_receiver = String.valueOf(userId);
                    String id_sender = String.valueOf(id);

                    chatRef.child("id").setValue(id_receiver);
                    chatRef.child("id_now").setValue(id_sender);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference chatRef2 = FirebaseDatabase.getInstance().getReference("Chatlist2")
                .child(String.valueOf(userId))
                .child(id);

        chatRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    String id_receiver = String.valueOf(userId);
                    String id_sender = String.valueOf(id);

                    chatRef2.child("id").setValue(id_receiver);
                    chatRef2.child("id_now").setValue(id_sender);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void readMessages(final String id, final String userid) {
        mChat = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");

        System.out.println("data reference:" + reference);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("data snapshot:" + dataSnapshot);

                mChat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Chat chat = snapshot.getValue(Chat.class);

                    System.out.println("Data chat" + snapshot.getValue());

                    if (chat != null) {
                        Log.d("TAG CHAT", chat.getMessage() + " / " +
                                chat.getReceiver() + " / " +
                                chat.getSender() + " /" + dataSnapshot.getValue());
                    }

                    System.out.println("IDNYA :" + id);

                    if (chat != null && (chat.getReceiver().equals(id) && chat.getSender().equals(userid) ||
                            chat.getReceiver().equals(userid) && chat.getSender().equals(id))) {
                        mChat.add(chat);
                    }

                    messageAdapter = new MessageAdapter(MessageActivity.this, mChat);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Cancelled", databaseError.toString());

            }
        });
    }
}



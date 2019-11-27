package com.project.idw.arhome.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.idw.arhome.R;
import com.project.idw.arhome.adapter.PemilikAdapter;
import com.project.idw.arhome.adapter.PenggunaAdapter;
import com.project.idw.arhome.model.Chat;
import com.project.idw.arhome.model.Chatlist;
import com.project.idw.arhome.model.Pemilik;
import com.project.idw.arhome.model.Pengguna;
import com.project.idw.arhome.model.User;
import com.project.idw.arhome.response.ResponsePemilik;
import com.project.idw.arhome.response.ResponsePengguna;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import afu.org.checkerframework.checker.nullness.qual.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;

    private PenggunaAdapter penggunaAdapter;
    private PemilikAdapter pemilikAdapter;
    private ShimmerFrameLayout mShimmerViewContainer;
    private List<Chatlist> usersList;
    DatabaseReference reference;
    private List<Pengguna> penggunaList;
    private List<Pemilik> pemilikList;
    List<Pemilik> mPemilik;
    List<Pengguna> mPengguna;
    ImageView iv_chat_kosong;

//    FirebaseUser firebaseUser;
//    DatabaseReference databaseReference;

    SessionManager sessionManager;
    ApiInterface apiService;
//    SwipeRefreshLayout swipeRefreshLayout;

//    private List<String> usersList;

    String id;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_chat);
        recyclerView.setHasFixedSize(true);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        iv_chat_kosong = view.findViewById(R.id.iv_chat_kosong);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<>();
        pemilikList = new ArrayList<>();
        penggunaList = new ArrayList<>();

        sessionManager = new SessionManager(getActivity());

        apiService = ApiClient.getClient().create(ApiInterface.class);

        id = sessionManager.getLoginDetail().get(SessionManager.ID);

        System.out.println("testing"+ id);

        if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PENGGUNA)) {

            reference = FirebaseDatabase.getInstance().getReference("Chatlist")
                    .child(sessionManager.getLoginDetail().get(SessionManager.ID));

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    usersList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Chatlist chatlist = snapshot.getValue(Chatlist.class);
                        usersList.add(chatlist);
                        // method chat list

                    }
                    chatListPemilik();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)) {

            reference = FirebaseDatabase.getInstance().getReference("Chatlist2")
                    .child(sessionManager.getLoginDetail().get(SessionManager.ID));

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    usersList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Chatlist chatlist = snapshot.getValue(Chatlist.class);
                        usersList.add(chatlist);
                        // method chat list

                    }
                    chatListPengguna();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        return view;
    }

    private void chatListPengguna() {
        mPengguna = new ArrayList<>();

        apiService.penggunaGetAll().enqueue(new Callback<ResponsePengguna>() {
            @Override
            public void onResponse(Call<ResponsePengguna> call, Response<ResponsePengguna> response) {
                if (response.isSuccessful()){
                    System.out.println(response.body().getMaster().size());
                    if (response.body().getMaster().size() > 0){
                        penggunaList = response.body().getMaster();

                        Iterator<Pengguna> iterator = new ArrayList<>(penggunaList).iterator();
                        while (iterator.hasNext()) {
                            Pengguna pengguna = iterator.next();

                            for (Chatlist chatlist : usersList)
                            {
                                System.out.println("id_now: "+ chatlist.id_now + " "+ pengguna.getIdPengguna());
                                String b = String.valueOf(pengguna.getIdPengguna());
                                boolean c = b.equals(chatlist.getId_now());
                                System.out.println("data b adalah"+c);

                                if (c)
                                {

                                    Log.d("log user", ""+pengguna.getIdPengguna());
                                    Log.d("log chat", ""+chatlist.getId_now());
                                    mPengguna.add(pengguna);

                                    System.out.println("isii : " +mPengguna);

                                }
                            }
                        }

                        penggunaAdapter = new PenggunaAdapter(getContext(), mPengguna);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(penggunaAdapter);

                    } else {
//                    loading.dismiss();
                        Toast.makeText(getContext(), "Data Chat Kosong", Toast.LENGTH_SHORT).show();
                        iv_chat_kosong.setVisibility(View.VISIBLE);
                    }

                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);

                }else{
                    Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePengguna> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void chatListPemilik() {

        mPemilik = new ArrayList<>();

        apiService.pemilikGetAll().enqueue(new Callback<ResponsePemilik>() {
            @Override
            public void onResponse(Call<ResponsePemilik> call, Response<ResponsePemilik> response) {
                if (response.isSuccessful()){
                    System.out.println(response.body().getMaster().size());
                    if (response.body().getMaster().size() > 0){
                        pemilikList = response.body().getMaster();

                        Iterator<Pemilik> iterator = new ArrayList<>(pemilikList).iterator();
                        while (iterator.hasNext()) {
                            Pemilik pemilik = iterator.next();

                            for (Chatlist chatlist : usersList)
                            {
                                String b = String.valueOf(pemilik.getIdPemilik());
                                boolean c = b.equals(chatlist.getId());
                                System.out.println("data a adalah"+c);

                                if (c)
                                {

                                    Log.d("log user", ""+pemilik.getIdPemilik());
                                    Log.d("log chat", ""+chatlist.getId());
                                    mPemilik.add(pemilik);

                                    System.out.println("isii : " +mPemilik);

                                }
                            }
                        }

                        pemilikAdapter = new PemilikAdapter(getContext(), mPemilik);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(pemilikAdapter);


                    } else {
                        Toast.makeText(getContext(), "Data Chat Kosong", Toast.LENGTH_SHORT).show();
                        iv_chat_kosong.setVisibility(View.VISIBLE);
                    }

                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);

                }else{
                    Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePemilik> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        super.onPause();
        mShimmerViewContainer.stopShimmerAnimation();

    }
}

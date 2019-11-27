package com.project.idw.arhome.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.project.idw.arhome.R;
import com.project.idw.arhome.adapter.DaftarKosPemilikAdapter;
import com.project.idw.arhome.model.KosPemilik;
import com.project.idw.arhome.response.ResponseKosPemilik;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarKosPemilikFragment extends Fragment {
    private RecyclerView recyclerView;
    private DaftarKosPemilikAdapter adapter;
    private ArrayList<KosPemilik> KosPemilikArrayList = new ArrayList<>();
    public static final String ARG_STATUS = "status";
    private ShimmerFrameLayout mShimmerViewContainer;

    SessionManager sessionManager;

    ApiInterface apiService;
    SwipeRefreshLayout swipeRefreshLayout;

    public static final String TAG = DaftarKosPemilikFragment.class.getSimpleName();
    private String status;

    //membuat fungsi menambahkan fragment di activity
    public static DaftarKosPemilikFragment newInstance(String status){
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);

        DaftarKosPemilikFragment fragment = new DaftarKosPemilikFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public DaftarKosPemilikFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getArguments();
        if (extras != null){
            status = extras.getString(ARG_STATUS);
        }

        apiService = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_daftar_kos_pemilik, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        getKosPemilik(status);
        initView(view);
        return view;
    }

    //fungsi saat layar diswipe keatas
    private void initView(View view) {
        swipeRefreshLayout      = view.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorAccent);

        refresh();

    }

    private void refresh() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        KosPemilikArrayList.clear();
                        getKosPemilik(status);
                    }
                }, 3000);
            }
        });

    }

    private void getKosPemilik(String status){
        if (status.equalsIgnoreCase("Kos")){
            apiService.kosGetAllByPemilikKos(sessionManager.getLoginDetail().get(SessionManager.ID)).enqueue(new Callback<ResponseKosPemilik>() {
                @Override
                public void onResponse(Call<ResponseKosPemilik> call, Response<ResponseKosPemilik> response) {
                    System.out.println(response.toString());
                    if (response.isSuccessful()){
                        System.out.println(response.body().getMaster().size());

                        if (response.body().getMaster().size() > 0){

                            KosPemilikArrayList.addAll(response.body().getMaster());
                            System.out.println(response.body().getMaster().get(0).getNamaLengkapPemilik());

                            adapter = new DaftarKosPemilikAdapter(getContext(), KosPemilikArrayList);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);


                        }else {
                            Toast.makeText(getContext(), "Data Kos Kosong", Toast.LENGTH_SHORT).show();
                        }

                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);

                    }else {
                        Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseKosPemilik> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

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

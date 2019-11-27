package com.project.idw.arhome.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import com.project.idw.arhome.adapter.RekomendasiKontrakanAdapter;
import com.project.idw.arhome.model.RekomendasiKontrakans;
import com.project.idw.arhome.response.ResponseRekomendasiKontrakans;
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
public class RekomendasiKontrakanFragment extends Fragment {
    private RecyclerView rv_rekomendasi_kontrakan;
    private RekomendasiKontrakanAdapter adapter;
    private ArrayList<RekomendasiKontrakans> RekomendasiKontrakanArrayList = new ArrayList<>();
    public static final String ARG_STATUS = "status";
    private ShimmerFrameLayout mShimmerViewContainer;

    SessionManager sessionManager;

    ApiInterface apiService;
    SwipeRefreshLayout swipeRefreshLayout;

    public static final String TAG = RekomendasiKontrakanFragment.class.getSimpleName();
    private String status;

    //membuat fungsi menambahkan fragment di activity
    public static RekomendasiKontrakanFragment newInstance(String status){
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);

        RekomendasiKontrakanFragment fragment = new RekomendasiKontrakanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RekomendasiKontrakanFragment() {
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rekomendasi_kontrakan, container, false);
        rv_rekomendasi_kontrakan = view.findViewById(R.id.rv_rekomendasi_kontrakan);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        getRekomendasiKontrakan(status);
        initView(view);
        return view;
    }

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
                        RekomendasiKontrakanArrayList.clear();
                        getRekomendasiKontrakan(status);
                    }
                }, 3000);
            }
        });
    }

    private void getRekomendasiKontrakan(String status){
        if (status.equalsIgnoreCase("Rekomendasi Kontrakan")){
            apiService.rekomendasiKontrakanGetAll().enqueue(new Callback<ResponseRekomendasiKontrakans>() {
                @Override
                public void onResponse(Call<ResponseRekomendasiKontrakans> call, Response<ResponseRekomendasiKontrakans> response) {
                    if (response.isSuccessful()){

                        System.out.println("responseSize"+response.body().getMaster().size());
                        System.out.println("responseIn"+response);

                        if (response.body().getMaster().size()>0){
                            RekomendasiKontrakanArrayList.addAll(response.body().getMaster());
                            System.out.println(response.body().getMaster().get(0).getNama());

                            adapter = new RekomendasiKontrakanAdapter(getContext(),RekomendasiKontrakanArrayList);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            rv_rekomendasi_kontrakan.setLayoutManager(layoutManager);
                            rv_rekomendasi_kontrakan.setAdapter(adapter);
                        }else {
                            Toast.makeText(getContext(), "Data Rekomendasi Kontrakan Kosong", Toast.LENGTH_SHORT).show();
                        }

                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);

                    }else{
                        Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseRekomendasiKontrakans> call, Throwable t) {
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

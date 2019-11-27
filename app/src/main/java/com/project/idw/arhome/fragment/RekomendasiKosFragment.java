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
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.project.idw.arhome.R;
import com.project.idw.arhome.adapter.RekomendasiKosAdapter;
import com.project.idw.arhome.model.RekomendasiKoses;
import com.project.idw.arhome.response.ResponseRekomendasiKoses;
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
public class RekomendasiKosFragment extends Fragment {
    private RecyclerView rv_rekomendasi_kos;
    private RekomendasiKosAdapter adapter;
    private ArrayList<RekomendasiKoses> RekomendasiKosArrayList = new ArrayList<>();
    public static final String ARG_STATUS = "status";
    private ShimmerFrameLayout mShimmerViewContainer;

    SessionManager sessionManager;

    ApiInterface apiService;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView tv;

    public static final String TAG = PemesananKosFragment.class.getSimpleName();
    private String status;

    //membuat fungsi menambahkan fragment di activity
    public static RekomendasiKosFragment newInstance(String status){
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);

        RekomendasiKosFragment fragment = new RekomendasiKosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RekomendasiKosFragment() {
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rekomendasi_kos, container, false);
        rv_rekomendasi_kos = view.findViewById(R.id.rv_rekomendasi_kos);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        getRekomendasiKos(status);
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
                        RekomendasiKosArrayList.clear();
                        getRekomendasiKos(status);
                    }
                }, 3000);
            }
        });

    }

    private void getRekomendasiKos(String status) {
        if (status.equalsIgnoreCase("Rekomendasi Kos")){
            apiService.rekomendasiKosGetAll().enqueue(new Callback<ResponseRekomendasiKoses>() {
                @Override
                public void onResponse(Call<ResponseRekomendasiKoses> call, Response<ResponseRekomendasiKoses> response) {
                    if (response.isSuccessful()){
                        System.out.println("responseSize"+response.body().getMaster().size());
                        System.out.println("responseIn"+response);

                        if (response.body().getMaster().size()>0){
                            RekomendasiKosArrayList.addAll(response.body().getMaster());
                            System.out.println(response.body().getMaster().get(0).getNama());

                            adapter = new RekomendasiKosAdapter(getContext(),RekomendasiKosArrayList);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            rv_rekomendasi_kos.setLayoutManager(layoutManager);
                            rv_rekomendasi_kos.setAdapter(adapter);
                        }else {
                            Toast.makeText(getContext(), "Data Rekomendasi Kos Kosong", Toast.LENGTH_SHORT).show();
                        }

                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);

                    }else{
                        Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseRekomendasiKoses> call, Throwable t) {
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

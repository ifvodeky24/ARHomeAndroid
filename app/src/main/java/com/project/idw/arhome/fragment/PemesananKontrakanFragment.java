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
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.project.idw.arhome.R;
import com.project.idw.arhome.adapter.PemesananKontrakanAdapter;
import com.project.idw.arhome.model.PemesananKontrakans;
import com.project.idw.arhome.response.ResponsePemesananKontrakans;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;
import com.project.idw.arhome.util.SessionManager;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemesananKontrakanFragment extends Fragment {
    private RecyclerView rv_pemesanan_kontrakan;
    private PemesananKontrakanAdapter adapter;
    private ArrayList<PemesananKontrakans> PemesananKontrakanArrayList = new ArrayList<>();
    public static final String ARG_STATUS = "status";
    private ShimmerFrameLayout mShimmerViewContainer;
    ImageView iv_pemesanan_kosong;

    SessionManager sessionManager;

    ApiInterface apiService;
    SwipeRefreshLayout swipeRefreshLayout;

    public static final String TAG = PemesananKontrakanFragment.class.getSimpleName();
    private String status;

    //membuat fungsi menambahkan fragment di activity
    public static PemesananKontrakanFragment newInstance(String status){
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);

        PemesananKontrakanFragment fragment = new PemesananKontrakanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public PemesananKontrakanFragment() {
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
        View view = inflater.inflate(R.layout.fragment_pemesanan_kontrakan, container, false);
        rv_pemesanan_kontrakan = view.findViewById(R.id.rv_pemesanan_kontrakan);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        iv_pemesanan_kosong = view.findViewById(R.id.iv_pemesanan_kosong);

        getPemesananKontrakan(status);
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
                        PemesananKontrakanArrayList.clear();
                        getPemesananKontrakan(status);
                    }
                }, 3000);
            }
        });

    }

    private void getPemesananKontrakan(String status) {
        if (status.equalsIgnoreCase("Pemesanan Kontrakan")){
            if (sessionManager.getLoginDetail().get(SessionManager.LEVEL_LOGIN).equalsIgnoreCase(SessionManager.LEVEL_PEMILIK)) {
                apiService.pemesananKontrakanGetAllByPemilik(sessionManager.getLoginDetail().get(SessionManager.ID)).enqueue(new Callback<ResponsePemesananKontrakans>() {
                    @Override
                    public void onResponse(Call<ResponsePemesananKontrakans> call, Response<ResponsePemesananKontrakans> response) {
                        System.out.println(response.toString());
                        if (response.isSuccessful()) {

                            System.out.println(response.body().getMaster().size());
                            if (response.body().getMaster().size() > 0) {
                                PemesananKontrakanArrayList.addAll(response.body().getMaster());
                                System.out.println(response.body().getMaster().get(0).getNamaLengkapPemilik());

                                adapter = new PemesananKontrakanAdapter(getContext(), PemesananKontrakanArrayList);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                                rv_pemesanan_kontrakan.setLayoutManager(layoutManager);
                                rv_pemesanan_kontrakan.setAdapter(adapter);
                            } else {
                                Toast.makeText(getContext(), "Data Pemesanan Kontrakan Kosong", Toast.LENGTH_SHORT).show();
                                iv_pemesanan_kosong.setVisibility(View.VISIBLE);
                            }

                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.GONE);

                        } else {
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePemesananKontrakans> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getContext(), "Gagal Koneksi ke Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                apiService.pemesananKontrakanGetAllByPengguna(sessionManager.getLoginDetail().get(SessionManager.ID)).enqueue(new Callback<ResponsePemesananKontrakans>() {
                    @Override
                    public void onResponse(Call<ResponsePemesananKontrakans> call, Response<ResponsePemesananKontrakans> response) {
                        if (response.isSuccessful()) {
                            System.out.println(response.body().getMaster().size());
                            if (response.body().getMaster().size() > 0) {
                                PemesananKontrakanArrayList.addAll(response.body().getMaster());
                                System.out.println(response.body().getMaster().get(0).getNamaLengkapPemilik());

                                adapter = new PemesananKontrakanAdapter(getContext(), PemesananKontrakanArrayList);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                                rv_pemesanan_kontrakan.setLayoutManager(layoutManager);
                                rv_pemesanan_kontrakan.setAdapter(adapter);
                            } else {
                                Toast.makeText(getContext(), "Data Pemesanan Kontrakan Kosong", Toast.LENGTH_SHORT).show();
                                iv_pemesanan_kosong.setVisibility(View.VISIBLE);
                            }

                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.GONE);

                        } else {
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePemesananKontrakans> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
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


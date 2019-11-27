package com.project.idw.arhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.idw.arhome.R;
import com.project.idw.arhome.common.DetailPemesananKontrakanActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.PemesananKontrakanDetail;
import com.project.idw.arhome.model.RekomendasiKontrakans;
import com.project.idw.arhome.pengguna.DetailRekomendasiKontrakanActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RekomendasiKontrakanAdapter extends RecyclerView.Adapter<RekomendasiKontrakanAdapter.RekomendasiKontrakanViewHolder> {

    private Context context;

    private ArrayList<RekomendasiKontrakans> dataList;
    public static final String TAG = RekomendasiKontrakanAdapter.class.getSimpleName();

    public RekomendasiKontrakanAdapter(Context context, ArrayList<RekomendasiKontrakans> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RekomendasiKontrakanAdapter.RekomendasiKontrakanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.daftar_rekomendasi_kontrakan_item, viewGroup, false);

        return new RekomendasiKontrakanAdapter.RekomendasiKontrakanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RekomendasiKontrakanAdapter.RekomendasiKontrakanViewHolder rekomendasiKontrakanViewHolder, final int i) {
        Picasso.with(context)
                .load(ServerConfig.KONTRAKAN_IMAGE+ dataList.get(i).getFoto())
//                .fit()
//                .centerCrop()
                .into(rekomendasiKontrakanViewHolder.iv_foto_rekomendasi_kontrakan);

        rekomendasiKontrakanViewHolder.tv_nama_rekomendasi_kontrakan.setText(dataList.get(i).getNama());
        rekomendasiKontrakanViewHolder.tv_harga_rekomendasi_kontrakan.setText("Rp. "+dataList.get(i).getHarga());
        rekomendasiKontrakanViewHolder.tv_alamat_rekomendasi_kontrakan.setText(dataList.get(i).getAlamat());
        rekomendasiKontrakanViewHolder.tv_rb_rekomendasi_kontrakan.setText("Skor: "+String.valueOf(dataList.get(i).getRating()));
        rekomendasiKontrakanViewHolder.rb_rekomendasi_kontrakan.setRating(Float.parseFloat(String.valueOf(dataList.get(i).getRating())));



        rekomendasiKontrakanViewHolder.cv_rekomendasi_kontrakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailRekomendasiKontrakanActivity.class);
                intent.putExtra(DetailRekomendasiKontrakanActivity.TAG_ID, dataList.get(i).getIdKontrakan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class RekomendasiKontrakanViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_foto_rekomendasi_kontrakan;
        TextView tv_nama_rekomendasi_kontrakan, tv_harga_rekomendasi_kontrakan, tv_alamat_rekomendasi_kontrakan;
        TextView tv_rb_rekomendasi_kontrakan;
        CardView cv_rekomendasi_kontrakan;
        RatingBar rb_rekomendasi_kontrakan;

        public RekomendasiKontrakanViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto_rekomendasi_kontrakan = itemView.findViewById(R.id.iv_foto_rekomendasi_kontrakan);
            tv_nama_rekomendasi_kontrakan = itemView.findViewById(R.id.tv_nama_rekomendasi_kontrakan);
            tv_harga_rekomendasi_kontrakan = itemView.findViewById(R.id.tv_harga_rekomendasi_kontrakan);
            tv_alamat_rekomendasi_kontrakan = itemView.findViewById(R.id.tv_alamat_rekomendasi_kontrakan);
            cv_rekomendasi_kontrakan = itemView.findViewById(R.id.cv_rekomendasi_kontrakan);
            tv_rb_rekomendasi_kontrakan = itemView.findViewById(R.id.tv_rb_rekomendasi_kontrakan);
            rb_rekomendasi_kontrakan = itemView.findViewById(R.id.rb_rekomendasi_kontrakan);
        }
    }
}



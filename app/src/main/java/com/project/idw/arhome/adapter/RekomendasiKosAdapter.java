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
import com.project.idw.arhome.common.DetailPemesananKosActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.PemesananKosDetail;
import com.project.idw.arhome.model.RekomendasiKoses;
import com.project.idw.arhome.pengguna.DetailRekomendasiKosActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RekomendasiKosAdapter extends RecyclerView.Adapter<RekomendasiKosAdapter.RekomendasiKosViewHolder> {

    private Context context;

    private ArrayList<RekomendasiKoses> dataList;
    public static final String TAG = RekomendasiKosAdapter.class.getSimpleName();

    public RekomendasiKosAdapter(Context context, ArrayList<RekomendasiKoses> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RekomendasiKosAdapter.RekomendasiKosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.daftar_rekomendasi_kos_item, viewGroup, false);

        return new RekomendasiKosAdapter.RekomendasiKosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RekomendasiKosAdapter.RekomendasiKosViewHolder rekomendasiKosViewHolder, final int i) {
        Picasso.with(context)
                .load(ServerConfig.KOS_IMAGE+ dataList.get(i).getFoto())
//                .fit()
//                .centerCrop()
                .into(rekomendasiKosViewHolder.iv_foto_rekomendasi_kos);


        rekomendasiKosViewHolder.tv_nama_rekomendasi_kos.setText(dataList.get(i).getNama());
        rekomendasiKosViewHolder.tv_harga_rekomendasi_kos.setText("Rp. "+dataList.get(i).getHarga());
        rekomendasiKosViewHolder.tv_alamat_rekomendasi_kos.setText(dataList.get(i).getAlamat());
        rekomendasiKosViewHolder.tv_jenis_rekomendasi_kos.setText(dataList.get(i).getJenisKos());
        rekomendasiKosViewHolder.tv_stok_kamar_rekomendasi_kos.setText(dataList.get(i).getStokKamar()+" Kamar");
        rekomendasiKosViewHolder.tv_rb_rekomendasi_kos.setText("Skor: "+String.valueOf(dataList.get(i).getRating()));
        rekomendasiKosViewHolder.rb_rekomendasi_kos.setRating(Float.parseFloat(String.valueOf(dataList.get(i).getRating())));

        rekomendasiKosViewHolder.cv_rekomendasi_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailRekomendasiKosActivity.class);
                intent.putExtra(DetailRekomendasiKosActivity.TAG_ID, dataList.get(i).getIdKos());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class RekomendasiKosViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_foto_rekomendasi_kos;
        TextView tv_nama_rekomendasi_kos, tv_harga_rekomendasi_kos, tv_alamat_rekomendasi_kos, tv_jenis_rekomendasi_kos, tv_stok_kamar_rekomendasi_kos;
        CardView cv_rekomendasi_kos;
        TextView tv_rb_rekomendasi_kos;
        RatingBar rb_rekomendasi_kos;

        public RekomendasiKosViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto_rekomendasi_kos = itemView.findViewById(R.id.iv_foto_rekomendasi_kos);
            tv_nama_rekomendasi_kos = itemView.findViewById(R.id.tv_nama_rekomendasi_kos);
            tv_harga_rekomendasi_kos = itemView.findViewById(R.id.tv_harga_rekomendasi_kos);
            tv_alamat_rekomendasi_kos = itemView.findViewById(R.id.tv_alamat_rekomendasi_kos);
            tv_jenis_rekomendasi_kos = itemView.findViewById(R.id.tv_jenis_rekomendasi_kos);
            tv_stok_kamar_rekomendasi_kos = itemView.findViewById(R.id.tv_stok_kamar_rekomendasi_kos);
            cv_rekomendasi_kos = itemView.findViewById(R.id.cv_rekomendasi_kos);
            rb_rekomendasi_kos = itemView.findViewById(R.id.rb_rekomendasi_kos);
            tv_rb_rekomendasi_kos = itemView.findViewById(R.id.tv_rb_rekomendasi_kos);
        }
    }
}



package com.project.idw.arhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.project.idw.arhome.R;
import com.project.idw.arhome.common.DetailPemesananKosActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.PemesananKosDetail;
import com.project.idw.arhome.model.PemesananKoses;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PemesananKosAdapter extends RecyclerView.Adapter<PemesananKosAdapter.PemesananKosViewHolder> {

    private Context context;

    private ArrayList<PemesananKoses> dataList;
    public static final String TAG = PemesananKosAdapter.class.getSimpleName();

    public PemesananKosAdapter(Context context, ArrayList<PemesananKoses> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public PemesananKosAdapter.PemesananKosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.daftar_pemesanan_kos_item, viewGroup, false);

        return new PemesananKosAdapter.PemesananKosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananKosAdapter.PemesananKosViewHolder pemesananKosViewHolder, final int i) {
        Picasso.with(context)
                .load(ServerConfig.KOS_IMAGE+ dataList.get(i).getFotoKos())
//                .fit()
//                .centerCrop()
                .into(pemesananKosViewHolder.iv_foto_pemesanan_kos);


        pemesananKosViewHolder.tv_id_pemesanan_kos.setText("Pemesanan ke- "+dataList.get(i).getIdPemesananKos());
        pemesananKosViewHolder.tv_nama_pemesanan_kos.setText(dataList.get(i).getNamaKos());
        pemesananKosViewHolder.tv_status_kos.setText(dataList.get(i).getStatus());
        pemesananKosViewHolder.tv_tanggal_pemesanan_kos.setText("Rp. "+dataList.get(i).getHarga());

        pemesananKosViewHolder.cv_pemesanan_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPemesananKosActivity.class);
                intent.putExtra(PemesananKosDetail.TAG_ID, dataList.get(i).getIdPemesananKos());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class PemesananKosViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_foto_pemesanan_kos;
        TextView tv_nama_pemesanan_kos, tv_id_pemesanan_kos, tv_tanggal_pemesanan_kos, tv_status_kos;
        CardView cv_pemesanan_kos;

        public PemesananKosViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto_pemesanan_kos = itemView.findViewById(R.id.iv_foto_pemesanan_kos);
            tv_nama_pemesanan_kos = itemView.findViewById(R.id.tv_nama_pemesanan_kos);
            tv_id_pemesanan_kos = itemView.findViewById(R.id.tv_id_pemesanan_kos);
            tv_tanggal_pemesanan_kos = itemView.findViewById(R.id.tv_tanggal_pemesanan_kos);
            tv_status_kos = itemView.findViewById(R.id.tv_status_kos);
            cv_pemesanan_kos = itemView.findViewById(R.id.cv_pemesanan_kos);
        }
    }
}



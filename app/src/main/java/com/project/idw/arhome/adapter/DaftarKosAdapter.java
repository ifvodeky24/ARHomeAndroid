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
import com.project.idw.arhome.common.DetailKosActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.KosDetail;
import com.project.idw.arhome.model.Kos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DaftarKosAdapter extends RecyclerView.Adapter<DaftarKosAdapter.DaftarKosViewHolder> {

    private Context context;

    private ArrayList<Kos> dataList;
    public static final String TAG = DaftarKosAdapter.class.getSimpleName();

    public DaftarKosAdapter(Context context, ArrayList<Kos> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DaftarKosAdapter.DaftarKosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.daftar_kos_item, viewGroup, false);

        return new DaftarKosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarKosAdapter.DaftarKosViewHolder daftarKosViewHolder, final int i) {
        Picasso.with(context)
                .load(ServerConfig.KOS_IMAGE+ dataList.get(i).getFoto())
//                .fit()
//                .centerCrop()
                .into(daftarKosViewHolder.iv_foto_kos);

        daftarKosViewHolder.tv_nama_kos.setText(dataList.get(i).getNama());
        daftarKosViewHolder.tv_harga_kos.setText("Rp. " + dataList.get(i).getHarga() + " / Bulan");
        daftarKosViewHolder.tv_alamat_kos.setText(dataList.get(i).getAlamat());
        daftarKosViewHolder.tv_stok_kamar_kos.setText(dataList.get(i).getStokKamar() + " Kamar");
        daftarKosViewHolder.tv_jenis_kos.setText(dataList.get(i).getJenisKos());

        daftarKosViewHolder.cv_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailKosActivity.class);
                intent.putExtra(KosDetail.TAG_ID, dataList.get(i).getIdKos());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class DaftarKosViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_foto_kos;
        TextView tv_nama_kos, tv_harga_kos, tv_stok_kamar_kos, tv_jenis_kos, tv_alamat_kos;
        CardView cv_kos;

        public DaftarKosViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto_kos = itemView.findViewById(R.id.iv_foto_kos);
            tv_nama_kos = itemView.findViewById(R.id.tv_nama_kos);
            tv_harga_kos = itemView.findViewById(R.id.tv_harga_kos);
            tv_stok_kamar_kos = itemView.findViewById(R.id.tv_stok_kamar_kos);
            tv_jenis_kos = itemView.findViewById(R.id.tv_jenis_kos);
            tv_alamat_kos = itemView.findViewById(R.id.tv_alamat_kos);
            cv_kos =  itemView.findViewById(R.id.cv_kos);
        }
    }
}


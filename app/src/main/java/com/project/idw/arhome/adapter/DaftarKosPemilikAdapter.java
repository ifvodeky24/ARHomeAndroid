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
import com.project.idw.arhome.model.KosPemilik;
import com.project.idw.arhome.pemilik_iklan.DetailKosPemilikActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DaftarKosPemilikAdapter extends RecyclerView.Adapter<DaftarKosPemilikAdapter.DaftarKosPemilikViewHolder> {

    private Context context;

    private ArrayList<KosPemilik> dataList;
    public static final String TAG = DaftarKontrakanAdapter.class.getSimpleName();

    public DaftarKosPemilikAdapter(Context context, ArrayList<KosPemilik> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DaftarKosPemilikAdapter.DaftarKosPemilikViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.daftar_kos_pemilik_item, viewGroup, false);

        return new DaftarKosPemilikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarKosPemilikAdapter.DaftarKosPemilikViewHolder daftarKosPemilikViewHolder, final int i) {
        Picasso.with(context)
                .load(ServerConfig.KOS_IMAGE+ dataList.get(i).getFotoKos())
//                .fit()
//                .centerCrop()
                .into(daftarKosPemilikViewHolder.iv_foto_kos);

        daftarKosPemilikViewHolder.tv_nama_kos.setText(dataList.get(i).getNamaKos());
        daftarKosPemilikViewHolder.tv_harga_kos.setText("Rp. " + dataList.get(i).getHarga() + " / Bulan");
        daftarKosPemilikViewHolder.tv_alamat_kos.setText(dataList.get(i).getAlamatKos());
        daftarKosPemilikViewHolder.tv_stok_kamar_kos.setText(dataList.get(i).getStokKamar() + " Kamar");
        daftarKosPemilikViewHolder.tv_jenis_kos.setText(dataList.get(i).getJenisKos());

        daftarKosPemilikViewHolder.cv_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailKosPemilikActivity.class);
                intent.putExtra(KosDetail.TAG_ID, dataList.get(i).getIdKos());
                System.out.println("id:" +dataList.get(i).getIdKos());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class DaftarKosPemilikViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_foto_kos;
        TextView tv_nama_kos, tv_harga_kos, tv_stok_kamar_kos, tv_jenis_kos, tv_alamat_kos;
        CardView cv_kos;

        public DaftarKosPemilikViewHolder(@NonNull View itemView) {
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

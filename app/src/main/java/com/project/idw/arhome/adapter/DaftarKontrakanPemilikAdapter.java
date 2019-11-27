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
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.KontrakanDetail;
import com.project.idw.arhome.model.KontrakanPemilik;
import com.project.idw.arhome.pemilik_iklan.DetailKontrakanPemilikActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DaftarKontrakanPemilikAdapter extends RecyclerView.Adapter<DaftarKontrakanPemilikAdapter.DaftarKontrakanPemilikViewHolder> {

    private Context context;

    private ArrayList<KontrakanPemilik> dataList;
    public static final String TAG = DaftarKontrakanPemilikAdapter.class.getSimpleName();

    public DaftarKontrakanPemilikAdapter(Context context, ArrayList<KontrakanPemilik> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DaftarKontrakanPemilikAdapter.DaftarKontrakanPemilikViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.daftar_kontrakan_pemilik_item, viewGroup, false);

        return new DaftarKontrakanPemilikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarKontrakanPemilikAdapter.DaftarKontrakanPemilikViewHolder daftarKontrakanPemilikViewHolder, final int i) {
        Picasso.with(context)
                .load(ServerConfig.KONTRAKAN_IMAGE+ dataList.get(i).getFotoKontrakan())
//                .fit()
//                .centerCrop()
                .into(daftarKontrakanPemilikViewHolder.iv_foto_kontrakan);

        daftarKontrakanPemilikViewHolder.tv_nama_kontrakan.setText(dataList.get(i).getNamaKontrakan());
        daftarKontrakanPemilikViewHolder.tv_harga_kontrakan.setText("Rp. " + dataList.get(i).getHarga() +" / Tahun");
        daftarKontrakanPemilikViewHolder.tv_alamat_kontrakan.setText(dataList.get(i).getAlamatKontrakan());

        daftarKontrakanPemilikViewHolder.cv_kontrakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailKontrakanPemilikActivity.class);
                intent.putExtra(KontrakanDetail.TAG_ID, dataList.get(i).getIdKontrakan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class DaftarKontrakanPemilikViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_foto_kontrakan;
        TextView tv_nama_kontrakan, tv_harga_kontrakan, tv_alamat_kontrakan;
        CardView cv_kontrakan;

        public DaftarKontrakanPemilikViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto_kontrakan = itemView.findViewById(R.id.iv_foto_kontrakan);
            tv_nama_kontrakan = itemView.findViewById(R.id.tv_nama_kontrakan);
            tv_harga_kontrakan = itemView.findViewById(R.id.tv_harga_kontrakan);
            tv_alamat_kontrakan = itemView.findViewById(R.id.tv_alamat_kontrakan);
            cv_kontrakan = itemView.findViewById(R.id.cv_kontrakan);
        }
    }
}

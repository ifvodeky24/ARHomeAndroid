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
import com.project.idw.arhome.common.DetailKontrakanActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.Kontrakan;
import com.project.idw.arhome.model.KontrakanDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DaftarKontrakanAdapter extends RecyclerView.Adapter<DaftarKontrakanAdapter.DaftarKontrakanViewHolder> {

    private Context context;

    private ArrayList<Kontrakan> dataList;
    public static final String TAG = DaftarKontrakanAdapter.class.getSimpleName();

    public DaftarKontrakanAdapter(Context context, ArrayList<Kontrakan> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DaftarKontrakanAdapter.DaftarKontrakanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.daftar_kontrakan_item, viewGroup, false);

        return new DaftarKontrakanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarKontrakanAdapter.DaftarKontrakanViewHolder daftarKontrakanViewHolder, final int i) {
        Picasso.with(context)
                .load(ServerConfig.KONTRAKAN_IMAGE+ dataList.get(i).getFoto())
//                .fit()
//                .centerCrop()
                .into(daftarKontrakanViewHolder.iv_foto_kontrakan);

        daftarKontrakanViewHolder.tv_nama_kontrakan.setText(dataList.get(i).getNama());
        daftarKontrakanViewHolder.tv_harga_kontrakan.setText("Rp. " + dataList.get(i).getHarga() +" / Tahun");
        daftarKontrakanViewHolder.tv_alamat_kontrakan.setText(dataList.get(i).getAlamat());

        daftarKontrakanViewHolder.cv_kontrakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailKontrakanActivity.class);
                intent.putExtra(KontrakanDetail.TAG_ID, dataList.get(i).getIdKontrakan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class DaftarKontrakanViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_foto_kontrakan;
        TextView tv_nama_kontrakan, tv_harga_kontrakan, tv_alamat_kontrakan;
        CardView cv_kontrakan;

        public DaftarKontrakanViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto_kontrakan = itemView.findViewById(R.id.iv_foto_kontrakan);
            tv_nama_kontrakan = itemView.findViewById(R.id.tv_nama_kontrakan);
            tv_harga_kontrakan = itemView.findViewById(R.id.tv_harga_kontrakan);
            tv_alamat_kontrakan = itemView.findViewById(R.id.tv_alamat_kontrakan);
            cv_kontrakan = itemView.findViewById(R.id.cv_kontrakan);
        }
    }
}

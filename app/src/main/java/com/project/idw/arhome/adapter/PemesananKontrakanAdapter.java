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
import com.project.idw.arhome.common.DetailPemesananKontrakanActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.PemesananKontrakanDetail;
import com.project.idw.arhome.model.PemesananKontrakans;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class PemesananKontrakanAdapter extends RecyclerView.Adapter<PemesananKontrakanAdapter.PemesananKontrakanViewHolder> {

    private Context context;

    private ArrayList<PemesananKontrakans> dataList;
    public static final String TAG = PemesananKontrakanAdapter.class.getSimpleName();

    public PemesananKontrakanAdapter(Context context, ArrayList<PemesananKontrakans> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public PemesananKontrakanAdapter.PemesananKontrakanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.daftar_pemesanan_kontrakan_item, viewGroup, false);

        return new PemesananKontrakanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananKontrakanAdapter.PemesananKontrakanViewHolder pemesananKontrakanViewHolder, final int i) {
        Picasso.with(context)
                .load(ServerConfig.KONTRAKAN_IMAGE+ dataList.get(i).getFotoKontrakan())
//                .fit()
//                .centerCrop()
                .into(pemesananKontrakanViewHolder.iv_foto_pemesanan_kontrakan);

        pemesananKontrakanViewHolder.tv_id_pemesanan_kontrakan.setText("Pemesanan ke- "+dataList.get(i).getIdPemesananKontrakan());
        pemesananKontrakanViewHolder.tv_status_kontrakan.setText(dataList.get(i).getStatus());
        pemesananKontrakanViewHolder.tv_nama_pemesanan_kontrakan.setText(dataList.get(i).getNamaKontrakan());
        pemesananKontrakanViewHolder.tv_tanggal_pemesanan_kontrakan.setText("Rp. "+dataList.get(i).getHarga());


        pemesananKontrakanViewHolder.cv_pemesanan_kontrakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPemesananKontrakanActivity.class);
                intent.putExtra(PemesananKontrakanDetail.TAG_ID, dataList.get(i).getIdPemesananKontrakan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class PemesananKontrakanViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_foto_pemesanan_kontrakan;
        TextView tv_nama_pemesanan_kontrakan, tv_id_pemesanan_kontrakan, tv_tanggal_pemesanan_kontrakan, tv_status_kontrakan;
        CardView cv_pemesanan_kontrakan;

        public PemesananKontrakanViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto_pemesanan_kontrakan = itemView.findViewById(R.id.iv_foto_pemesanan_kontrakan);
            tv_nama_pemesanan_kontrakan = itemView.findViewById(R.id.tv_nama_pemesanan_kontrakan);
            tv_id_pemesanan_kontrakan = itemView.findViewById(R.id.tv_id_pemesanan_kontrakan);
            tv_tanggal_pemesanan_kontrakan = itemView.findViewById(R.id.tv_tanggal_pemesanan_kontrakan);
            tv_status_kontrakan = itemView.findViewById(R.id.tv_status_kontrakan);
            cv_pemesanan_kontrakan = itemView.findViewById(R.id.cv_pemesanan_kontrakan);
        }
    }
}


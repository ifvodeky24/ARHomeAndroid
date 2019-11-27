package com.project.idw.arhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.idw.arhome.R;
import com.project.idw.arhome.common.MessageActivity;
import com.project.idw.arhome.config.ServerConfig;
import com.project.idw.arhome.model.Pemilik;
import com.squareup.picasso.Picasso;

import java.util.List;

import afu.org.checkerframework.checker.nullness.qual.NonNull;
import de.hdodenhof.circleimageview.CircleImageView;

public class PemilikAdapter extends RecyclerView.Adapter<PemilikAdapter.ViewHolder> {

    private Context mContext;
    private List<Pemilik> datalist;

    public PemilikAdapter(Context mContext, List<Pemilik> datalist){
        this.datalist = datalist;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, viewGroup, false );
        return new PemilikAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.nama_lengkap.setText(datalist.get(i).getNamaLengkap());
        Picasso.with(mContext)
                .load(ServerConfig.PROFIL_IMAGE+ datalist.get(i).getFoto())
//                .fit()
//                .centerCrop()
                .into(holder.foto_profil);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra(MessageActivity.KEY_ID, datalist.get(i).getIdPemilik());
                intent.putExtra(MessageActivity.KEY_NAMA_LENGKAP_PEMILIK, datalist.get(i).getNamaLengkap());
                intent.putExtra(MessageActivity.KEY_FOTO_PEMILIK, datalist.get(i).getFoto());
                intent.putExtra(Pemilik.TAG_ID, datalist.get(i).getIdPemilik());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nama_lengkap;
        public CircleImageView foto_profil;
        public RelativeLayout rl_chat;

        public ViewHolder(View itemView) {
            super(itemView);

            nama_lengkap = itemView.findViewById(R.id.nama_lengkap);
            foto_profil = itemView.findViewById(R.id.foto_profil);
            rl_chat = itemView.findViewById(R.id.rl_chat);
        }
    }
}


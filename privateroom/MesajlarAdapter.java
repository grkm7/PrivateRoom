package com.info.privateroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MesajlarAdapter extends RecyclerView.Adapter<MesajlarAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Mesajlar> mesajlarListe;

    public MesajlarAdapter(Context mContext, List<Mesajlar> mesajlarListe) {
        this.mContext = mContext;
        this.mesajlarListe = mesajlarListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Mesajlar mesaj= mesajlarListe.get(position);

        holder.tvMesajAtan.setText(mesaj.getMesaj_atan());
        holder.tvMesaj.setText(mesaj.getMesaj());
    }

    @Override
    public int getItemCount() {
        return mesajlarListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView tvMesajAtan;
        private TextView tvMesaj;
        private CardView mesaj_card;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            tvMesajAtan=itemView.findViewById(R.id.tvMesajAtan);
            tvMesaj=itemView.findViewById(R.id.tvMesaj);
            mesaj_card=itemView.findViewById(R.id.mesaj_card);
        }
    }
}

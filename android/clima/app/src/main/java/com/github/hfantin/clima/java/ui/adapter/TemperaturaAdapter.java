package com.github.hfantin.clima.java.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import bb.com.br.clima.R;
import com.github.hfantin.clima.java.domain.model.Temperatura;

public class TemperaturaAdapter extends RecyclerView.Adapter<TemperaturaAdapter.ViewHolder> {

    private final List<Temperatura> mTemperaturas;
    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt","BR"));
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,0");
    private final AdapterView.OnItemClickListener mItemClickListener;

    public TemperaturaAdapter(final List<Temperatura> temperaturas, AdapterView.OnItemClickListener itemClickListener) {
        mTemperaturas = temperaturas;
        mItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Temperatura item =  mTemperaturas.get(position);
        holder.icone.setImageResource(item.getIcone());
        holder.data.setText(dateFormat.format(item.getData()));
        holder.descricao.setText(item.getDescricao());
        holder.temperaturaMaxima.setText(decimalFormat.format(item.getTemperaturaMaxima())+"°");
        holder.temperaturaMinima.setText(decimalFormat.format(item.getTemperaturaMinima())+"°");
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(null, view, position, 0L);
            }
        });
    }

    public Temperatura getItem(Integer position){
        return mTemperaturas.get(position);

    }

    @Override
    public int getItemCount() {
        return mTemperaturas!=null ? mTemperaturas.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icone;
        public TextView data;
        public TextView descricao;
        public TextView temperaturaMaxima;
        public TextView temperaturaMinima;

        public ViewHolder(View itemView) {
            super(itemView);

            icone = itemView.findViewById(R.id.icone);
            data = itemView.findViewById(R.id.data);
            descricao = itemView.findViewById(R.id.descricao);
            temperaturaMaxima = itemView.findViewById(R.id.temperaturaMaxima);
            temperaturaMinima = itemView.findViewById(R.id.temperaturaMinima);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
                }
            });
        }
    }

}

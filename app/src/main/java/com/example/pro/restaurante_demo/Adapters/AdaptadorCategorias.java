package com.example.pro.restaurante_demo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pro.restaurante_demo.Models.Comida;
import com.example.pro.restaurante_demo.R;

import java.util.List;

/**
 * Created by pro on 16/02/16.
 */
public class AdaptadorCategorias extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> {

    private final List<Comida> items;

    public AdaptadorCategorias(List<Comida> items) {
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_categorias, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Comida item = items.get(position);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);

        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("$" + item.getPrecio());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}

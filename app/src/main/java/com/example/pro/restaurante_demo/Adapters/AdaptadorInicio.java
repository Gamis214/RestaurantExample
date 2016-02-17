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

/**
 * Created by pro on 16/02/16.
 */
public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {

    /**
     * ESTE METODO NOS SIRVE PARA DECLARAR LAS VARIABLES DENTRO DE LA CLASE ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
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

    /**
     * METODO QUE SE EJECUTA AL INICIAR EL ADAPTADOR onCreate()
     */
    @Override
    public AdaptadorInicio.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v);
    }

    /**
     * METODO QUE SE EJECUTA UNA VEZ CREADO EL ADAPTADOR PARA PINTAR EL ITEM.
     */
    @Override
    public void onBindViewHolder(AdaptadorInicio.ViewHolder viewHolder, int position) {
        Comida item = Comida.COMIDAS_POPULARES.get(position);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("$" + item.getPrecio());
    }

    @Override
    public int getItemCount() {
        return Comida.COMIDAS_POPULARES.size();
    }
}

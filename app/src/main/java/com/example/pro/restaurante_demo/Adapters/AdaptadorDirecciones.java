package com.example.pro.restaurante_demo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pro.restaurante_demo.Models.Direccion;
import com.example.pro.restaurante_demo.R;

/**
 * Created by pro on 16/02/16.
 */
public class AdaptadorDirecciones extends RecyclerView.Adapter<AdaptadorDirecciones.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        // Campos respectivos de un item
        public TextView direccion;
        public TextView departamento;
        public TextView ciudad;
        public TextView telefono;

        public ViewHolder(View v) {
            super(v);
            direccion = (TextView) v.findViewById(R.id.texto_direccion);
            departamento = (TextView) v.findViewById(R.id.texto_departamento);
            ciudad = (TextView) v.findViewById(R.id.texto_ciudad);
            telefono = (TextView) v.findViewById(R.id.texto_telefono);
        }
    }

    @Override
    public AdaptadorDirecciones.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_direcciones, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorDirecciones.ViewHolder viewHolder, int position) {
        Direccion item = Direccion.DIRECCIONES.get(position);
        viewHolder.direccion.setText(item.getNumeroDireccion());
        viewHolder.departamento.setText(item.getDepartamento());
        viewHolder.ciudad.setText(item.getCiudad());
        viewHolder.telefono.setText(item.getTelefono());
    }

    @Override
    public int getItemCount() {
        return Direccion.DIRECCIONES.size();
    }
}

package net.jesusramirez.cargarimagenes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//ESTA CLASE ES EL ADAPTADOR DEL RECYCLER VIEW COMO EN OTRA PRACTICAS ANTERIORES ES NECESARIO PARA CARGAR LO QUE
//SE VA A VER DENTRO DEL RECYCLER
public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.SuperViewHolder> {
    List<Heroe> lista;
    Context context;

    public AdaptadorRecycler(Context context, List<Heroe> list) {
        this.context = context;
        this.lista = list;
    }
    @NonNull
    @Override
    public SuperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento, parent, false);
        return new SuperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, int position) {
        holder.txtNombre.setText(lista.get(position).getName());
        //SE USA LA LIBRERIA PICASSO PARA CARGAR LAS IMAGENES DESDE EL INTERNET SOLO CON EL URL
        Picasso.with(context).load(lista.get(position).getImageUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class SuperViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtNombre;

        public SuperViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgFoto);
            txtNombre = itemView.findViewById(R.id.txtNombre);
        }
    }
}


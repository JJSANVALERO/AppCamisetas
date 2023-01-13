package com.svalero.appCamisetas.adapter;

import static com.svalero.appCamisetas.db.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.appCamisetas.R;
import com.svalero.appCamisetas.db.AppDatabase;
import com.svalero.appCamisetas.domain.Objeto;

import java.util.List;

//Se hace siempre para añadirlo a un RecyclerView
public class ObjetoAdapter extends RecyclerView.Adapter<ObjetoAdapter.ObjetoHolder> {

    private Context context;
    private List<Objeto> objetoList;

    public ObjetoAdapter (Context context, List<Objeto> objetoList) {
        this.context = context;
        this.objetoList = objetoList;
    }

    @Override
    public ObjetoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.objeto_item, parent, false);
        return new ObjetoHolder(view);
    }


    @Override
    public void onBindViewHolder(ObjetoAdapter holder, int position) {
        holder.id.setText(objetoList.get(position).getId());
        //Así con cada valor que queremos traer del objeto
        //
        //
        //
    }

    @Override
    public int getItemCount() {
        return objetoList.size();
    }

    public class ObjetoHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public View parentView;

        public ObjetoHolder(View view){
            super(view);
            parentView = view;

            id = view.findViewById(R.id.tvId);


            eliminarObjeto.setOnClickListener(v -> borrarObjeto(getAdapterPosition()));
            detalleObjeto.setOnClickListener(v -> verDetalles(getAdapterPosition()));
        }
    }

    private void borrarObjeto (int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Estas seguro?")
                .setTitle("Eliminar objeto")
                .setPositiveButton("Yes", (dialog, id) -> {
                    final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries().build();
                    Objeto objeto = objetoList.get(position);
                    db.objetoDao().delete(objeto);

                    objetoListList.remove(position);
                    notifyItemRemoved(position);
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void verDetalles(int position) {
        Objeto objeto = objetoList.get(position);

        Intent intent = new Intent(context, ObjetoDetalleActiviti.class);
        intent.putExtra("id", objeto.getId());
        context.startActivity(intent);
    }
}

package com.example.sanba;

import android.app.SearchManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.TaskExecutor;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BuqueAdapter extends ListAdapter<buqueClase, BuqueAdapter.BuqueHolder>{

    // private View.OnClickListener listener;
    private OnItemClickListener listener;

    //Este super constructor es para verificar si los items o sus contenidos han cambiado
    public BuqueAdapter (){
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<buqueClase> DIFF_CALLBACK = new
            DiffUtil.ItemCallback<buqueClase>() {
        @Override
        public boolean areItemsTheSame(@NonNull buqueClase oldItem, @NonNull buqueClase newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull buqueClase oldItem, @NonNull buqueClase newItem) {
            return oldItem.getNombreBuque().equals(newItem.getNombreBuque()) &&
                    oldItem.getOperacion().equals(newItem.getOperacion()) &&
                    oldItem.getNombreProducto().equals(newItem.getNombreProducto()) &&
                    oldItem.getNominacion() == newItem.getNominacion() &&
                    oldItem.getTL().equals(newItem.getTL()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public BuqueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.buque_item, parent,
                false);

        //   itemView.setOnClickListener(this);
        return new BuqueHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuqueHolder holder, int position) {
        buqueClase currentBuque = getItem(position);
        holder.textViewNombreBuque.setText(currentBuque.getNombreBuque());
        holder.textViewOperacion.setText(currentBuque.getOperacion());
        holder.textViewNombreProducto.setText(currentBuque.getNombreProducto());
        holder.textViewTL.setText(currentBuque.getTL());
        holder.textViewNominacion.setText(String.valueOf(currentBuque.getNominacion()));
        holder.textViewPriority.setText(String.valueOf(currentBuque.getPriority()));


    }





    public buqueClase getBuqueAt(int position) {
        return getItem(position);
    }

/*    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }*/

    class BuqueHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombreBuque;
        private TextView textViewOperacion;
        private TextView textViewNombreProducto;
        private TextView textViewTL;
        private TextView textViewNominacion;
        private TextView textViewPriority;

        public BuqueHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreBuque = itemView.findViewById(R.id.text_view_nombreBuque);
            textViewOperacion = itemView.findViewById(R.id.text_view_operacion);
            textViewNombreProducto = itemView.findViewById(R.id.text_view_nombre_producto);
            textViewTL = itemView.findViewById(R.id.text_view_TL);
            textViewNominacion = itemView.findViewById(R.id.text_view_nominacion);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(buqueClase buque);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

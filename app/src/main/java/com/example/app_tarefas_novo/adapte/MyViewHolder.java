package com.example.app_tarefas_novo.adapte;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tarefas_novo.R;

import org.jspecify.annotations.NonNull;

public class MyViewHolder extends RecyclerView.ViewHolder{

    public TextView nomeTarefa;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;

    public ImageButton btnInicio;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nomeTarefa = (TextView) itemView.findViewById(R.id.tarefa2);
        btnEditar = (ImageButton) itemView.findViewById(R.id.btnEdit);
        btnExcluir = (ImageButton) itemView.findViewById(R.id.btnDelete);
        btnInicio = (ImageButton) itemView.findViewById(R.id.inicio);


    }
}

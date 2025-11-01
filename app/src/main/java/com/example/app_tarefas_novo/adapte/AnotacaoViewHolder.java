package com.example.app_tarefas_novo.adapte;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tarefas_novo.R;

public class AnotacaoViewHolder extends RecyclerView.ViewHolder {

    public TextView txtAnotacao;

    public ImageButton btEditarAnotacao;

    public  ImageButton btDeleteAnotacao;

    public  ImageButton btInicioAnotacao;

    public AnotacaoViewHolder(@NonNull View itemView) {
        super(itemView);
        txtAnotacao = (TextView) itemView.findViewById(R.id.Anotacao);
        btEditarAnotacao = (ImageButton) itemView.findViewById(R.id.btnEditAnotacao);
        btDeleteAnotacao = (ImageButton) itemView.findViewById(R.id.btnDeleteAnotacao);
        btInicioAnotacao = (ImageButton) itemView.findViewById(R.id.inicio);
    }
}

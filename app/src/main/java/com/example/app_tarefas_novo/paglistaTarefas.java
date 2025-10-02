package com.example.app_tarefas_novo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tarefas_novo.DB_Helper.TarefaDAO;
import com.example.app_tarefas_novo.adapte.TarefaAdapte;
import com.example.app_tarefas_novo.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class paglistaTarefas extends AppCompatActivity {

    // configurarRecycler
    private RecyclerView recycler;

    private TarefaAdapte tarefaAdapte;

    private List<Tarefa> listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paglista_tarefas);

        // Configurando o gerenciador de layout para ser uma lista.
        recycler = findViewById(R.id.recyclerView);

        listaTarefas = new ArrayList<>();

        tarefaAdapte = new TarefaAdapte(listaTarefas);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(tarefaAdapte);

        configurarRecycler();
    }


    @Override
    protected void onResume() {
        configurarRecycler();
        super.onResume();
    }

    private void configurarRecycler() {

        // Adiciona o adapter que irá anexar os objetos à lista.
        TarefaDAO tarefaDAO = new TarefaDAO(this);

        listaTarefas = tarefaDAO.listar();

        tarefaAdapte.setListaTarefas(listaTarefas);
        tarefaAdapte.notifyDataSetChanged();

    }
}
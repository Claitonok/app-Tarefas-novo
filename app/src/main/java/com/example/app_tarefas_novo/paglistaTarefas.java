package com.example.app_tarefas_novo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tarefas_novo.DB_Helper.TarefaDAO;
import com.example.app_tarefas_novo.adapte.TarefaAdapte;

public class paglistaTarefas extends AppCompatActivity {

    // configurarRecycler
    private RecyclerView recycler;

    private TarefaAdapte tarefaAdapte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paglista_tarefas);

        configurarRecycler();
    }

    private void configurarRecycler() {

        // Adiciona o adapter que irá anexar os objetos à lista.
        TarefaDAO tarefaDAO = new TarefaDAO(this);

        // Configurando o gerenciador de layout para ser uma lista.
        recycler = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        tarefaAdapte = new TarefaAdapte(tarefaDAO.listar());
        recycler.setAdapter(tarefaAdapte);
        recycler.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }
}
package com.example.app_tarefas_novo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tarefas_novo.DB_Helper.AnotacaoDAO;
import com.example.app_tarefas_novo.adapte.AnotacaoAdapte;
import com.example.app_tarefas_novo.model.Anotacao;

import java.util.ArrayList;
import java.util.List;

public class Lista_anotacao extends AppCompatActivity {

    private AnotacaoAdapte anotacaoAdapte;

    private RecyclerView recyclerViewAnotacao;

    private List<Anotacao> listeAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_anotacao);

        // Configurando o gerenciador de layout para ser uma lista.
        recyclerViewAnotacao = findViewById(R.id.re_anotacao);

        listeAnotacao = new ArrayList<>();

        anotacaoAdapte = new AnotacaoAdapte(listeAnotacao);

        recyclerViewAnotacao.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAnotacao.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAnotacao.setAdapter(anotacaoAdapte);

        configurarRecyclerAnotacao();
    }



    @Override
    protected void onResume() {
        configurarRecyclerAnotacao();
        super.onResume();
    }

private void configurarRecyclerAnotacao() {

    // Adiciona o adapter que irá anexar os objetos à lista.
    AnotacaoDAO anotacaoDAO = new AnotacaoDAO(this);

    listeAnotacao = anotacaoDAO.listarAnotacoes();

    anotacaoAdapte.setListaAnotacao(listeAnotacao);
    anotacaoAdapte.notifyDataSetChanged();

}

}


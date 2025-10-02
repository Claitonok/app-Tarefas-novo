package com.example.app_tarefas_novo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

import com.example.app_tarefas_novo.DB_Helper.TarefaDAO;
import com.example.app_tarefas_novo.adapte.TarefaAdapte;
import com.example.app_tarefas_novo.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    private Button bt_ta;

    private Button bt_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Aqui temos a renderização do nosso botão do APP
        bt_ta = findViewById(R.id.bt_tarefas);
        bt_ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), adicionar_tarefa.class);
                startActivity(intent);
            }
        });

        bt_lista = findViewById(R.id.bt_lista_tarefa);
        bt_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), paglistaTarefas.class);
                startActivity(intent);
            }
        });

//        TarefaDAO tarefaDAO = new TarefaDAO(this);
//        tarefaDAO.deletar(7);

    }

}
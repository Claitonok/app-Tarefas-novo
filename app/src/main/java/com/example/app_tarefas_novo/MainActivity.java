package com.example.app_tarefas_novo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bt_ta;

    private Button bt_Anotacoes;

    private Button bt_lista;

    private Button bt_lis_anote;

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

        // Pag de adicionar uma Anotação!
        bt_Anotacoes = findViewById(R.id.bt_Anota);
        bt_Anotacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Anotacoes.class);
                startActivity(intent);
            }
        });

        bt_lis_anote = findViewById(R.id.bt_list);
        bt_lis_anote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lista_anotacao.class);
                startActivity(intent);
            }
        });






    }

}
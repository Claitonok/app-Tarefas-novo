package com.example.app_tarefas_novo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_tarefas_novo.DB_Helper.TarefaDAO;
import com.example.app_tarefas_novo.adapte.TarefaAdapte;
import com.example.app_tarefas_novo.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class adicionar_tarefa extends AppCompatActivity {

    private Button save;

    private TextInputEditText tarefaNova;

    private Button bt_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        //Pegando a informação vindo do Front!!
        tarefaNova = findViewById(R.id.textTarefaNova);


        save = findViewById(R.id.bt_salvar);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!tarefaNova.getText().toString().isEmpty()){

                    //Minha variavel vindo do Front-End
                    String nomeTarefa = tarefaNova.getText().toString();

                    //executar a ação salvar
                    TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                    Tarefa tr = new Tarefa();
                    tr.setNomeTarefa(nomeTarefa);

                    boolean sucesso = tarefaDAO.salvar(tr);

                    if(sucesso){

                        Tarefa tarefa = tarefaDAO.retornarUltimo();

                        (new TarefaAdapte()).adicionar(tarefa);

                        Toast.makeText(getApplicationContext(),
                                "Sucesso ao salvar a tarefa",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Erro ao salvar, consulte os logs!",
                                Toast.LENGTH_LONG).show();
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Ops!!")
                            .setMessage("Por favor digite uma tarefa?")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(),
                                            "Por favor digite uma tarefa?!",
                                            Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Cancelar", null)
                            .create()
                            .show();
                }



            }
        });


        bt_home = findViewById(R.id.HOME);

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * AQUI O APP VOLTA PARA A HOME
                 * */
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
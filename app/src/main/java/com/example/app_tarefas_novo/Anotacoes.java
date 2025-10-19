package com.example.app_tarefas_novo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_tarefas_novo.DB_Helper.AnotacaoDAO;
import com.example.app_tarefas_novo.model.Anotacao;
import com.google.android.material.textfield.TextInputEditText;

public class Anotacoes extends AppCompatActivity {

    private TextInputEditText anotacao;
    private Button save;
    private Button bt_home_pag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacoes);


        //Pegando a informação vindo do Front!!
        anotacao = findViewById(R.id.text_Anotacao);

        save = findViewById(R.id.bt_salvar_Anotacao);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!anotacao.getText().toString().isEmpty()){

                    //Minha variavel vindo do Front-End
                    String nomeAnotacao = anotacao.getText().toString();

                    //executar a ação salvar
                    AnotacaoDAO anotacaoDAO = new AnotacaoDAO(getApplicationContext());

                    Anotacao an = new Anotacao();
                    an.setAnotacao(nomeAnotacao);

                    boolean sucesso = anotacaoDAO.salvarAnotacoes(an);

                    if(sucesso){

                        anotacaoDAO.retornarUltimo_Anotacao();

                        Toast.makeText(getApplicationContext(),
                                "Sucesso ao salvar a Anotação",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Erro ao salvar, consulte os logs!",
                                Toast.LENGTH_LONG).show();
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Ops!!")
                            .setMessage("Por favor digite uma Anotação?")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(),
                                            "Por favor digite uma Anotação?!",
                                            Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Cancelar", null)
                            .create()
                            .show();
                }
            }
        });


        //AQUI O APP VOLTA PARA A HOME
        bt_home_pag = findViewById(R.id.HOME);
        bt_home_pag.setOnClickListener(new View.OnClickListener() {
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
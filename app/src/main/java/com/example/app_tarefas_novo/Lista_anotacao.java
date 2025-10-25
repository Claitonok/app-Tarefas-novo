package com.example.app_tarefas_novo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tarefas_novo.DB_Helper.AnotacaoDAO;
import com.example.app_tarefas_novo.model.Anotacao;

import java.util.ArrayList;
import java.util.List;

public class Lista_anotacao extends AppCompatActivity {

    private ImageButton ini;

    private RecyclerView recy;

    private List<Anotacao> listeAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_anotacao);


        ini = findViewById(R.id.inicio);
        ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }



}

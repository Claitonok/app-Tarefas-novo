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

import com.example.app_tarefas_novo.DB_Helper.AnotacaoDAO;

import java.util.ArrayList;

public class Lista_anotacao extends AppCompatActivity {

    private ImageButton ini;

    private ListView listv;

    private ArrayList<String> itens;
    private ArrayAdapter<String> adapter;
    private AnotacaoDAO anotacaoDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_anotacao);

        listv = findViewById(R.id.lis_vie);
        itens = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itens);
        listv.setAdapter(adapter);
//        listarDados();



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
        listarDados();
    }

    private void listarDados(){
        Cursor cursor = anotacaoDAO.teste();
        if (cursor.moveToFirst()) {
            do {
                String anotacao = cursor.getString(cursor.getColumnIndex("Anotacao"));
                itens.add(anotacao);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();

    }

}

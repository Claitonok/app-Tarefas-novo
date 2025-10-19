package com.example.app_tarefas_novo.DB_Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper_Anotacoes extends SQLiteOpenHelper {

    public static int VERSION = 1;

    public static String ANOTACOES_LISTA = "anotacoes";

    public static String NOME_DB_AN = "DB_Anotacoes";

    public DbHelper_Anotacoes(@Nullable Context context) {
        super(context, NOME_DB_AN, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS " + ANOTACOES_LISTA
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL ); ";

        try{
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB","sucesso ao criar a tabela");
        }catch (Exception e){
            Log.i("INFO DB","Erro ao criar a tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + ANOTACOES_LISTA + ";";

        try{
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB","sucesso ao criar a tabela");
        }catch (Exception e){
            Log.i("INFO DB","Erro ao criar a tabela" + e.getMessage());
        }
    }
}

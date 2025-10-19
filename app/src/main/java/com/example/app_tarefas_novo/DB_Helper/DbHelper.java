package com.example.app_tarefas_novo.DB_Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION =1;
    public static String NOME_DB = "DB_TAREFAS";

    public static String TABELA_TAREFAS = "tarefas";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
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

        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS + ";";

        try{
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB","sucesso ao criar a tabela");
        }catch (Exception e){
            Log.i("INFO DB","Erro ao criar a tabela" + e.getMessage());
        }
    }



    //Seria uma outra logica!!!


    //São metodos que iremos usar para cadastrar fazer o "CRUD"!!
    public Boolean insertUserData(String nome){

        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", nome);
        Long result = DB.insert(NOME_DB,null, contentValues);
        if (result == -1){
            return  false;
        }else {
            return  true;
        }
    }

    public Boolean DeleteUserData(String nome) {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor findRecord = DB.rawQuery("SELECT * FROM DB_TAREFAS WHERE nome=?", new String[]{nome});

        if (findRecord.getCount() > 0) {
            int result = DB.delete(NOME_DB, nome, new String[]{nome});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor viewUserData() {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor findRecords = DB.rawQuery("SELECT * FROM  DB_TAREFAS",null);

        return findRecords;
    }

}

package com.example.app_tarefas_novo.DB_Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.app_tarefas_novo.model.Anotacao;
import com.example.app_tarefas_novo.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class AnotacaoDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public AnotacaoDAO(Context context) {
        DbHelper_Anotacoes db = new DbHelper_Anotacoes(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    //Aqui temos a parte da Anotação!!
    public boolean salvarAnotacoes(Anotacao anotacao){

        ContentValues cv = new ContentValues();
        cv.put("nome", anotacao.getAnotacao());

        try{
            escreve.insert(DbHelper_Anotacoes.NOME_DB_AN,null,cv);
            Log.e("INFO","Anotação salva com sucesso");
        }catch(Exception e){
            Log.e("INFO","Erro ao salvar Anotação" + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean atualizarAnotacoes(Anotacao anotacao){

        ContentValues cv = new ContentValues();
        cv.put("nome",anotacao.getAnotacao());

        try{
            String[] args = {anotacao.getId().toString()};
            escreve.update(DbHelper_Anotacoes.NOME_DB_AN,cv,"id=?",args);
            Log.e("INFO","anotacao salva com sucesso");
        }catch(Exception e){
            Log.e("INFO","Erro ao salvar anotacao" + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deletarAnotacoes(long id){

        try{
            String[] args = {String.valueOf(id)};
            escreve.delete(DbHelper_Anotacoes.NOME_DB_AN,"id=?",args);
            Log.e("INFO","anotacao removida com sucesso");
        }catch(Exception e){
            Log.e("INFO","Erro ao remover anotacao" + e.getMessage());
            return false;
        }


        return true;
    }

    public List<Anotacao> listarAnotacoes(){

        List<Anotacao> anotacaoList = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper_Anotacoes.NOME_DB_AN + ";";
        Cursor c = le.rawQuery(sql, null);

        if (c.moveToFirst()){

            do {
                Long id = c.getLong(c.getColumnIndex("id"));
                String nomeAnotacao = c.getString(c.getColumnIndex("nome"));

                anotacaoList.add(new Anotacao(id, nomeAnotacao));
            } while (c.moveToNext());
        }
        c.close();
        return anotacaoList;
    }

    public Anotacao retornarUltimo_Anotacao(){

        String sql = "SELECT * FROM " + DbHelper_Anotacoes.NOME_DB_AN + " ORDER BY id DESC; ";
        Cursor c = le.rawQuery(sql,null);

        if (c.moveToFirst()){
            do {
                Long id = c.getLong(c.getColumnIndex("id"));
                String nomeAnotacao = c.getString(c.getColumnIndex("nome"));

                return new Anotacao(id, nomeAnotacao);
            } while (c.moveToNext());
        }
        c.close();
        return null;
    }
}

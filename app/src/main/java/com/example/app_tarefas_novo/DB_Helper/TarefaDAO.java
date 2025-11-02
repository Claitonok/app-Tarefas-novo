package com.example.app_tarefas_novo.DB_Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.app_tarefas_novo.model.Tarefa;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements iTarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getNomeTarefa());

        try{
            escreve.insert(DbHelper.TABELA_TAREFAS,null,cv);
            Log.e("INFO","tarefa salva com sucesso");
        }catch(Exception e){
            Log.e("INFO","Erro ao salvar tarefa" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getNomeTarefa());

        try{
            String[] args = {tarefa.getId().toString()};
            escreve.update(DbHelper.TABELA_TAREFAS,cv,"id=?",args);
            Log.e("INFO","tarefa salva com sucesso");
        }catch(Exception e){
            Log.e("INFO","Erro ao salvar tarefa" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(long id) {
        try{
            String[] args = {String.valueOf(id)};
            escreve.delete(DbHelper.TABELA_TAREFAS,"id=?",args);
            Log.e("INFO","tarefa removida com sucesso");
        }catch(Exception e){
            Log.e("INFO","Erro ao remover tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + ";";
        Cursor c = le.rawQuery(sql, null);

        while(c.moveToNext()){

            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));

            tarefas.add(new Tarefa(id, nomeTarefa));
        }
        c.close();
        return tarefas;
    }

    public Tarefa retornarUltimo(){
        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ORDER BY id DESC" + ";";
        Cursor c = le.rawQuery(sql,null);

        if (c.moveToFirst()){
            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));
            c.close();
            return new Tarefa(id, nomeTarefa);
        }
        return null;
    }

}

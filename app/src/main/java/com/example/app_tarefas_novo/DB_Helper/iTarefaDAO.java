package com.example.app_tarefas_novo.DB_Helper;



import com.example.app_tarefas_novo.model.Tarefa;

import java.util.List;

public interface iTarefaDAO {

    public boolean salvar(Tarefa tarefa);
    public boolean atualizar(Tarefa tarefa);
    public boolean deletar(long id);

    public List<Tarefa> listar();

}

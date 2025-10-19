package com.example.app_tarefas_novo.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    public Tarefa() {}

    public Tarefa(Long id, String nomeTarefa) {
        this.Id = id;
        this.nomeTarefa = nomeTarefa;
    }

    private Long Id;

    private String nomeTarefa;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

}

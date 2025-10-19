package com.example.app_tarefas_novo.model;

import java.io.Serializable;

public class Anotacao implements Serializable {

    public Anotacao(Long id, String anotacao) {
        this.Id = id;
        this.Anotacao = anotacao;
    }

    public Anotacao() {}

    private String Anotacao;

    private  Long Id;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getAnotacao() {
        return Anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.Anotacao = anotacao;
    }


}

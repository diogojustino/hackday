package br.com.codtech.hackday.activity.model;

import java.io.Serializable;

public class Calendario implements Serializable {
    private Long id;
    private String dataInicio;
    private String dataFim;

    public Calendario(String dataInicio, String dataFim){
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Calendario(Long id, String dataInicio, String dataFim){
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public void setDataInicio(String dataInicio){
        this.dataInicio = dataInicio;
    }

    public String getDataInicio(){
        return dataInicio;
    }
    public void setDataFim(String dataFim){
        this.dataFim = dataFim;
    }

    public String getDataFim(){
        return dataFim;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }
}

package com.example.meusmedicos.models;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Dênnis on 7/30/2015.
 */
public class Sintoma implements Comparable<Sintoma> {
    private String titulo;
    private int duracaoDeDias;
    private Calendar dataQueComecou;
    private Especialidade especialidade;
    private String anotacao;

    public Sintoma(String titulo, Calendar date, int duracao, Especialidade especialidade, String anotacao) {
        this.setTitulo(titulo);
        this.setDataQueComecou(date);
        this.setDuracaoDeDias(duracao);
        this.setEspecialidade(especialidade);
        this.setAnotacao(anotacao);
    }

    public String getTitulo() {  return this.titulo;  }

    public void setTitulo(String titulo) {   this.titulo = titulo;  }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public int getDuracaoDeDias() {
        return this.duracaoDeDias;
    }

    public void setDuracaoDeDias(int duracaoDeDias) {  this.duracaoDeDias= duracaoDeDias;  }

    public Calendar getDataQueComecou() {
        return this.dataQueComecou;
    }

    public Calendar getDataQueTerminou(){
        int year = dataQueComecou.get(Calendar.YEAR);
        int month = dataQueComecou.get(Calendar.MONTH);
        int day = dataQueComecou.get(Calendar.DAY_OF_MONTH);

        return new GregorianCalendar(year, month, day + getDuracaoDeDias());
    }

    public void setDataQueComecou(Calendar date) {
        this.dataQueComecou = date;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    @Override
    public int compareTo(Sintoma another) {
        if (this.getDataQueComecou().getTimeInMillis() != another.getDataQueComecou().getTimeInMillis()) {
            return this.getDataQueComecou().compareTo(another.getDataQueComecou());
        } else if (!this.getEspecialidade().equals(another.getEspecialidade())) {
            return this.getEspecialidade().compareTo(another.getEspecialidade());
        } else if (this.getDuracaoDeDias() != another.getDuracaoDeDias()) {
            return (this.getDuracaoDeDias() < another.getDuracaoDeDias())? -1 : 1;
        } else {
            return this.getTitulo().compareTo(another.getTitulo());
        }

    }

}

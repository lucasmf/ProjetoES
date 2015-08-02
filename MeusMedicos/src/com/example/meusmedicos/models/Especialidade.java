package com.example.meusmedicos.models;

public class Especialidade implements Comparable<Especialidade>{
    private String especialidade;

    public Especialidade(String especialidade){
        setEspecialidade(especialidade);
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public int compareTo(Especialidade another) {
        return this.especialidade.compareTo(another.getEspecialidade());
    }

    @Override
    public String toString() {
        return especialidade;
    }
}

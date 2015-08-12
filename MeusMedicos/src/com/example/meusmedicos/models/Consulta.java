package com.example.meusmedicos.models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Consulta implements Comparable <Consulta> {
	private String medico;
	private Especialidade especialidade;
	private Calendar date;
	private String anotacao;
	private boolean lembrar;

	public Consulta(String medico, Especialidade  especialidade, Calendar date, boolean lembrar) {
		this.setMedico(medico);
		this.setEspecialidade(especialidade);
		this.setDate(date);
		this.setAnotacao("");
		this.setLembrar(lembrar);
	}

	public void setLembrar(boolean lembrar){
		this.lembrar = lembrar;
	}

	public boolean getLembrar(){
		return this.lembrar;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date2) {
		this.date = date2;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	@Override
	public int compareTo(Consulta another) {
		if (this.getDate().getTimeInMillis() != another.getDate().getTimeInMillis()) {
			return this.getDate().compareTo(another.getDate());
		}else if (!this.getEspecialidade().equals(another.getEspecialidade())){
			return this.getEspecialidade().compareTo(another.getEspecialidade());
		}else{
			return this.getMedico().compareTo(another.getMedico());
		}

	}
	
	@Override
	public String toString() {
		return medico + " - " + especialidade;

	}

	public void copy(Consulta consulta) {
		this.medico = consulta.medico;
		this.especialidade = consulta.especialidade;
		this.date = consulta.date;
		this.anotacao = consulta.anotacao;
		
	}
}

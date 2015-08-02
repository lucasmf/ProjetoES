package com.example.meusmedicos.models;

import java.util.GregorianCalendar;

public class Consulta implements Comparable <Consulta> {
	private String medico;
	private String especialidade;
	private GregorianCalendar date;
	private String anotacao;

	public Consulta(String medico, String especialidade, GregorianCalendar date) {
		this.setMedico(medico);
		this.setEspecialidade(especialidade);
		this.setDate(date);
		this.setAnotacao("");
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
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
}

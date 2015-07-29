package com.example.meusmedicos;

import java.util.GregorianCalendar;

public class Consulta {
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
}

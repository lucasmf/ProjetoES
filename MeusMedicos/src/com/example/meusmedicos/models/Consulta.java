package com.example.meusmedicos.models;

import java.util.Calendar;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anotacao == null) ? 0 : anotacao.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((especialidade == null) ? 0 : especialidade.hashCode());
		result = prime * result + (lembrar ? 1231 : 1237);
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		if (anotacao == null) {
			if (other.anotacao != null)
				return false;
		} else if (!anotacao.equals(other.anotacao))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (especialidade == null) {
			if (other.especialidade != null)
				return false;
		} else if (!especialidade.equals(other.especialidade))
			return false;
		if (lembrar != other.lembrar)
			return false;
		if (medico == null) {
			if (other.medico != null)
				return false;
		} else if (!medico.equals(other.medico))
			return false;
		return true;
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
		this.lembrar = consulta.lembrar;
		this.anotacao = consulta.anotacao;
		
	}
}

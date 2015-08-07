package com.example.meusmedicos.controllers;

import com.example.meusmedicos.Global;
import com.example.meusmedicos.models.Consulta;
import com.example.meusmedicos.models.Especialidade;
import com.example.meusmedicos.models.Sintoma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Controller {
	private static ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	private static ArrayList<Sintoma> sintomas = new ArrayList<Sintoma>();
	private static ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>(
			Arrays.asList(new Especialidade("Clinico Geral")));

	public static ArrayList<Consulta> getConsultas() {
		return consultas;
	}
	public static void addConsulta(Consulta consulta) {
		int insertionIndex = Collections.binarySearch(consultas, consulta);
		// Do not add if obj is already in the list
		if (insertionIndex < 0) {
			consultas.add((-insertionIndex - 1), consulta);
		}
	}

	public static ArrayList<Sintoma> getSintomas(){
		return sintomas;
	}
	public static void addSintoma(Sintoma sintoma){
		int insertionIndex = Collections.binarySearch(sintomas, sintoma);
		// Do not add if obj is already in the list
		if (insertionIndex < 0) {
			sintomas.add((-insertionIndex - 1), sintoma);
		}
	}
	public static void deletaConsulta(Consulta consulta) {
		consultas.remove(consulta);
	}

	public static void removeSintoma() {
		int deletionIndex = Collections.binarySearch(sintomas, Global.selectedSintoma);
		if (deletionIndex >= 0){
			sintomas.remove(deletionIndex);
		}
	}

	public static ArrayList<Especialidade> getEspecialidades(){
		return especialidades;
	}

	public static void adicionaEspecialidade(Especialidade especialidade){
		int insertionIndex = Collections.binarySearch(especialidades, especialidade);
		// Do not add if obj is already in the list
		if (insertionIndex < 0) {
			especialidades.add((-insertionIndex - 1), especialidade);
		}
	}

	public static void editSintoma(Sintoma sintoma) {
		Global.selectedSintoma.setTitulo(sintoma.getTitulo());
		Global.selectedSintoma.setDataQueComecou(sintoma.getDataQueComecou());
		Global.selectedSintoma.setEspecialidade(sintoma.getEspecialidade());
		Global.selectedSintoma.setDuracaoDeDias(sintoma.getDuracaoDeDias());
		Global.selectedSintoma.setAnotacao(sintoma.getAnotacao());
		Collections.sort(sintomas);
	}
	public static void editConsulta(Consulta consulta) {
		Global.selectedConsulta.copy(consulta);		
	}
}

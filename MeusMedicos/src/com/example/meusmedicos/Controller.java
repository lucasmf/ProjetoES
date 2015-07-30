package com.example.meusmedicos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Controller {
	private static ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	private static ArrayList<Sintoma> sintomas = new ArrayList<Sintoma>();

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
}

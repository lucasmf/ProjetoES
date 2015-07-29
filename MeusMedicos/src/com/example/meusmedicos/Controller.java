package com.example.meusmedicos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Controller {
	private static ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	public static ArrayList<Consulta> getConsultas() {
		Collections.sort(consultas);
		return consultas;
	}
	public static void addConsulta(Consulta consulta) {
		consultas.add(consulta);
	}
}

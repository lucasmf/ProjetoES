package com.example.meusmedicos;

import java.util.ArrayList;

public class Controller {
	private static ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	public static ArrayList<Consulta> getConsultas() {
		return consultas;
	}
	public static void addConsulta(Consulta consulta) {
		consultas.add(consulta);
	}
}

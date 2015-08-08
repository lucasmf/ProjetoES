package com.example.meusmedicos;

import com.example.meusmedicos.models.Consulta;
import com.example.meusmedicos.models.Sintoma;

import java.text.SimpleDateFormat;

public class Global {
    public static Consulta selectedConsulta = new Consulta("","",null);
    public static Sintoma selectedSintoma = new Sintoma("",null,0,"", "");
    public static final SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
}

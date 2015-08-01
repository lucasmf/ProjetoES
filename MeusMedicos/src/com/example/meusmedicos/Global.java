package com.example.meusmedicos;

import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class Global {
    public static Consulta selectedConsulta = new Consulta("","",null);
    public static Sintoma selectedSintoma = new Sintoma("",null,0,"", "");
    public static final SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
}

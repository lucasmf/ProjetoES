package com.example.meusmedicos;

import android.annotation.SuppressLint;
import com.example.meusmedicos.models.Consulta;
import com.example.meusmedicos.models.Sintoma;
import java.text.SimpleDateFormat;

@SuppressLint("SimpleDateFormat")
public class Global {
    public static Consulta selectedConsulta = new Consulta("",null,null, false);
    public static Sintoma selectedSintoma = new Sintoma("",null,0,null, "");
	public static final SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
}

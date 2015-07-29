package com.example.meusmedicos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Órion on 29/07/2015.
 */
public class AddAnnotationOnConsulta extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_annotation_on_consulta);
        ((EditText)findViewById(R.id.editText1)).setText(Global.selectedConsulta.getAnotacao());
    }

    public void limpar(View view){
        ((EditText)findViewById(R.id.editText1)).setText("");
    }

    public void salvar(View view){
        Global.selectedConsulta.setAnotacao(((EditText) findViewById(R.id.editText1)).getText().toString());
        Intent myIntent = new Intent(getApplicationContext(), ShowConsultaDetalhada.class);
        startActivityForResult(myIntent, 0);
    }

}
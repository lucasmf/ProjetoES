package com.example.meusmedicos.views.consulta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.meusmedicos.Global;
import com.example.meusmedicos.R;

/**
 * Class that add anotacao field on Consulta.
 */
public class AddAnnotationOnConsulta extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_annotation_on_consulta);
        ((EditText)findViewById(R.id.editText1)).setText(Global.selectedConsulta.getAnotacao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
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
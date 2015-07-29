package com.example.meusmedicos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Órion on 29/07/2015.
 */
public class ShowConsultaDetalhada extends Activity {
    private TextView lbSetNameDoctor;
    private TextView lbSetAnnotation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_consulta_detalhada);
        lbSetNameDoctor = (TextView) findViewById(R.id.textView2);
        lbSetAnnotation = (TextView) findViewById(R.id.textView3);
        lbSetNameDoctor.setText(Global.selectedConsulta.getMedico());
        lbSetAnnotation.setText(Global.selectedConsulta.getAnotacao());
    }


    public void criaAnotacao(View view){
        Intent intent = new Intent(this, AddAnnotationOnConsulta.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
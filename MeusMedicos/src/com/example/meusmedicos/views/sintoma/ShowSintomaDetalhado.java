package com.example.meusmedicos.views.sintoma;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meusmedicos.controllers.Controller;
import com.example.meusmedicos.Global;
import com.example.meusmedicos.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dênnis on 7/31/2015.
 */
public class ShowSintomaDetalhado extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_sintoma_detalhado);
        setAllTextViews();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setAllTextViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getMenuInflater().inflate(R.menu.edit_and_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            Intent intent = new Intent(this, EditSintomasActivity.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_delete){
            deleteSintoma();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void deleteSintoma() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        Controller.removeSintoma();
                        Toast.makeText(getApplicationContext(), R.string.deletion_sintoma_sucess,
                                Toast.LENGTH_LONG).show();
                        onBackPressed();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.sure_to_delete_sintoma))
                .setPositiveButton(getString(R.string.yes), dialogClickListener)
                .setNegativeButton(getString(R.string.no), dialogClickListener).show();
    }

    private void setAllTextViews() {
        ((TextView)findViewById(R.id.textView30)).setText(getTextPartOne());
        ((TextView)findViewById(R.id.textView31)).setText(getTextPartTwo());
    }

    private String getTextPartTwo() {
        String anotacao = Global.selectedSintoma.getAnotacao();
        if(anotacao.equals("")) {
            return "";
        }else{
            return getString(R.string.sintoma_visualisation_part07) + anotacao;
        }
    }

    public String getTextPartOne(){
        String text = "";
        text += getString(R.string.sintoma_visualisation_part01) + getTitleOfSintoma();
        text += getString(R.string.sintoma_visualisation_part02) + getDataOfSintoma();
        text += getString(R.string.sintoma_visualisation_part03) + getDuracaoOfSintoma()
              + getString(R.string.sintoma_visualisation_part04);
        text += getString(R.string.sintoma_visualisation_part05) + getEspecialistaOfSintoma()
              + getString(R.string.sintoma_visualisation_part06);
        return text;
    }

    private String getEspecialistaOfSintoma() {
        String especialista = Global.selectedSintoma.getEspecialidade().toString();
        return especialista;
    }

    private String getDuracaoOfSintoma() {
        int duracao = Global.selectedSintoma.getDuracaoDeDias();
        return "" + duracao;
    }

    private String getDataOfSintoma() {
        Calendar date = Global.selectedSintoma.getDataQueComecou();
        return date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR);
    }

    private String getTitleOfSintoma() {
        return Global.selectedSintoma.getTitulo();
    }
}
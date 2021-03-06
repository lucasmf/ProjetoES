package com.example.meusmedicos.views.sintoma;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meusmedicos.DatePickerFragment;
import com.example.meusmedicos.Global;
import com.example.meusmedicos.R;
import com.example.meusmedicos.controllers.Controller;
import com.example.meusmedicos.models.Especialidade;
import com.example.meusmedicos.models.Sintoma;
import com.example.meusmedicos.views.AdicionadorDeEspecialidade;
import com.example.meusmedicos.views.especialidade.DialogEspecialidade;

/**
 * Created by D�nnis on 8/1/2015.
 */
public class EditSintomasActivity extends Activity implements AdicionadorDeEspecialidade {
    private Calendar begginingDate = Global.selectedSintoma.getDataQueComecou();
    private Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_sintomas_activity);

        addItemsOnSpinner();
        setAllEditTexts();
    }

    private void setAllEditTexts() {
        ((EditText)findViewById(R.id.editText10)).setText(getActualTitleFromSintoma());
        ((EditText)findViewById(R.id.editText11)).setText(getActualDataOfSintoma());
        ((EditText)findViewById(R.id.editText12)).setText(getActualNumberOfDaysFromSintoma());
        ((EditText)findViewById(R.id.editText13)).setText(getActualAnotacaoFromSintoma());
    }

    private String getActualAnotacaoFromSintoma() {
        return Global.selectedSintoma.getAnotacao();
    }

    private String getActualNumberOfDaysFromSintoma() {
        return "" + Global.selectedSintoma.getDuracaoDeDias();
    }

    private String getActualDataOfSintoma() {
        Date date = Global.selectedSintoma.getDataQueComecou().getTime();
        String dataStr = Global.formatterDate.format(date);
        return dataStr;
    }

    private String getActualTitleFromSintoma() {
        return Global.selectedSintoma.getTitulo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void callDatePickerDialog(View view){
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment)newFragment).show(getFragmentManager(), "datePicker", ((EditText) findViewById(R.id.editText11)), begginingDate);
    }

    public void limpar(View view){
        ((EditText)findViewById(R.id.editText10)).setText("");
        ((EditText)findViewById(R.id.editText11)).setText("");
        ((EditText)findViewById(R.id.editText12)).setText("");
        ((EditText)findViewById(R.id.editText13)).setText("");
    }

    public void salvar(View view){
        if(allRequiredFieldsWereFilled()) {
            Controller.editSintoma(createSintoma(getTitleFromSintoma(), getNumberOfDaysFromSintoma(),
                    getEspecialidadeFromSintoma(), getAnotacaoFromSintoma()));
            informSintomaEdition();
        }
    }

    private boolean allRequiredFieldsWereFilled() {
        if (getTitleFromSintoma().equals("")){
            Toast.makeText(getApplicationContext(), R.string.toast_message_title_missing,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if(getNumberOfDaysFromSintoma() == -1){
            Toast.makeText(getApplicationContext(), R.string.toast_message_duration_missing,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void informSintomaEdition() {
        Log.i("Sintoma", "Sintoma modificado, " + Controller.getSintomas().size());
        Toast.makeText(getApplicationContext(), "Sintoma modificado.",
                Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public String getTitleFromSintoma(){
        final EditText title = (EditText)findViewById(R.id.editText10);
        return title.getText().toString();
    }

    public int getNumberOfDaysFromSintoma(){
        final EditText numberDaysEditText = (EditText)findViewById(R.id.editText12);
        Editable textEditable = numberDaysEditText.getText();
        String textString = textEditable.toString();
        if(textString.equals("")){
            return -1;
        }
        return Integer.parseInt(textString);
    }

    public String getEspecialidadeFromSintoma(){
        final Spinner especialidadeSpinner = (Spinner) findViewById(R.id.especialidadeForm2);
        return especialidadeSpinner.getSelectedItem().toString();
    }

    public Sintoma createSintoma(String title, int number, String especialidade, String anotacao){
        return new Sintoma(title, this.begginingDate, number, new Especialidade(especialidade), anotacao);
    }

/*    private String getDataOfSintoma() {
        Date date = Global.selectedSintoma.getDataQueComecou().getTime();
        String dataStr = Global.formatterDate.format(date);
        return dataStr;
    }*/

    public String getAnotacaoFromSintoma() {
        final EditText note = (EditText)findViewById(R.id.editText13);
        return note.getText().toString();
    }

    public void addItemsOnSpinner() {

        spinner = (Spinner) findViewById(R.id.especialidadeForm2);
        List<String> list = new ArrayList<String>();
        for (Especialidade e: Controller.getEspecialidades()){
            list.add(e.toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void adicionaEspecialidade(View view){
        FragmentManager manager = getFragmentManager();
        DialogEspecialidade dialogEspecialidade = new DialogEspecialidade();
        dialogEspecialidade.show(manager, "DialogEspecialidade");
    }
}
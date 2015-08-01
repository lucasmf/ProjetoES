package com.example.meusmedicos;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Screen where Sintoma is added on System.
 */
public class AddSintomasActivity extends Activity {

    private Calendar begginingDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_sintomas_activity);
        begginingDate = Calendar.getInstance();
        int year = begginingDate.get(Calendar.YEAR);
        int month = begginingDate.get(Calendar.MONTH);
        int day = begginingDate.get(Calendar.DAY_OF_MONTH);

        String currentDate = day + "/" + month + "/" + year;
        ((EditText)findViewById(R.id.editText11)).setText(currentDate);
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
            Controller.addSintoma(createSintoma(getTitleFromSintoma(), getNumberOfDaysFromSintoma(),
                    getEspecialidadeFromSintoma(), getAnotacaoFromSintoma()));
            informSintomaCreation();
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

    private void informSintomaCreation() {
        Log.i("Sintoma", "Sintoma adicionado, " + Controller.getSintomas().size());
        Toast.makeText(getApplicationContext(), "Sintoma criado.",
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
        return new Sintoma(title, this.begginingDate, number, especialidade, anotacao);
    }

    public String getAnotacaoFromSintoma() {
        final EditText note = (EditText)findViewById(R.id.editText13);
        return note.getText().toString();
    }
}
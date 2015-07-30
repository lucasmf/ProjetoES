package com.example.meusmedicos;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddConsultasActivity extends Activity {
	
	public void createConsulta(View view) throws InterruptedException {
		
		final EditText nameField = (EditText) findViewById(R.id.nodeForm);
		String nomeMedico = nameField.getText().toString();
		
		final Spinner especialidadeSpinner = (Spinner) findViewById(R.id.especialidadeForm);
		String especialidade = especialidadeSpinner.getSelectedItem()
				.toString();
	
		final TimePicker time = (TimePicker) findViewById(R.id.timePickerForm);
		final DatePicker date = (DatePicker) findViewById(R.id.datePickerForm);
		GregorianCalendar calendar = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDayOfMonth(),
				time.getCurrentHour(), time.getCurrentMinute());
		
		Consulta consulta = new Consulta(nomeMedico, especialidade, calendar);
		Controller.addConsulta(consulta);
		Log.i("Consulta", "Consulta adicionada, "+Controller.getConsultas().size());
		finish();
		startActivity(getIntent());
		Toast.makeText(getApplicationContext(), "Consulta criada.",
				Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultas);
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

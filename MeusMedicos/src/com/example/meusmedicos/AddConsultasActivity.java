package com.example.meusmedicos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class AddConsultasActivity extends Activity {
	
	public void createConsulta(View view) {
		
		Toast.makeText(getApplicationContext(), "Metodo chamado",
				Toast.LENGTH_LONG).show();
		
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
		

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultas);
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

}

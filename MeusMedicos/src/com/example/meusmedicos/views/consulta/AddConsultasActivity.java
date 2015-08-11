package com.example.meusmedicos.views.consulta;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.meusmedicos.controllers.Controller;
import com.example.meusmedicos.DatePickerFragment;
import com.example.meusmedicos.R;
import com.example.meusmedicos.TimePickerFragment;
import com.example.meusmedicos.models.Consulta;
import com.example.meusmedicos.models.Especialidade;
import com.example.meusmedicos.views.AdicionadorDeEspecialidade;
import com.example.meusmedicos.views.especialidade.DialogEspecialidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AddConsultasActivity extends Activity implements AdicionadorDeEspecialidade {

	private Spinner spinner;
	private GregorianCalendar calendar = new GregorianCalendar();
	
	public void createConsulta(View view) throws InterruptedException {
		
		final EditText nameField = (EditText) findViewById(R.id.nodeForm);
		String nomeMedico = nameField.getText().toString();
		
		final Spinner especialidadeSpinner = (Spinner) findViewById(R.id.especialidadeForm1);
		String especialidade = especialidadeSpinner.getSelectedItem()
				.toString();

		final Switch switchLembrar = (Switch) findViewById(R.id.switch1);
		boolean lembrar = switchLembrar.isChecked();
		
		Consulta consulta = new Consulta(nomeMedico, new Especialidade(especialidade), calendar, lembrar);
		Controller.addConsulta(consulta);



		Log.i("Consulta", "Consulta adicionada, "+Controller.getConsultas().size());
		finish();
		startActivity(getIntent());
		Toast.makeText(getApplicationContext(), "Consulta criada.",
				Toast.LENGTH_LONG).show();
	}


    public void callDatePickerDialog(View view){
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment)newFragment).show(getFragmentManager(), "datePicker", ((EditText) findViewById(R.id.datePickerForm1)), calendar);
    }
    
    public void callTimePickerDialog(View view) {
    	 DialogFragment newFragment = new TimePickerFragment();
         ((TimePickerFragment)newFragment).show(getFragmentManager(), "timePicker", ((EditText) findViewById(R.id.timePickerForm1)), calendar);
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_consulta);
		addItemsOnSpinner();
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

	public void adicionaEspecialidade(View view){
		FragmentManager manager = getFragmentManager();
		DialogEspecialidade dialogEspecialidade = new DialogEspecialidade();
		dialogEspecialidade.show(manager, "DialogEspecialidade");
	}

	public void addItemsOnSpinner() {

		spinner = (Spinner) findViewById(R.id.especialidadeForm1);
		List<String> list = new ArrayList<String>();
		for (Especialidade e: Controller.getEspecialidades()){
			list.add(e.toString());
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
	}

}

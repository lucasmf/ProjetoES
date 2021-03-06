package com.example.meusmedicos.views.consulta;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.meusmedicos.DatePickerFragment;
import com.example.meusmedicos.Global;
import com.example.meusmedicos.R;
import com.example.meusmedicos.TimePickerFragment;
import com.example.meusmedicos.controllers.Controller;
import com.example.meusmedicos.models.Consulta;
import com.example.meusmedicos.models.Especialidade;
import com.example.meusmedicos.views.especialidade.DialogEspecialidade;

public class EditConsultaActivity extends Activity {
	private Spinner spinner;
	private GregorianCalendar calendar = new GregorianCalendar();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_consulta);
		setAllEditTexts();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_consulta, menu);
		return true;
	}

	private void setAllEditTexts() {
		Consulta consulta = Global.selectedConsulta;
		((EditText) findViewById(R.id.nodeForm)).setText(consulta.getMedico());
		addItemsOnSpinner();
		String dateStr = Global.formatterDate.format(consulta.getDate()
				.getTime());
		((EditText) findViewById(R.id.datePickerForm1)).setText(dateStr);
		dateStr = Global.formatterTime.format(consulta.getDate().getTime());
		((EditText) findViewById(R.id.timePickerForm1)).setText(dateStr);
	}

	public void addItemsOnSpinner() {
		spinner = (Spinner) findViewById(R.id.especialidadeForm1);
		List<String> list = new ArrayList<String>();
		for (Especialidade e : Controller.getEspecialidades()) {
			list.add(e.toString());
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
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
	
    public void callDatePickerDialog(View view){
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment)newFragment).show(getFragmentManager(), "datePicker", ((EditText) findViewById(R.id.datePickerForm1)), calendar);
    }
    
    public void callTimePickerDialog(View view) {
    	 DialogFragment newFragment = new TimePickerFragment();
         ((TimePickerFragment)newFragment).show(getFragmentManager(), "timePicker", ((EditText) findViewById(R.id.timePickerForm1)), calendar);
    }
    
	public void createConsulta(View view) throws InterruptedException {

		final EditText nameField = (EditText) findViewById(R.id.nodeForm);
		String nomeMedico = nameField.getText().toString();

		final Spinner especialidadeSpinner = (Spinner) findViewById(R.id.especialidadeForm1);
		String especialidade = especialidadeSpinner.getSelectedItem()
				.toString();

		final Switch switchLembrar = (Switch) findViewById(R.id.switch1);
		boolean lembrar = switchLembrar.isChecked();

		Controller.editConsulta(new Consulta(nomeMedico, new Especialidade(especialidade), calendar, lembrar));
		
		
		finish();
		startActivity(getIntent());
		Toast.makeText(getApplicationContext(), "Consulta Modificada.",
				Toast.LENGTH_LONG).show();
	}
	
	public void adicionaEspecialidade(View view){
		FragmentManager manager = getFragmentManager();
		DialogEspecialidade dialogEspecialidade = new DialogEspecialidade();
		dialogEspecialidade.show(manager, "DialogEspecialidade");
	}
	
}

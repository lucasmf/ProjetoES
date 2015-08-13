package com.example.meusmedicos.views.sintoma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.meusmedicos.DatePickerFragment;
import com.example.meusmedicos.Global;
import com.example.meusmedicos.R;
import com.example.meusmedicos.controllers.Controller;
import com.example.meusmedicos.models.Especialidade;
import com.example.meusmedicos.models.Sintoma;

/**
 * Created by Órion on 29/07/2015.
 */
public class ShowSintomas extends Activity {
    private ListView lv;
    private Spinner spinner;
    private Calendar begginingDate, endingDate;
    private EditText begginingDateET, endingDateET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_sintomas);
        lv = (ListView) findViewById(R.id.listView2);
        initializeCalendar();
        addItemsOnSpinner();
        loadListOfSintomas();
        spinnerOnChange();
    }

    private void initializeCalendar(){
        begginingDateET = (EditText) findViewById(R.id.editText16);
        endingDateET = (EditText) findViewById(R.id.editText17);

        begginingDate = Calendar.getInstance();
        begginingDate.set(Calendar.YEAR, 2015);
        begginingDate.set(Calendar.MONTH, 1);
        begginingDate.set(Calendar.DAY_OF_MONTH, 1);

        begginingDateET.setText("01/01/2015");

        endingDate = Calendar.getInstance();
        int year = endingDate.get(Calendar.YEAR);
        int month = endingDate.get(Calendar.MONTH) + 1;
        int day = endingDate.get(Calendar.DAY_OF_MONTH);

        endingDate.set(Calendar.MONTH, month);
        String currentDate = day + "/" + month + "/" + year;
        endingDateET.setText(currentDate);

        begginingDateET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (begginingDate.compareTo(endingDate) > 0) {
                    endingDate.set(Calendar.YEAR, begginingDate.get(Calendar.YEAR));
                    endingDate.set(Calendar.MONTH, begginingDate.get(Calendar.MONTH));
                    endingDate.set(Calendar.DAY_OF_MONTH, begginingDate.get(Calendar.DAY_OF_MONTH));

                    endingDateET.setText(endingDate.get(Calendar.DAY_OF_MONTH) + "/"
                            + endingDate.get(Calendar.MONTH) + "/" + endingDate.get(Calendar.YEAR));
                }
                loadListOfSintomas();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        endingDateET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadListOfSintomas();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void spinnerOnChange(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                loadListOfSintomas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                loadListOfSintomas();
            }

        });
    }

    public void addItemsOnSpinner() {

        spinner = (Spinner) findViewById(R.id.dropdown);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList("Todas Especialidades"));
        for (Especialidade e: Controller.getEspecialidades()){
            list.add(e.toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void openDetalhes(int id) {
        Global.selectedSintoma = Controller.getSintomas().get(id);
        Intent intent = new Intent(this, ShowSintomaDetalhado.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_plus) {
            Intent intent = new Intent(this, AddSintomasActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        lv.setAdapter(null);
        addItemsOnSpinner();
        loadListOfSintomas();
        spinnerOnChange();
    }

    private String getSelectedEspecialidade(){
        return spinner.getSelectedItem().toString();
    }

    private boolean sintomaFilter(Sintoma sintoma){
        Calendar date = sintoma.getDataQueComecou();
        Log.v("Data sintoma começou:", date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR) + "    " + date.getTimeInMillis());
        date = sintoma.getDataQueTerminou();
        Log.v("Data sintoma terminou:", date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR)+ "    " + date.getTimeInMillis());
        date = begginingDate;
        Log.v("Data filtro começa:", date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR)+ "    " + date.getTimeInMillis());
        date = endingDate;
        Log.v("Data filtro termina:", date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR)+ "    " + date.getTimeInMillis());

        return (sameDay(sintoma.getDataQueTerminou(), begginingDate) || sameDay(sintoma.getDataQueComecou(), endingDate) ||
                (!(sintoma.getDataQueComecou().compareTo(begginingDate) == -1 && sintoma.getDataQueTerminou().compareTo(begginingDate) == -1) &&
                !(sintoma.getDataQueComecou().compareTo(endingDate) == 1 && sintoma.getDataQueTerminou().compareTo(endingDate) == 1))) &&
                (getSelectedEspecialidade().equals("Todas Especialidades") ||
                sintoma.getEspecialidade().toString().equals(getSelectedEspecialidade()));
    }

    private boolean sameDay(Calendar c1, Calendar c2){
        return c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
    }

    private void loadListOfSintomas() {
        ArrayList<Sintoma> sintomas = Controller.getSintomas();
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        for (Sintoma item : sintomas) {
            if (sintomaFilter(item)) {
                Map<String, String> datum = new HashMap<String, String>(2);
                datum.put("campo1", item.getTitulo());     //Item
                Calendar date = item.getDataQueComecou();
                datum.put("campo2", date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR));    //subItem
                data.add(datum);
            }
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2, new String[]{"campo1", "campo2"},
                new int[]{android.R.id.text1, android.R.id.text2});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetalhes((int) id);
            }
        });
    }

    public void callDatePickerDialog1(View view){
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment)newFragment).show(getFragmentManager(), "datePicker", begginingDateET, begginingDate);
    }

    public void callDatePickerDialog2(View view){
        DialogFragment newFragment = new DatePickerFragment();
        Calendar date = new GregorianCalendar(begginingDate.get(Calendar.YEAR),begginingDate.get(Calendar.MONTH) - 1,begginingDate.get(Calendar.DAY_OF_MONTH));
        DatePickerFragment datePickerFragment = (DatePickerFragment) newFragment;
        datePickerFragment.setMinimumDate(date.getTimeInMillis());
        datePickerFragment.show(getFragmentManager(), "datePicker", endingDateET, endingDate);
    }
}
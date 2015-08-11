package com.example.meusmedicos.views.sintoma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.meusmedicos.controllers.Controller;
import com.example.meusmedicos.Global;
import com.example.meusmedicos.R;
import com.example.meusmedicos.models.Especialidade;
import com.example.meusmedicos.models.Sintoma;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Órion on 29/07/2015.
 */
public class ShowSintomas extends Activity {
    private ListView lv;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_sintomas);
        lv = (ListView) findViewById(R.id.listView2);
        addItemsOnSpinner();
        loadListOfSintomas();
        spinnerOnChange();
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

    private void loadListOfSintomas() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Sintoma> sintomas = Controller.getSintomas();
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        for (Sintoma item : sintomas) {
            if (getSelectedEspecialidade().equals("Todas Especialidades") ||
                    item.getEspecialidade().toString().equals(getSelectedEspecialidade())) {
                Map<String, String> datum = new HashMap<String, String>(2);
                datum.put("campo1", item.getTitulo());     //Item
                datum.put("campo2", formatter.format(item.getDataQueComecou().getTime()));    //subItem
                data.add(datum);
            }
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data,android.R.layout.simple_list_item_2,new String[] {"campo1", "campo2"},
                new int[] {android.R.id.text1, android.R.id.text2});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetalhes((int) id);
            }
        });
    }
}
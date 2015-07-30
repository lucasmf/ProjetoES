package com.example.meusmedicos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ShowConsultas extends Activity {
	
	private ListView lv;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_consultas);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   hh:mm");

        lv = (ListView) findViewById(R.id.listView1);
        // Instanciating an array list (you don't need to do this, 
        // you already have yours).
        ArrayList<Consulta> s = Controller.getConsultas();
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        for (Consulta item : s) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("campo1", item.toString());     //Item
            datum.put("campo2", formatter.format(item.getDate().getTime()));    //subItem
            data.add(datum);
        }
        // This is the array adapter, it takes the context of the activity as a 
        // first parameter, the type of list view as a second parameter and your 
        // array as a third parameter.
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

    private void openDetalhes(int id) {
        Global.selectedConsulta = Controller.getConsultas().get(id);
        Intent intent = new Intent(this, ShowConsultaDetalhada.class);
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
            Intent intent = new Intent(this, AddConsultasActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.meusmedicos;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class ShowConsultas extends Activity {
	
	private ListView lv;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_consultas);
        lv = (ListView) findViewById(R.id.listView1);
        // Instanciating an array list (you don't need to do this, 
        // you already have yours).
        List<String> your_array_list = new ArrayList<String>();
        List<Consulta> consultas = Controller.getConsultas();
        for(Consulta c:consultas) {
        	your_array_list.add(c.getMedico());
        }
        // This is the array adapter, it takes the context of the activity as a 
        // first parameter, the type of list view as a second parameter and your 
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                your_array_list );
        lv.setAdapter(arrayAdapter); 
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

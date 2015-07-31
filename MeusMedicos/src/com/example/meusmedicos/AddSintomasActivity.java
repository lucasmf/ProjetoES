package com.example.meusmedicos;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Screen where Sintoma is added on System.
 */
public class AddSintomasActivity extends Activity {

    private Calendar begginingDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sintomas_activity);
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

    public void callDatePickerDialog(View view){
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment)newFragment).show(getFragmentManager(), "datePicker", ((EditText) findViewById(R.id.editText11)), begginingDate);
    }

}
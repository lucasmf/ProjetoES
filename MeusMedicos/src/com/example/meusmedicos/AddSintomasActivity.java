package com.example.meusmedicos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * Screen where Sintoma is added on System.
 */
public class AddSintomasActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sintomas_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }
}
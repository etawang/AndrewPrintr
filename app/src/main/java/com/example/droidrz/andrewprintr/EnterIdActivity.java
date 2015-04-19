package com.example.droidrz.andrewprintr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterIdActivity extends ActionBarActivity implements View.OnClickListener {
    Button idButton;
    EditText idEditText;
    CharSequence editTextHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // String andrewId = prefs.getString("andrewId", "");
        setContentView(R.layout.activity_enter_id);

        idButton = (Button) findViewById(R.id.enterid_button);
        idButton.setOnClickListener(this);

        idEditText = (EditText) findViewById(R.id.enterid_edittext);
        editTextHint = idEditText.getHint();
        System.out.println(editTextHint.toString());
        /* idEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (idEditText.getHint().toString().equals("")) {
                    idEditText.setHint(editTextHint);
                } else {
                    idEditText.setHint("");
                }
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // Save andrewID in local storage
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("andrewId", idEditText.getText().toString());

        editor.commit();
        System.out.println(prefs.getString("andrewId", "NO STRING!"));

        Intent intent = new Intent(this, DisplayIdActivity.class);
        startActivity(intent);
    }
}

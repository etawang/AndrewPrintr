package com.example.droidrz.andrewprintr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

public class DisplayIdActivity extends ActionBarActivity implements View.OnClickListener {
    TextView idTextView;
    Button changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null) {
            Intent mailClient = new Intent(Intent.ACTION_VIEW);

            // TODO: check that user actually has has gmail installed
            mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
            mailClient.setType("plain/text");
            mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            mailClient.putExtra(Intent.EXTRA_EMAIL, new String[]{"print@scottylabs.org"});
            mailClient.putExtra(Intent.EXTRA_SUBJECT, "PRINT");
            mailClient.putExtra(Intent.EXTRA_STREAM, data);
            startActivity(mailClient);
        }
        setContentView(R.layout.activity_display_id);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        idTextView = (TextView) findViewById(R.id.displayid_textview);
        idTextView.setText("Your andrewId is " + prefs.getString("andrewId", ""));

        changeButton = (Button) findViewById(R.id.changeid_button);
        changeButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_id, menu);
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
        Intent intent = new Intent(this, EnterIdActivity.class);
        startActivity(intent);
    }
}

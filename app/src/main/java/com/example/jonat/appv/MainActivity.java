package com.example.jonat.appv;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    SharedPreferences prefs;
    SharedPreferences.Editor edits;
    TextView txtPersist;
    CheckBox checkpersist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPersist = (TextView) findViewById(R.id.txtPersist);
        checkpersist = (CheckBox) findViewById(R.id.chkpersist);
        prefs = getSharedPreferences("view", 0);
        edits = prefs.edit();
        populateValues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_persist, menu);
        return true;
    }

    public void populateValues() {
        String persistedText = prefs.getString("txtVal", "None Stored Yet");
        boolean isChecked = prefs.getBoolean("chkState", false);
        txtPersist.setText(persistedText);
        checkpersist.setChecked(isChecked);
    }

    public void persistValues(View v){
        edits.putString("txtVal", txtPersist.getText().toString());
        edits.putBoolean("chkState", checkpersist.isChecked());
        edits.commit();
        Toast.makeText(this, "Persisted", Toast.LENGTH_SHORT).show();
    }
}

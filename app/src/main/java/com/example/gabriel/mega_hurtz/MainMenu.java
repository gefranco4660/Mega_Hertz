package com.example.gabriel.mega_hurtz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainMenu extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button browse = (Button) findViewById(R.id.Browse);
        browse.setOnClickListener(this);

        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        Button login = (Button) findViewById(R.id.sign_in);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.Browse){
            Intent browseIntent = new Intent (this, Browse_Categories.class);
            startActivity(browseIntent);
        }
        if (v.getId() == R.id.register){
            Intent registerIntent = new Intent(this, Account_Information.class);
            startActivity(registerIntent);
        }
        if (v.getId() == R.id.sign_in){
            Intent loginIntent = new Intent(this, Login.class);
            startActivity(loginIntent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}

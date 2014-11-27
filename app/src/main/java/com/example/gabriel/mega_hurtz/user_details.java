package com.example.gabriel.mega_hurtz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class user_details extends Activity {

    EditText txtfirstname;
    EditText txtlastname;
    EditText txtusername;
    EditText txtdob;
    EditText txtphone;
    EditText txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        //Devin Byrne: I passed a bundle from Login.java
        //It held a userProfile object
        Bundle bundle = getIntent().getExtras();
        User_Profile userprofile = (User_Profile) bundle.get("user");

        //Take xml edit text
        txtfirstname = (EditText) findViewById(R.id.editFirstname);
        txtlastname = (EditText) findViewById(R.id.editLastname);
        txtusername = (EditText) findViewById(R.id.editUsername);
        txtdob = (EditText) findViewById(R.id.editDOB);
        txtphone = (EditText) findViewById(R.id.editPhone);
        txtemail = (EditText) findViewById(R.id.editEmail);

        //Put in user info from userProfile object
        txtfirstname.setText(userprofile.getFirstname());
        txtlastname.setText(userprofile.getLastname());
        txtusername.setText(userprofile.getUsername());
        txtdob.setText(userprofile.getDob());
        txtphone.setText(userprofile.getPhone());
        txtemail.setText(userprofile.getEmail());

        Button submitAccount = (Button) findViewById(R.id.user_browse);
        submitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browseIntent = new Intent(user_details.this, Browse_Categories.class);
                startActivity(browseIntent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_details, menu);

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
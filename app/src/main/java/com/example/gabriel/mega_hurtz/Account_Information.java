package com.example.gabriel.mega_hurtz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


public class Account_Information extends Activity {

    // Progress Dialog
    private ProgressDialog progressDialog;

    JSONParser jsonParser = new JSONParser();
    EditText newFirstName;
    EditText newLastName;
    EditText newDateOfBirth;
    EditText newEmail;
    EditText newUsername;
    EditText newPassword;
    EditText newPhone;

    //url to create new product
    private static String urlCreateUser = "http://www.westbayluxury.com/adduser.php";

    //JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__information);

        newFirstName = (EditText) findViewById(R.id.firstName);
        newLastName = (EditText) findViewById(R.id.lastName);
        newDateOfBirth = (EditText) findViewById(R.id.dateOfBirth);
        newEmail = (EditText) findViewById(R.id.email);
        newUsername = (EditText) findViewById(R.id.username);
        newPassword = (EditText) findViewById(R.id.password);
        newPhone = (EditText) findViewById(R.id.phone);

        Button submitAccount = (Button) findViewById(R.id.submitAccount);
        submitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CreateUser().execute();
            }
        });
    }
    /*
    Background Async Task to create a new user account
     */
    class CreateUser extends AsyncTask<String, String, String>{
        /*
        Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(Account_Information.this);
            progressDialog.setMessage("Creating Account..");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        /*
        Creating user Account! yay
         */
        protected String doInBackground(String... args){
            String username = newUsername.getText().toString();
            String password = newPassword.getText().toString();
            String first_name = newFirstName.getText().toString();
            String last_name = newLastName.getText().toString();
            String email = newEmail.getText().toString();
            String phone = newPhone.getText().toString();
            String birth_date = newDateOfBirth.getText().toString();

            //Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("firstName", first_name));
            params.add(new BasicNameValuePair("lastName", last_name));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("phone", phone));
            params.add(new BasicNameValuePair("birthdate", birth_date));

            //getting JSON object
            JSONObject json = jsonParser.makeHttpRequest(urlCreateUser, "POST", params);

            //check log cat for response
            Log.d("Create User", json.toString());

            //check for success tag
            try{
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1){
                    //successfully created new user
                    Intent i = new Intent(getApplicationContext(), Account_Information.class);
                    startActivity(i);

                    //closing this screen
                    finish();
                } else {
                    //failed to create user
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        /*
        After completing background task Dismiss the progress dialog
         */
        protected void onPostExecute(String file_url){
            //dismiss the dialog once done
            progressDialog.dismiss();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account__information, menu);
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

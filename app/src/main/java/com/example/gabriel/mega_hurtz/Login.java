package com.example.gabriel.mega_hurtz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carl on 11/22/2014.
 */
public class Login extends Activity implements View.OnClickListener {


    EditText user, pass;
    EditText txtfirstname, txtlastname, txtdob, txtemail, txtusername, txtphone;

    //progress Dialog
    private ProgressDialog progressDialog;

    //JSON parser class
    JSONParser jsonParser = new JSONParser();

    //url to login.php
    private static final String urlLogin = "http://www.westbayluxury.com/login.php";

    //JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //Edit Text variables
        user = (EditText) findViewById(R.id.loginUsername);
        pass = (EditText) findViewById(R.id.loginPassword);

        //Button for register and intent
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        //Button for login and intent
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                new GetUserDetails().execute();
                break;
            case R.id.register:
                Intent registerIntent = new Intent(Login.this, Account_Information.class);
                startActivity(registerIntent);
                break;
            default:
                break;
        }
    }
    /*
    Background Async Task to Get complete User Details
    */
    class GetUserDetails extends AsyncTask<String, String, String>{
        /*
        Before starting background thread show progress dialog
         */
        boolean failure = false;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Attempting Login...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
        /*
        Getting user details in background thread
         */
        protected String doInBackground(String... args){

                    //Check for success tag
                    int success;
                    String username = user.getText().toString();
                    String password = pass.getText().toString();
                    try{
                        //Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("username", username));
                        params.add(new BasicNameValuePair("password", password));

                        //Note in logcat that the request has started
                        Log.d("Request!", "Starting...");

                        //getting product details by making HTTP request
                        //Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(urlLogin, "GET", params);

                        //check the log for json response
                        Log.d("Login Attempt", json.toString());

                        //json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1){

                            //Note in logcat login successful
                            Log.d("Login Successful!", json.toString());

                             //Intent to send user to User_Details
                            Intent ii = new Intent(Login.this, user_details.class);

                            startActivity(ii);

                            return json.getString(TAG_MESSAGE);

                        } else {
                            Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                            progressDialog.setMessage("Failed to find that username");
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }

            return null;
            }



        }
        /*
        After completing background task dismiss the progress dialog
         */

        protected void onPostExecute(String file_url){
            if (progressDialog!= null){
                progressDialog.dismiss();
                progressDialog = null;
            }
        }

    }



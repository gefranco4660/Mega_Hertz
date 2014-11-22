package com.example.gabriel.mega_hurtz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;

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
public class Login extends Activity {

    String username;
    String password;

    //progress Dialog
    private ProgressDialog progressDialog;

    //JSON parser class
    JSONParser jsonParser = new JSONParser();

    //url to login.php
    private static final String urlLogin = "http://www.westbayluxury.com/login.php";

    //JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_USER = "user";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        //login button
        Button login = (Button) findViewById(R.id.sign_in);

        //getting login details from intent
        Intent i = getIntent();

        //getting username and password from intent
        username = i.getStringExtra(TAG_USERNAME);
        password = i.getStringExtra(TAG_PASSWORD);

        //Getting complete user details in background thread
        new GetUserDetails().execute();
    }

    /*
    Background Async Task to Get complete User Details
    */
    class GetUserDetails extends AsyncTask<String, String, String>{
        /*
        Before starting background thread show progress dialog
         */
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Loading User details. Please wait...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
        /*
        Getting user details in background thread
         */
        protected String doInBackground(String... params){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //Check for success tag
                    int success;
                    try{
                        //Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("username", username));
                        params.add(new BasicNameValuePair("password", password));

                        //getting product details by making HTTP request
                        //Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(urlLogin, "GET", params);

                        //check the log for json response
                        Log.d("Login Details", json.toString());

                        //json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1){
                            //successfully received user details
                            JSONArray userObj = json.getJSONArray(TAG_USER);

                            //get first user object from JSON array
                            JSONObject user = userObj.getJSONObject(0);

                            //user details with this username found
                        } else {
                            progressDialog.setMessage("Failed to find that username");
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            });

            return null;
        }
        /*
        After completing background task dismiss the progress dialog
         */
        protected void onPostExecute(String file_url){
            progressDialog.dismiss();
        }
    }

}


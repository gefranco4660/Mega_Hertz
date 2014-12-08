package com.example.gabriel.mega_hurtz;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Gabriel.
 */
public class LoadSingleCar extends AsyncTask<String, String, String> {
    ProgressDialog progressupdate;//Creating JSON Parser object
    JSONParser jsonParser = new JSONParser();

    HashMap<String, String> carData;
    Context c;

    //URL to get all cars list
    private static String URL_SINGLE_CAR = "http://westbayluxury.com/returnSingleCars.php";

    //JSON Node names
    private static final String TAG_SUCCESS = "success";

    //once the php is updloaded check the tag to make sure you are getting the right array of car info.
    private static final String TAG_CARS = "cars";
    private static final String TAG_VEHICLE_ID = "vehicle_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_SUMMARY = "summary";
    private static final String TAG_MPG = "mpg";
    private static final String TAG_PRICE = "costperday";
    private static final String TAG_YEAR = "year";
    private static final String TAG_MAKE = "make";

    //car JSONArray
    JSONArray cars = null;

    public void getThisCarInfo( HashMap<String, String> carData ,String pid){
        this.c = c;
        this.carData = carData;
        this.onPreExecute();
        this.execute();
        this.onPostExecute(URL_SINGLE_CAR);
    }

@Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressupdate = new ProgressDialog(c);
        progressupdate.setMessage("Loading Products. Please Wait...");
        progressupdate.setIndeterminate(false);
        progressupdate.setCancelable(true);
        progressupdate.show();
    }

    protected String doInBackground(String... args) {
        //Building parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        //getting JSON string from URL
        JSONObject json = jsonParser.makeHttpRequest(URL_SINGLE_CAR, "GET", params);

        //check your log cat for JSON response
        Log.d("All Products: ", json.toString());

        try {
            //Checking for success tag
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                //product found
                //depending on the php code this might nor be neccessary.
                cars = json.getJSONArray(TAG_CARS);

                //if there is only one product returned by the php then you can make a direct json object from the php
                JSONObject c = cars.getJSONObject(0);

                //Storing each json item in variable
                String id = c.getString(TAG_VEHICLE_ID);
                String name = c.getString(TAG_NAME);
                String price = c.getString(TAG_PRICE);
                String mpg = c.getString(TAG_MPG);
                String year = c.getString(TAG_YEAR);
                String summary = c.getString(TAG_SUMMARY);
                String make = c.getString(TAG_MAKE);


                //adding each car info tag to the hashmap passed into this class when called.
                carData.put(TAG_VEHICLE_ID, id);
                carData.put(TAG_NAME, name);
                carData.put(TAG_PRICE, price);
                carData.put(TAG_MAKE, make);
                carData.put(TAG_SUMMARY, summary);
                carData.put(TAG_YEAR, year);
                carData.put(TAG_MPG, mpg);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
            return null;

    }

    protected void onPostExecute(String file_url){
        if (progressupdate != null) {
            progressupdate.dismiss();
            progressupdate = null;
        }
    }
}

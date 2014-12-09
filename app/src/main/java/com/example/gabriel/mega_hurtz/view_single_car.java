package com.example.gabriel.mega_hurtz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* Created by Gabriel.
*
* */
public class view_single_car extends Activity implements OnClickListener {
    ProgressDialog progressupdate;
    //Creating JSON Parser object
    JSONParser jsonParser = new JSONParser();

    HashMap<String, String> carData;

    int insureValue=0;
    int totcost=0;
    int daysRequested=0;
    String pid;
    Context c= this;


    private static String URL_SINGLE_CAR = "http://westbayluxury.com/returnSingleCar.php";
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_view_single_car);
        pid = intent.getStringExtra("vehicle_id");

        new LoadSingleCar().execute();

        Button reserve = (Button) findViewById(R.id.reserve);
        reserve.setOnClickListener(this);
    }

    class LoadSingleCar extends AsyncTask<String, String, String> {
        //car JSONArray

        @Override
        protected void onPreExecute() {
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
            params.add(new BasicNameValuePair("vehicle_id", pid));

            //getting JSON string from URL
            JSONObject json = jsonParser.makeHttpRequest(URL_SINGLE_CAR, "GET", params);

            //check your log cat for JSON response
            Log.d("Single car Products: ", json.toString());

            try {
                //Checking for success tag
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    //product found
                    //depending on the php code this might nor be neccessary.
                    JSONArray cars = json.getJSONArray(TAG_CARS);

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

                    Log.d("cardata :", carData.get("mpg"));


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            if (progressupdate != null) {
                progressupdate.dismiss();
                progressupdate = null;
            }
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    TextView carname = (TextView) findViewById(R.id.name);
                    TextView priceperday = (TextView) findViewById(R.id.costperday);
                    TextView summary = (TextView) findViewById(R.id.summary);
                    TextView totalcost = (TextView) findViewById(R.id.cost);
                    TextView year = (TextView) findViewById(R.id.year);
                    TextView make = (TextView) findViewById(R.id.make);
                    TextView mpg = (TextView) findViewById(R.id.mpg);
                    TextView insuranceCost = (TextView) findViewById(R.id.insuranceCost);
                    TextView numberofDays = (TextView) findViewById(R.id.numberOfDays);
                    ImageView carView = (ImageView) findViewById(R.id.carpic);

                    carname.setText(carData.get("name"));
                    priceperday.setText(carData.get("price"));
                    summary.setText(carData.get("summary"));
                    totalcost.setText(totcost);
                    year.setText(carData.get("year"));
                    make.setText(carData.get("make"));
                    mpg.setText(carData.get("mpg"));
                    insuranceCost.setText(insureValue);
                    numberofDays.setText(daysRequested);

                    String s = carData.get("vehicle_id");

                    if (s.equals("1")) {
                        carView.setImageResource(R.drawable.id1);
                    } else if (s.equals("2")) {
                        carView.setImageResource(R.drawable.id2);
                    } else if (s.equals("3")) {
                        carView.setImageResource(R.drawable.id3);
                    } else if (s.equals("4")) {
                        carView.setImageResource(R.drawable.id4);
                    } else if (s.equals("5")) {
                        carView.setImageResource(R.drawable.id5);
                    } else if (s.equals("6")) {
                        carView.setImageResource(R.drawable.id6);
                    } else if (s.equals("7")) {
                        carView.setImageResource(R.drawable.id7);
                    } else if (s.equals("8")) {
                        carView.setImageResource(R.drawable.id8);
                    } else if (s.equals("9")) {
                        carView.setImageResource(R.drawable.id9);
                    } else if (s.equals("10")) {
                        carView.setImageResource(R.drawable.id10);
                    } else if (s.equals("11")) {
                        carView.setImageResource(R.drawable.id11);
                    } else if (s.equals("12")) {
                        carView.setImageResource(R.drawable.id12);
                    } else if (s.equals("13")) {
                        carView.setImageResource(R.drawable.id13);
                    } else if (s.equals("14")) {
                        carView.setImageResource(R.drawable.id14);
                    } else if (s.equals("15")) {
                        carView.setImageResource(R.drawable.id15);
                    } else if (s.equals("16")) {
                        carView.setImageResource(R.drawable.id16);
                    } else {
                        carView.setImageResource(R.drawable.ic_launcher);
                    }
                }
            });
        }
    }


    public void onClick(View v){
        Intent intent = new Intent (this,ConfirmReservationActivity.class);
        startActivity(intent);


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_single_car, menu);
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




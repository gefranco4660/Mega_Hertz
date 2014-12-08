package com.example.gabriel.mega_hurtz;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carl on 11/30/2014.
 */
public class AllProductsActivity extends ListActivity{

    //Progress Dialog
    private ProgressDialog progressDialog;

    //Creating JSON Parser object
    JSONParser jsonParser = new JSONParser();

    ArrayList<HashMap<String, String>> carsList;

    //URL to get all cars list
    private static String URL_ALL_CARS = "http://westbayluxury.com/returnAllCars.php";

    //JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CARS = "cars";
    private static final String TAG_VEHICLE_ID = "vehicle_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_PRICE = "costperday";

    //car JSONArray
    JSONArray cars = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        //hashmap for listView
        carsList = new ArrayList<HashMap<String, String>>();

        //loading products in Background Thread
        new LoadAllProducts().execute();

        //Get listview
        ListView listView = getListView();


        //THIS WILL BE THE SINGLE CAR DETAILS PAGE I THINK  **********************
        // on selecting single product
        // launching Edit Product Screen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                        .toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        view_single_car.class);
                // sending pid to next activity
                in.putExtra(TAG_VEHICLE_ID, pid);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
            }
        });
    }
        // Response from Edit Product Activity
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            // if result code 100
            if (resultCode == 100) {
                // if result code 100 is received
                // means user edited/deleted product
                // reload this screen again
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }

        }

        class LoadAllProducts extends AsyncTask<String, String, String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                progressDialog = new ProgressDialog(AllProductsActivity.this);
                progressDialog.setMessage("Loading Products. Please Wait...");
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(true);
                progressDialog.show();
            }

            protected String doInBackground(String... args){
                //Building parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                //getting JSON string from URL
                JSONObject json = jsonParser.makeHttpRequest(URL_ALL_CARS, "GET", params);

                //check your log cat for JSON response
                Log.d("All Products: ", json.toString());

                try{
                    //Checking for success tag
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1){
                        //products found
                        //Getting array of products
                        cars = json.getJSONArray(TAG_CARS);

                        //looping through all products
                        for (int i = 0; i < cars.length(); i++){
                            JSONObject c = cars.getJSONObject(i);

                            //Storing each json item in variable
                            String id = c.getString(TAG_VEHICLE_ID);
                            String name = c.getString(TAG_NAME);
                            String price = c.getString(TAG_PRICE);

                            //creating new Hash map
                            HashMap<String, String> map = new HashMap<String, String>();

                            //adding each child node to HashMap key => value
                            map.put(TAG_VEHICLE_ID, id);
                            map.put(TAG_NAME, name);
                            map.put(TAG_PRICE, price);

                            //adding hashlist to arraylist
                            carsList.add(map);
                        }
                    } else {
                        //no products found
                    }
                } catch(JSONException e){
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(String file_url){
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }

                runOnUiThread(new Runnable() {
                    public void run() {
                        /**
                         * Updating parsed JSON data into ListView
                         * */
                        MySimpleAdapter adapter = new MySimpleAdapter(
                                AllProductsActivity.this, carsList,
                                new String[] { TAG_VEHICLE_ID,TAG_NAME, TAG_PRICE},
                                new int[] { R.id.pid, R.id.name, R.id.price });

                        // updating listview
                        setListAdapter(adapter);
                    }
                });
            }
        }
}

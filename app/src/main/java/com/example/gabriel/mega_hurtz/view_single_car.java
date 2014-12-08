package com.example.gabriel.mega_hurtz;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/*
* Created by Gabriel.
*
* */
public class view_single_car extends Activity implements OnClickListener {
    LoadSingleCar getdata = new LoadSingleCar();
    HashMap<String, String> carData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        getdata.getThisCarInfo(carData,intent.getStringExtra("pid") );


        setContentView(R.layout.activity_view_single_car);
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

        if(s.equals("1")) {
            carView.setImageResource(R.drawable.id1);
        }else if (s.equals("2")) {
            carView.setImageResource(R.drawable.id2);
        }else if (s.equals("3")) {
            carView.setImageResource(R.drawable.id3);
        }else if (s.equals("4")) {
            carView.setImageResource(R.drawable.id4);
        }else if (s.equals("5")) {
            carView.setImageResource(R.drawable.id5);
        }else if (s.equals("6")) {
            carView.setImageResource(R.drawable.id6);
        }else if (s.equals("7")) {
            carView.setImageResource(R.drawable.id7);
        }else if (s.equals("8")) {
            carView.setImageResource(R.drawable.id8);
        }else if (s.equals("9")) {
            carView.setImageResource(R.drawable.id9);
        }else if (s.equals("10")) {
            carView.setImageResource(R.drawable.id10);
        }else if (s.equals("11")) {
            carView.setImageResource(R.drawable.id11);
        }else if (s.equals("12")) {
            carView.setImageResource(R.drawable.id12);
        }else if (s.equals("13")) {
            carView.setImageResource(R.drawable.id13);
        }else if (s.equals("14")) {
            carView.setImageResource(R.drawable.id14);
        }else if (s.equals("15")) {
            carView.setImageResource(R.drawable.id15);
        }else if (s.equals("16")) {
            carView.setImageResource(R.drawable.id16);
        }else{
            carView.setImageResource(R.drawable.ic_launcher);
        }


        Button reserve = (Button) findViewById(R.id.reserve);
        reserve.setOnClickListener(this);
    }

    public void onClick(View v){
        Intent intent = new Intent (this,ConfirmReservationActivity.class);
        startActivity(intent);


    }

    @Override
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




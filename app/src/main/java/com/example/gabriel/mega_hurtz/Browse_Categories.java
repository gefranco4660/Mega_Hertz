package com.example.gabriel.mega_hurtz;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


public class Browse_Categories extends Activity implements OnClickListener {

    int choice[] = new int[10];
    String selected = "Selected Cars";
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_prompt);

        Button search = (Button)findViewById(R.id.search);
        search.setOnClickListener(this);

        // Checkboxes
        CheckBox minivan = (CheckBox) findViewById(R.id.minivan);
        minivan.setOnClickListener(this);

        CheckBox sports = (CheckBox) findViewById(R.id.sports);
        sports.setOnClickListener(this);

        CheckBox luxury = (CheckBox) findViewById(R.id.luxury);
        luxury.setOnClickListener(this);

        CheckBox fullSized = (CheckBox) findViewById(R.id.fullSized);
        fullSized.setOnClickListener(this);

        CheckBox midSized = (CheckBox) findViewById(R.id.midsized);
        midSized.setOnClickListener(this);

        CheckBox premium = (CheckBox) findViewById(R.id.premium);
        premium.setOnClickListener(this);

        CheckBox convertible = (CheckBox) findViewById(R.id.convertible);
        convertible.setOnClickListener(this);

        CheckBox suv = (CheckBox) findViewById(R.id.sportsUtility);
        suv.setOnClickListener(this);

        CheckBox economy = (CheckBox) findViewById(R.id.economy);
        economy.setOnClickListener(this);

        CheckBox compact = (CheckBox) findViewById(R.id.compact);
        compact.setOnClickListener(this);


    }
    public void toggleButs(View v){

        boolean checked = ((CheckBox)v).isChecked();

        switch (v.getId()){
            case R.id.minivan:
                if (checked){
                    choice[0] = 1;
                }

            case R.id.sports:
                if (checked){
                    choice[1] = 1;
                }

            case R.id.luxury:
                if (checked){
                    choice[2] = 1;
                }

            case R.id.fullSized:
                if (checked){
                    choice[3] = 1;
                }

            case R.id.midsized:
                if (checked){
                    choice[4] = 1;
                }

            case R.id.premium:
                if (checked){
                    choice[5] = 1;
                }

            case R.id.convertible:
                if (checked){
                    choice[6] = 1;
                }

            case R.id.sportsUtility:
                if (checked){
                    choice[7] = 1;
                }

            case R.id.economy:
                if (checked){
                    choice[8] = 1;
                }

            case R.id.compact:
                if (checked){
                    choice[9] = 1;
                }

        }
        for (int i = 0; i < choice.length; i++){
            sum = sum + choice[i];
        }

    }

    public static class DatePickerFragmentTo extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            EditText to = (EditText) getActivity().findViewById(R.id.toDatepick);
            String date = (month+1)+"/"+day+"/"+year;
            to.setText(date);
        }
    }
    public void showDatePickerDialogTo(View v) {
        DialogFragment newFragment = new DatePickerFragmentTo();
        newFragment.show(getFragmentManager() ,"dateFromPicker");
    }

    public static class DatePickerFragmentFrom extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            EditText from = (EditText) getActivity().findViewById(R.id.fromDatepick);
            String date = (month+1)+"/"+day+"/"+year;
            from.setText(date);
        }

    }
    public void showDatePickerDialogFrom(View v) {
        DialogFragment newFragment = new DatePickerFragmentFrom();
        newFragment.show(getFragmentManager() ,"dateFromPicker");
    }

    @Override
    public void onClick(View v){

        for (int i = 0; i < choice.length; i++){
            sum = sum + choice[i];
        }
        // if statement for no selections
        if (v.getId() == R.id.search && sum < 1){
//            creates an array to pass into the listview with values that create the cars.
            Intent intent = new Intent (this,AllProductsActivity.class);
            startActivity(intent);
        }
        //if statement for any selections
        else if (v.getId() == R.id.search && sum > 0){
            // creates an array to pass into the listview with values that create the cars.
            Intent intent = new Intent (this,some_products.class);
            intent.putExtra(selected, choice);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.browse__categories, menu);
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

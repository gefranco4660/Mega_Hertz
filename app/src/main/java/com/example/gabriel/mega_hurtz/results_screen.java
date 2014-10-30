package com.example.gabriel.mega_hurtz;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;




public class results_screen extends ListActivity {
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            String[] values = new String[] { "Economy", "Midsized", "Full Sized",
                    "Sports Utility", "MiniVan", "Luxury", "Sports Car", "Convertible",
                    "Premium", "Compact" };
            // use your custom layout
            DemoArrayAdapter adapter = new DemoArrayAdapter(this,values);
            setListAdapter(adapter);
        }

        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
            String item = (String) getListAdapter().getItem(position);
            Intent intent = new Intent(this , DetailedCarDescription.class);
            Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DemoArrayAdapter adapter = new DemoArrayAdapter<string>;
        setContentView(R.layout.activity_results_screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results_screen, menu);
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
    }*/
}

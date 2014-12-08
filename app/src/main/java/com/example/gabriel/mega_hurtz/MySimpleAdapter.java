package com.example.gabriel.mega_hurtz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gabriel on 12/7/2014.
 */
public class MySimpleAdapter extends SimpleAdapter{

    private final Context context;
    ArrayList<HashMap<String, String>> carslist;
    private final String[] from;
    private final int[] to;


        public MySimpleAdapter(Context context, ArrayList<HashMap<String, String>> carslist, String[] from, int[] to) {
            super(context,carslist , R.layout.list_layout, from,to);
            this.context = context;
            this.carslist = carslist;
            this.from = from;
            this.to = to;
        }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.list_layout, parent, false);
            TextView nameView = (TextView) rowView.findViewById(to[1]);
            TextView priceView = (TextView) rowView.findViewById(to[2]);
            TextView pid = (TextView) rowView.findViewById(to[0]);
            ImageView carView = (ImageView) rowView.findViewById(R.id.car);

                HashMap<String, String> car = carslist.get(position);

                nameView.setText(car.get(from[1]));
                priceView.setText(String.format("Cost per day: %s $", car.get(from[2])));
                pid.setText(car.get(from[0]));
                carView.setContentDescription(car.get(from[0]));

                String s = car.get(from[0]);

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


            return rowView;
        }

}

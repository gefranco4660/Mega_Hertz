package com.example.gabriel.mega_hurtz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gabriel on 10/26/2014.
 */

public class DemoArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public DemoArrayAdapter(Context context, String[] values) {
            super(context, R.layout.list_layout, values);
            this.context = context;
            this.values = values;
        }


//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View rowView = inflater.inflate(R.layout.list_layout, parent, false);
//            TextView textView = (TextView) rowView.findViewById(R.id.Label);
//            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//            textView.setText(values[position]);
//            // change the icon for Windows and iPhone
//            String s = values[position];
//            if (s.startsWith("Convertible")) {
//                imageView.setImageResource(R.drawable.convertable);
//            } else if (s.startsWith("Luxury")){
//                imageView.setImageResource(R.drawable.luxury);
//            }else if (s.startsWith("Sports Car")){
//                imageView.setImageResource(R.drawable.sports_car);
//            }else{
//                imageView.setImageResource(R.drawable.no_stock);
//            }
//
//
//            return rowView;
//        }
    }


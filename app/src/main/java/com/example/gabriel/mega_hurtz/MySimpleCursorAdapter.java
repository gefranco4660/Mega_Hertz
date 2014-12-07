package com.example.gabriel.mega_hurtz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/*
 Created by Gabriel on 10/25/2014.
*/

public class MySimpleCursorAdapter extends SimpleCursorAdapter {
        private final Context context;
        private final String[] vID;

        public MySimpleCursorAdapter(Context context, String[] values) {
            super(context, R.layout.list_layout,null,null,null);
            this.context = context;
            this.vID = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_layout, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.Label);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
           textView.setText(vID[position]);
            // change the icon for Windows and iPhone

            imageView.setImageResource(R.drawable.ic_launcher);

            return rowView;
       }
    }

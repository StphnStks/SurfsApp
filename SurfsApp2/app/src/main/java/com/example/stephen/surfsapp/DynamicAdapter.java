package com.example.stephen.surfsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Stephen on 08/03/2015.
 */
public class DynamicAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] text;
    private final Integer[] imageId;

    public DynamicAdapter(Activity context, String[] text, Integer[] imageId) {
        super(context, R.layout.row, text);

        this.context = context;
        this.text = text;
        this.imageId = imageId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if(rowView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.row, null, true);
        }

        TextView rowTextView = (TextView) rowView.findViewById(R.id.textView);
        ImageView rowImageView = (ImageView) rowView.findViewById(R.id.imageView);

        rowTextView.setText(text[position]);
        rowImageView.setImageResource((imageId[position]));

        return rowView;
    }

    /*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dynamic_adapter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */

}
package com.example.amarjot.mynewapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListview extends ArrayAdapter <String>{

    private String [] breakfastname;
    private String [] breakfastdescription;
    private Integer [] breakfastid;

    private Activity context;

    public CustomListview(@NonNull Context context, String [] breakfastname, String [] breakfastdescription, Integer [] breakfastid) {
        super(context, R.layout.listview_layout, breakfastname);

        this.context= (Activity) context;
        this.breakfastname=breakfastname;
        this.breakfastdescription=breakfastdescription;
        this.breakfastid=breakfastid;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.imageView.setImageResource(breakfastid[position]);
        viewHolder.textViewName.setText(breakfastname[position]);
        viewHolder.textViewDescription.setText(breakfastdescription[position]);

        return r;
    }

    class ViewHolder
    {
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;

        ViewHolder (View v)
        {
            textViewName = (TextView) v.findViewById(R.id.textTitle);
            textViewDescription = (TextView) v.findViewById(R.id.textDescription);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }
}

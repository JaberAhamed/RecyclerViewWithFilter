package com.example.recyclerviewwithspnerandedittextfilter;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {

    private Context context;
    private List<City> cityList;

    public CityAdapter(Context context, int textViewResourceId,
                       List<City> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.cityList = values;
    }

    public int getCount(){
        return cityList.size();
    }

    public City getItem(int position){
        return cityList.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setTextColor(Color.BLACK);
        view.setGravity(Gravity.CENTER);
        view.setText(cityList.get(position).getCity());

        return view;
    }

    //View of Spinner on dropdown Popping

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView view = new TextView(context);
        view.setTextColor(Color.BLACK);
        view.setText(cityList.get(position).getCity());
        view.setHeight(60);

        return view;
    }

   /* private class AddressFilter extends Filter
    {


        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String cityName=charSequence.toString();
            FilterResults filterResults=new FilterResults();

            if(cityName!=null){


            }




            return null;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        }
    }*/
}

package com.example.recyclerviewwithspnerandedittextfilter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class RclAdapter extends RecyclerView.Adapter<RclAdapter.WorkDayViewHolder> implements Filterable {



    private Context context;
    int i=12;
    private ArrayList<City> itemList;
    private ArrayList<City>list;
    int count=-1;
    CityFilter cityFilter;



    public RclAdapter(Context context, ArrayList<City> worksDayClasses) {
        this.context = context;
        this.list =worksDayClasses;

        this.itemList = worksDayClasses;
    }


    @Override
    public WorkDayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.rcl_adapter,parent,false);

        WorkDayViewHolder workDayViewHolder = new WorkDayViewHolder(v);
        return workDayViewHolder;


    }



    @Override
    public void onBindViewHolder(final WorkDayViewHolder holder, final int position) {


          holder.textType.setText(list.get(position).getCity());




    }



    @Override
    public int getItemCount() {

        return list.size();
    }

    @Override
    public Filter getFilter() {
        if(cityFilter==null){
            cityFilter = new CityFilter();
        }
        return cityFilter;
    }

    public class WorkDayViewHolder extends RecyclerView.ViewHolder{
        //layout_1 widget

        TextView textType;


        public WorkDayViewHolder(View itemView) {
            super(itemView);

            textType = (TextView) itemView.findViewById(R.id.textid);



        }
    }

    public class  CityFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String city = charSequence.toString();
            FilterResults filterResults =new FilterResults();

            if(city!=null){
                ArrayList<City>cities=new ArrayList<>();

                for(int i =0;i<itemList.size();i++){

                    if(itemList.get(i).getCity().toString().contains(city)){

                        City city1 = itemList.get(i);
                        cities.add(city1);
                    }
                }
                filterResults.count=cities.size();
                filterResults.values=cities;

            }
            else {

                filterResults.count =itemList.size();
                filterResults.values=itemList;

            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list = (ArrayList<City>)filterResults.values;
            notifyDataSetChanged();

        }
    }


}
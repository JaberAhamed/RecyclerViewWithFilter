package com.example.recyclerviewwithspnerandedittextfilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends BaseAdapter implements Filterable {

    private LayoutInflater mLayoutInflater;
    private List<Address> addressList;
    private List<Address> addressFilterList;
    private AddressFilter addressFilter;
    private Context context;

    public AddressAdapter(Context context, List data){
        addressList = data;
        addressFilterList=data;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public Address getItem(int position) {
        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View updateView;
        ViewHolder viewHolder;
        if (view == null) {
            updateView = mLayoutInflater.inflate(R.layout.address_listitem, null);
            viewHolder = new ViewHolder();

            viewHolder.tvName = (TextView) updateView.findViewById(R.id.nameTV);
            viewHolder.tvArea = (TextView) updateView.findViewById(R.id.areaTV);
            viewHolder.tvStreet = (TextView) updateView.findViewById(R.id.streetTv);

            updateView.setTag(viewHolder);

        } else {
            updateView = view;
            viewHolder = (ViewHolder) updateView.getTag();
        }

        final Address item = getItem(position);

        viewHolder.tvName.setText(item.getBuildingName());
        viewHolder.tvArea.setText(item.getArea());
        viewHolder.tvStreet.setText(String.valueOf(item.getStreet()));

        return updateView;
    }

    @Override
    public Filter getFilter() {
        if (addressFilter == null) {
            addressFilter = new AddressFilter();
        }
        return addressFilter;
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvArea;
        TextView tvStreet;
    }

// InnerClass for enabling Search feature by implementing the methods

    private class AddressFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

//below checks the match for the cityId and adds to the filterlist
            long cityId= Long.parseLong(constraint.toString());
            FilterResults results = new FilterResults();

            if (cityId > 0) {
                ArrayList<Address> filterList = new ArrayList<Address>();
                for (int i = 0; i < addressFilterList.size(); i++) {

                    if ( (addressFilterList.get(i).getCityId() )== cityId) {

                        Address address = addressFilterList.get(i);
                        filterList.add(address);
                    }
                }

                results.count = filterList.size();
                results.values = filterList;

            } else {

                results.count = addressFilterList.size();
                results.values = addressFilterList;

            }
            return results;
        }
        //Publishes the matches found, i.e., the selected cityids
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            addressList = (ArrayList<Address>)results.values;
            notifyDataSetChanged();
        }
    }
}
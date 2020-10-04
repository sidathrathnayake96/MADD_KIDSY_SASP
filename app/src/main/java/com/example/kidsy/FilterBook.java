package com.example.kidsy;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterBook extends Filter {

    private AdapterProductSeller adapter;
    private ArrayList<ModelBook>filterList;

    public FilterBook(AdapterProductSeller adapter, ArrayList<ModelBook> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint !=null && constraint.length() > 0){
            constraint=constraint.toString().toUpperCase();

            ArrayList<ModelBook> filterModels=new ArrayList<>();
            for(int i=0 ; i<filterList.size(); i++){
                if(filterList.get(i).getBookname().toUpperCase().contains(constraint) ||
                        filterList.get(i).getBookcategory().toUpperCase().contains(constraint)){
                    filterModels.add(filterList.get(i));

                }
            }
            results.count =filterModels.size();
            results.values=filterModels;
        }
        else{
            results.count =filterList.size();
            results.values=filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint , FilterResults results) {

        adapter.productList = (ArrayList<ModelBook>)results.values;
        adapter.notifyDataSetChanged();

    }
}

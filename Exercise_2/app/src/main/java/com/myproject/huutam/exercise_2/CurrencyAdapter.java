package com.myproject.huutam.exercise_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by huutam on 05/04/2017.
 */

public class CurrencyAdapter extends BaseAdapter {

    Context context;
    int mylayout;
    ArrayList<String> currencyList;
    public CurrencyAdapter(Context context, int mylayout, ArrayList<String> currencyList){
        this.currencyList = currencyList;
        this.context=context;
        this.mylayout=mylayout;
    }
    @Override
    public int getCount() {
        return currencyList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(mylayout,null);
        TextView txtName = (TextView)view.findViewById(R.id.tv_item);
            txtName.setText(currencyList.get(i));

        return view;
    }
}

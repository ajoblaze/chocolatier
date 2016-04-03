package com.choco.chocolatier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SRIN on 8/26/2015.
 */
public class TransactionListAdapter extends ArrayAdapter<Transaction> {

    private Context context;
    private ArrayList<Transaction> list;
    private int res;

    public TransactionListAdapter(Context context, int resource, ArrayList<Transaction> list) {
        super(context, resource, list);
        this.context = context;
        res = resource;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(res, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.choco_name);
        TextView desc = (TextView) convertView.findViewById(R.id.choco_desc);
        TextView price = (TextView) convertView.findViewById(R.id.choco_price);
        TextView qty = (TextView) convertView.findViewById(R.id.choco_qty);

        name.setText(list.get(position).getChoco().getName());
        desc.setText(list.get(position).getChoco().getDesc());
        price.setText(list.get(position).getChoco().getPrice());
        String s = "Quantity : " + list.get(position).getQty();
        qty.setText(s);

        return convertView;
    }
}
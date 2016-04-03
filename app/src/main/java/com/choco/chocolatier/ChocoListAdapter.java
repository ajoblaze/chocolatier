package com.choco.chocolatier;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SRIN on 8/26/2015.
 */
public class ChocoListAdapter extends ArrayAdapter<Choco> {

    private String username;
    private Context context;
    private ArrayList<Choco> list;
    private int res;

    public ChocoListAdapter(Context context, int resource, ArrayList<Choco> list, String username) {
        super(context, resource, list);
        this.context = context;
        res = resource;
        this.list = list;
        this.username = username;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(res, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.choco_name);
        TextView desc = (TextView) convertView.findViewById(R.id.choco_desc);
        TextView price = (TextView) convertView.findViewById(R.id.choco_price);
        Button btn = (Button) convertView.findViewById(R.id.btn_buy);

        name.setText(list.get(position).getName());
        desc.setText(list.get(position).getDesc());
        price.setText(list.get(position).getPrice());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CheckOutActivity.class);
                i.putExtra("username", username);
                i.putExtra("choco", position);
                context.startActivity(i);
            }
        });

        return convertView;
    }
}
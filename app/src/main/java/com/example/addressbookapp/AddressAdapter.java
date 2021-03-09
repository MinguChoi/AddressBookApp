package com.example.addressbookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AddressAdapter extends ArrayAdapter {
    private List<Address> addressList;
    private Context context;

    public AddressAdapter(List<Address> addressList, Context ctx) {
        super(ctx, R.layout.row_layout, addressList);
        this.addressList = addressList;
        this.context = ctx;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_layout, parent, false);
        }
        TextView nameTXTV = (TextView) convertView.findViewById(R.id.name_inRow);
        TextView phoneTXTV = (TextView) convertView.findViewById(R.id.phone_inRow);
        TextView emailTXTV = (TextView) convertView.findViewById(R.id.email_inRow);

        Address a = addressList.get(position);
        nameTXTV.setText(a.getName());
        phoneTXTV.setText(a.getPhone());
        emailTXTV.setText(a.getEmail());

        return convertView;
    }
}

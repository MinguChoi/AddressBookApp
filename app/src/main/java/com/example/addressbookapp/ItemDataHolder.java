package com.example.addressbookapp;

import android.view.View;
import android.widget.TextView;

public class ItemDataHolder {
    public TextView nameView;
    public TextView phoneView;
    public TextView emailView;

    public ItemDataHolder (View root) {
        this.nameView = root.findViewById(R.id.name_inRow);
        this.phoneView = root.findViewById(R.id.phone_inRow);
        this.emailView = root.findViewById(R.id.email_inRow);
    }

}

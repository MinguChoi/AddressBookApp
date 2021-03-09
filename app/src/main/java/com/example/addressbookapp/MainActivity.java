package com.example.addressbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Address> arrList;
    private AddressAdapter adapter;

    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrList = new ArrayList<Address>();
        adapter = new AddressAdapter(arrList, this);

        ListView listView = (ListView) findViewById(R.id.main_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                Address tmp = (Address) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                intent.putExtra("name", tmp.getName());
                intent.putExtra("phone", tmp.getPhone());
                intent.putExtra("email", tmp.getEmail());

                startActivityForResult(intent, 20);
            }
        });
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add_button: {
                Intent intent = new Intent(MainActivity.this, AddAddress.class);
                startActivityForResult(intent, 10);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String email = data.getStringExtra("email");

            Address newAddress = new Address(name, phone, email);
            arrList.add(newAddress);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "New Address is added", Toast.LENGTH_LONG).show();
        }

        else if (requestCode == 20 && resultCode == RESULT_OK) {
            arrList.remove(selectedPosition);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Address is deleted", Toast.LENGTH_LONG).show();
        }
    }

}
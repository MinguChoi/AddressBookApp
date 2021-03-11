package com.example.addressbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "AddressBook";

    private ArrayList<Address> arrList;
    private AddressAdapter adapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrList = new ArrayList<Address>();
        refreshList();

        adapter = new AddressAdapter(arrList, this);

        listView = findViewById(R.id.main_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
            refreshList();
            Toast.makeText(this, "New Address is added", Toast.LENGTH_LONG).show();
        }

        else if (requestCode == 20 && resultCode == RESULT_OK) {
            refreshList();
            Toast.makeText(this, "Address is deleted", Toast.LENGTH_LONG).show();
        }
        else {
        }
        adapter.notifyDataSetChanged();
    }

    public void refreshList() {
        DBHelper helper = new DBHelper(MainActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name, phone, email from tb_address order by _id desc", null);

        arrList.clear();
        while(cursor.moveToNext()) {
            Address newAddress = new Address (cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            //arrList.add(newAddress);
            arrList.add(newAddress);
        }
        Log.i(TAG, "arrList after read data " + printList(arrList));
        db.close();

    }

    public String printList (ArrayList list) {
        String result = "";
        for(int i=0; i<list.size(); i++) {
            result = result + list.get(i) + "\n";
        }
        return result;
    }
}
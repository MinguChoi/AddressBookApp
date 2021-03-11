package com.example.addressbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = "AddressBook";
    private TextView nameTXT, phoneTXT, emailTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameTXT = findViewById(R.id.name_detail);
        phoneTXT = findViewById(R.id.phone_detail);
        emailTXT = findViewById(R.id.email_detail);

        Intent intent = getIntent();
        nameTXT.setText(intent.getStringExtra("name"));
        phoneTXT.setText(intent.getStringExtra("phone"));
        emailTXT.setText(intent.getStringExtra("email"));
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ok_detail: {
                finish();
                break;
            }
            case R.id.delete_detail: {
                deleteDataAtDB();
                Log.i(TAG, "Delete button is clicked");
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    public void deleteDataAtDB() {
        DBHelper helper = new DBHelper(DetailActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        //db.execSQL("delete from tb_address");
        db.execSQL("delete from tb_address where name = '" + nameTXT.getText().toString() +
                "' And phone = '" + phoneTXT.getText().toString() +
                "' And email = '" + emailTXT.getText().toString() + "'");

        db.close();
    }
}
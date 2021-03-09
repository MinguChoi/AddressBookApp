package com.example.addressbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView name = (TextView) findViewById(R.id.name_detail);
        TextView phone = (TextView) findViewById(R.id.phone_detail);
        TextView email = (TextView) findViewById(R.id.email_detail);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        phone.setText(intent.getStringExtra("phone"));
        email.setText(intent.getStringExtra("email"));
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ok_detail: {
                finish();
                break;
            }
            case R.id.delete_detail: {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
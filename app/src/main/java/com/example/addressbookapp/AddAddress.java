package com.example.addressbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        EditText name = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText phone = (EditText) findViewById(R.id.editTextPhone);
        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        Button cancel = (Button) findViewById(R.id.cancel_button);
        Button ok = (Button) findViewById(R.id.ok_button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(name) || isEmpty(phone) || isEmpty(email)) {
                    Toast.makeText(AddAddress.this, "Please, fill everything out :)", Toast.LENGTH_LONG).show();
                }
                else {
                    //Address newAddress = new Address(name.getText().toString(), phone.getText().toString(), email.getText().toString());
                    Intent intent = new Intent(AddAddress.this, MainActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("phone", phone.getText().toString());
                    intent.putExtra("email", email.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean isEmpty(EditText etxt) {
        if(etxt.getText().toString().trim().length()>0)
            return false;

        return true;
    }
}
package com.example.addressbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddAddress extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    private InputMethodManager imm;

    private ImageView profileImage;
    private EditText nameETXT, phoneETXT, emailETXT;
    private Button cancel, ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);

        profileImage = findViewById(R.id.profile_image);
        nameETXT = findViewById(R.id.editTextTextPersonName);
        phoneETXT = findViewById(R.id.editTextPhone);
        emailETXT = findViewById(R.id.editTextTextEmailAddress);
        cancel = findViewById(R.id.cancel_button);
        ok = findViewById(R.id.ok_button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(nameETXT) || isEmpty(phoneETXT) || isEmpty(emailETXT)) {
                    Toast.makeText(AddAddress.this, "Please, fill everything out :)", Toast.LENGTH_LONG).show();
                }
                else {
                    // data
                    String name = nameETXT.getText().toString();
                    String phone = phoneETXT.getText().toString();
                    String email = emailETXT.getText().toString();
                    // save data
                    DBHelper helper = new DBHelper(AddAddress.this);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    db.execSQL("insert into tb_address (name, phone, email) values (?,?,?)", new String[]{name, phone, email});
                    db.close();
                    // back to mainActivity
                    Intent intent = new Intent(AddAddress.this, MainActivity.class);
//                    intent.putExtra("name", name);
//                    intent.putExtra("phone", phone);
//                    intent.putExtra("email", email);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                imm.hideSoftInputFromWindow(nameETXT.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(phoneETXT.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(emailETXT.getWindowToken(), 0);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });
    }

    public boolean isEmpty(EditText etxt) {
        if(etxt.getText().toString().trim().length()>0)
            return false;

        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }

}
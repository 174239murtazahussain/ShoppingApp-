package com.example.onlineshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;


public class AddItem extends AppCompatActivity {


    private DatabaseReference mDatabase;
    EditText e1,e2,e3,e4,e5;


    private Context mContext;
    //FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Items");
        this.mContext=getApplicationContext();
        Button addbtn = findViewById(R.id.additem);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //e1=(EditText)findViewById(R.id.id);
              e2=(EditText)findViewById(R.id.name);
              e3=(EditText)findViewById(R.id.price);
              e4=(EditText)findViewById(R.id.category);
              e5=(EditText)findViewById(R.id.description);

               String id=mDatabase.push().getKey();
                Items myitem = new Items();
                myitem.setId(id);
                myitem.setName(e2.getText().toString());
                myitem.setPrice(e3.getText().toString());
                myitem.setCategory(e4.getText().toString());
                myitem.setDescription(e5.getText().toString());

                mDatabase.child(id).setValue(myitem);

                Toast.makeText(getApplicationContext(), "Your product has been added!", Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
    }

    }






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
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;

public class SignUp extends AppCompatActivity {

    DatabaseReference reff;
    Seller seller;

    private String selectedImagePath;
    Boolean imageuploaded;
    String bname;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        imageuploaded = false;

        reff = FirebaseDatabase.getInstance().getReference().child("items");
        seller = new Seller();


        Button btn = findViewById(R.id.sellersu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText brandname = findViewById(R.id.ssuname);
                bname = brandname.getText().toString();
                if (brandname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must enter your brand name", Toast.LENGTH_SHORT).show();
                    return;
                }
                EditText email = findViewById(R.id.ssuemail);
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must enter your email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (email.getText().toString().contains("@") != true) {
                    Toast.makeText(getApplicationContext(), "You must enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                EditText pass = findViewById(R.id.ssupass);
                if (pass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must enter a password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pass.getText().toString().length() < 8) {
                    Toast.makeText(getApplicationContext(), "You password must be greater than 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                EditText cpass = findViewById(R.id.ssuconfirmpass);
                if (cpass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must enter re-enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText phone = findViewById(R.id.ssuphone);
                EditText address = findViewById(R.id.ssuaddress);


                seller.setName(brandname.getText().toString());
                seller.setEmail(email.getText().toString());
                seller.setPassword(pass.getText().toString());
                seller.setPhone(phone.getText().toString());
                seller.setAddress(address.getText().toString());
                seller.setLogo(bname);
                /*Items i=new Items();
                i.setName(email.getText().toString());
                i.setPrice(email.getText().toString());
                i.setCategory(email.getText().toString());
                i.setId(email.getText().toString());
                i.setDescription(email.getText().toString());*/
                reff.push().setValue(seller);
                Intent intent = new Intent(SignUp.this, AddItem.class);
                startActivity(intent);
                finish();
            }
        });




    }
}

package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i=(ImageView)findViewById(R.id.image);
        i.setImageResource(R.drawable.online);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        AdView adView=(AdView)findViewById(R.id.adview);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        //checkLogIn();
        //Intent i=new Intent(this,Home.class);
        //startActivity(i);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBuyerSignIn();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSellerSignIn();
            }
        });
    }
    public void openBuyerSignIn()
    {
        Intent intent = new Intent(this, BuyerSignIn.class);
        startActivity(intent);
    }

    public void openSellerSignIn()
    {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    void checkLogIn()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference("Customer");
        // Read from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for( DataSnapshot uniqueKeySnapShot : dataSnapshot.getChildren()){

                    String password = uniqueKeySnapShot.child("password").getValue().toString();
                    String email = uniqueKeySnapShot.child("email").getValue().toString();
                    String status=uniqueKeySnapShot.child("status").getValue().toString();

                    if(status=="true" )
                    {
                            Toast.makeText(getApplicationContext(),"Signed In already", Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                            finish();
                            break;

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Failure to Sign In", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

    }
    });

}
}

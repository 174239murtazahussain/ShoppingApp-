package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);
        Intent intent=getIntent();
        Items i=intent.getParcelableExtra("SingleObject");
        Toast.makeText(this, i.name+i.category, Toast.LENGTH_SHORT).show();
        TextView e1,e2,e3,e4,e5;
        e1=(TextView) findViewById(R.id.id1);
        e2=(TextView) findViewById(R.id.name1);
        e3=(TextView)findViewById(R.id.price1);
        e4=(TextView)findViewById(R.id.category1);
        e5=(TextView) findViewById(R.id.description1);
        ImageView ir=(ImageView)findViewById(R.id.pic);
        ir.setImageResource(R.drawable.shop);

        e1.setText(i.id);
        e2.setText(i.name);
        e3.setText(i.price);
        e4.setText(i.category);
        e5.setText(i.description);
    }
}

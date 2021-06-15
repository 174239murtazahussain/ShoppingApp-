package com.example.onlineshopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.onlineshopping.R.integer.btnplusview;

public class Home extends AppCompatActivity {

    private ListView lv;
    private ArrayList<Items> Stu=new ArrayList<>();
    //ItemsAdapter itemsAdapter;
    DatabaseReference d;

    private DatabaseReference mDatabase;
    myDB db;
    private Context mContext;
    Cart cart=new Cart();

    private  String[] names;// = new String[]{"Coat","Shoes","Ear Rings","Pant","Trousers","Slippers","Bottle","Soap","Salt","Tea"};
    private  String[] prices;//= new String[]{"100","200","300","400","500","600","700","800","900","1000"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        d= FirebaseDatabase.getInstance().getReference("Items");
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    Items item=d1.getValue(Items.class);
                    Stu.add(item);
                }
                db=new myDB(Home.this);
                for(Items i:Stu) {
                    db.insertData(i);
                }
                lv = (ListView) findViewById(R.id.std1);
               ItemsAdapter adapter = new ItemsAdapter(Home.this, Stu);
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void viewCart(View view) {

        Intent i=new Intent(Home.this,ViewCart.class);
                startActivity(i);

    }
    public class ItemsAdapter extends BaseAdapter {

        private Context context;
        public ArrayList<Items> stds;

        public ItemsAdapter(Context context, ArrayList<Items> s) {

            this.context = context;
            this.stds = s;
        }

        @Override
        public int getViewTypeCount() {
            return getCount();
        }

        @Override
        public int getItemViewType(int position) {

            return position;
        }

        @Override
        public int getCount() {
            return stds.size();
        }

        @Override
        public Object getItem(int position) {
            return stds.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.items, null, true);

                holder.button = (Button) convertView.findViewById(R.id.addtocart);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.price = (TextView) convertView.findViewById(R.id.price);
                holder.image = (ImageView) convertView.findViewById(R.id.pic);

                convertView.setTag(holder);
            } else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder) convertView.getTag();
            }
            //holder.checkBox.setText("Checkbox "+position);
            holder.name.setText(stds.get(position).getName());
            holder.price.setText(stds.get(position).getPrice());
            holder.image.setImageResource(R.drawable.shop);
            /*holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();

                }
            });*/
            holder.image.setTag(btnplusview, convertView);
            holder.image.setTag( position);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View tempview = (View) holder.image.getTag(R.integer.btnplusview);
                    TextView tv = (TextView) tempview.findViewById(R.id.name);
                    TextView tv1 = (TextView) tempview.findViewById(R.id.price);
                    Integer pos = (Integer)  holder.image.getTag();
                    //Toast.makeText(context, "Checkbox "+pos+" clicked!", Toast.LENGTH_SHORT).show();
                    Items s=stds.get(pos);
                    //Toast.makeText(context, s.name, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Home.this,SingleItem.class);
                    intent.putExtra("SingleObject",s);
                    startActivity(intent);
                }
            });
            //holder.checkBox.setChecked(stds.get(position).getIsPresent());

            holder.button.setTag(btnplusview, convertView);
            holder.button.setTag( position);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View tempview = (View) holder.button.getTag(R.integer.btnplusview);
                    TextView tv = (TextView) tempview.findViewById(R.id.name);
                    TextView tv1 = (TextView) tempview.findViewById(R.id.price);
                    Integer pos = (Integer)  holder.button.getTag();
                    Toast.makeText(context, "Checkbox "+pos+" clicked!", Toast.LENGTH_SHORT).show();
                    Items s=stds.get(pos);
                    Toast.makeText(context, s.name, Toast.LENGTH_SHORT).show();
                    addToCart(s);
                }
            });


            return convertView;
        }


        ArrayList<Items> getStudents() {
            return stds;
        }

    }

    void addToCart(Items items)
    {
        cart.cart.add(items);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Cart");
        this.mContext=getApplicationContext();
        String id=mDatabase.push().getKey();
        items.setId(id);
        mDatabase.child(id).setValue(items);
        Toast.makeText(getApplicationContext(), "Your product has been added!", Toast.LENGTH_SHORT).show();
    }

        class ViewHolder {

            private Button button;
            private TextView name;
            private TextView price;
            private ImageView image;

        }


    }







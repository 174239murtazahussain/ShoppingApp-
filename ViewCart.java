package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class ViewCart extends AppCompatActivity {
    public ListView lv;
    public ArrayList<Items> Stu=new ArrayList<>();
    private DatabaseReference d;
    ItemsAdapter adapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        d= FirebaseDatabase.getInstance().getReference("Cart");
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    Items item=d1.getValue(Items.class);
                    Stu.add(item);
                }
                if(!Stu.isEmpty()) {
                    lv = (ListView) findViewById(R.id.std1);
                    adapter = new ItemsAdapter(ViewCart.this, Stu);
                    lv.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(ViewCart.this, "The cart is Empty!!", Toast.LENGTH_SHORT).show();
                    Intent ir = new Intent(ViewCart.this, Home.class);
                    startActivity(ir);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void placeOrder(View view) {
        Toast.makeText(this,"Order Placed",Toast.LENGTH_SHORT).show();
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
                convertView = inflater.inflate(R.layout.cartsingle, null, true);

                holder.button = (Button) convertView.findViewById(R.id.delete);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.price = (TextView) convertView.findViewById(R.id.price);

                convertView.setTag(holder);
            } else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder) convertView.getTag();
            }
            //holder.checkBox.setText("Checkbox "+position);
            holder.name.setText(stds.get(position).getName());
            holder.price.setText(stds.get(position).getPrice());
            /*holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();

                }
            });*/
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

                    delete(s);
                    Toast.makeText(context, s.name, Toast.LENGTH_SHORT).show();
                }
            });



            return convertView;
        }
        ArrayList<Items> getStudents() {
            return stds;
        }


    }
void delete(Items s)
{

    DatabaseReference d1=FirebaseDatabase.getInstance().getReference("Cart").child(s.id);
    d1.removeValue();

    /*if(!Stu.isEmpty()) {
        adapter = new ItemsAdapter(ViewCart.this, Stu);
        lv.setAdapter(adapter);
    }
    else
    {
        Toast.makeText(this, "The cart is Empty!!", Toast.LENGTH_SHORT).show();
        Intent ir = new Intent(ViewCart.this, Home.class);
        startActivity(ir);
    }*/


}

    class ViewHolder {

        private Button button;
        private TextView name;
        private TextView price;

    }
}

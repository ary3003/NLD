package ary.com.nld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReceivingProductDetail extends AppCompatActivity {
    String date;
    String name;
    TextView tvDate;
    TextView tvProduct;
    TextView tvStockist;
    TextView tvQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_product_detail);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        name = intent.getStringExtra("value");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar15);
        setSupportActionBar(toolbar);
        setTitle("Details");

        tvDate = (TextView) findViewById(R.id.date);
        tvDate.setText(date);
        tvProduct = (TextView) findViewById(R.id.product);
        tvProduct.setText(name);
        tvStockist = (TextView) findViewById(R.id.stockist);
        tvQuantity = (TextView) findViewById(R.id.quantity);

        Firebase.setAndroidContext(this);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("receiving").child(date);
        databaseReference.keepSynced(true);
        databaseReference.orderByChild("product-name").equalTo(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String key = snapshot.getKey();
                    databaseReference.child(key).child("stockist").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String string = dataSnapshot.getValue().toString();
                            tvStockist.setText(string);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child(key).child("quantity").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String string = dataSnapshot.getValue().toString();
                            tvQuantity.setText(string);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

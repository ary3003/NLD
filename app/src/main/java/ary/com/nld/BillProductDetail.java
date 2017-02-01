package ary.com.nld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BillProductDetail extends AppCompatActivity {

    private TextView stockist, bill, product, quantity, mrp, trade, exp, batch;
    ArrayList<AddingNewProduct> arrayList = new ArrayList<>();
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_product_detail);


        final Intent intent = getIntent();
        title = intent.getStringExtra("temp");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar13);
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);

        stockist = (TextView) findViewById(R.id.stockist);
        bill = (TextView) findViewById(R.id.bill);
        product = (TextView) findViewById(R.id.product);
        quantity = (TextView) findViewById(R.id.quantity);
        mrp = (TextView) findViewById(R.id.mrp);
        trade = (TextView) findViewById(R.id.trade);
        exp = (TextView) findViewById(R.id.exp);
        batch = (TextView) findViewById(R.id.batch);




        Firebase.setAndroidContext(this);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("products");
        databaseReference.orderByChild("name").equalTo(title).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    String key = child.getKey();
                    databaseReference.child(key).child("name").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue().toString();
                            product.setText(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child(key).child("stockist").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue().toString();
                            stockist.setText(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child(key).child("bill number").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue().toString();
                            bill.setText(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child(key).child("quantity").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue().toString();
                            quantity.setText(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child(key).child("mrp").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue().toString();
                            mrp.setText(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child(key).child("trade").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue().toString();
                            trade.setText(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child(key).child("exp").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue().toString();
                            exp.setText(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child(key).child("batch").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue().toString();
                            batch.setText(value);
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

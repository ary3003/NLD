package ary.com.nld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class DetailActivity extends AppCompatActivity {
    String title;
    TextView productName, mrp, trade, stockist, batch;
    Firebase ref, firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://nldfirebaseproject.firebaseio.com/productNames/");



        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        title = extras.getString("temp");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);

        productName = (TextView) findViewById(R.id.textView6);
        mrp = (TextView) findViewById(R.id.textView7);
        trade = (TextView) findViewById(R.id.textView8);
        batch = (TextView) findViewById(R.id.textView12);
        stockist = (TextView) findViewById(R.id.textView10);


        productName.setText(title);

        firebaseRef = ref.child(title);
        final Firebase ref2 = firebaseRef.child("mrp");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str_mrp = dataSnapshot.getValue(String.class);
                mrp.setText(str_mrp);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final Firebase ref3 = firebaseRef.child("trade");
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str_trade = dataSnapshot.getValue(String.class);
                trade.setText(str_trade);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final Firebase ref4 = firebaseRef.child("batch");
        ref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str_batch = dataSnapshot.getValue(String.class);
                batch.setText(str_batch);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final Firebase ref5 = firebaseRef.child("stockist");
        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str_stockist = dataSnapshot.getValue(String.class);
                stockist.setText(str_stockist);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }
}

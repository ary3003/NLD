package ary.com.nld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BillDetailActivity extends AppCompatActivity {

    String str_title;
    EditText editText;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<ListProductDetail> arrayList = new ArrayList<ListProductDetail>();
    ArrayAdapter<String> product_adapter;
    ArrayAdapter<String> mrp_adapter;
    ArrayAdapter<String> trade_adapter;
    ArrayAdapter<String> exp_adapter;
    ArrayAdapter<String> batch_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);

        final Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        str_title = extras.getString("temp");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar12);
        setSupportActionBar(toolbar);
        toolbar.setTitle(str_title);

        editText = (EditText) findViewById(R.id.inputSearch);
        listView = (ListView) findViewById(R.id.mListView);

        Firebase.setAndroidContext(this);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("bill-product-list").child(str_title);
        database.keepSynced(true);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String string = snapshot.getValue(String.class);
                    adapter.add(string);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String temp = ((TextView)view).getText().toString();
                Intent intent = new Intent(BillDetailActivity.this, BillProductDetail.class);
                intent.putExtra("temp", temp);
                startActivity(intent);
            }
        });


    }
}

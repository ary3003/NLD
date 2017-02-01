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




public class ReceivingDetail2Activity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    ArrayAdapter<String> adapter;
    String title, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_detail2);

        Intent intent1 = getIntent();
        Bundle extras = intent1.getExtras();
        title = extras.getString("temp");
        date = extras.getString("date");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);

        Firebase.setAndroidContext(this);
        editText = (EditText) findViewById(R.id.inputSearch);
        listView = (ListView) findViewById(R.id.mListView);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("date-wise-sorting");
        reference.keepSynced(true);
        adapter = new ArrayAdapter<String>(ReceivingDetail2Activity.this, android.R.layout.simple_list_item_1);
        reference.child(date).child(title).addValueEventListener(new ValueEventListener() {
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
                String value = ((TextView)view).getText().toString();
                Intent intent = new Intent(ReceivingDetail2Activity.this, ReceivingProductDetail.class);
                intent.putExtra("date", date);
                intent.putExtra("value", value);
                startActivity(intent);
            }
        });


    }
}

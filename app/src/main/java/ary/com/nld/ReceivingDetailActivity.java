package ary.com.nld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/*  *************************************************************************************************

          THIS ACTIVITY LISTS THE PRODUCTS RECEIVED ON A GIVEN DATE.



*****************************************************************************************************
 */


public class ReceivingDetailActivity extends AppCompatActivity {

    String date;
    ListView mList;
    EditText inputSearch;
    ArrayAdapter<String> searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_detail);

        final Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        date = extras.getString("temp");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        setTitle(date);



        mList = (ListView) findViewById(R.id.mListView);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("receiving-date");
        database.keepSynced(true);
        searchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        database.child(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String str_date = snapshot.getValue(String.class);
                    searchAdapter.add(str_date);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mList.setAdapter(searchAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String temp = ((TextView)view).getText().toString();
                Intent intent1 = new Intent(ReceivingDetailActivity.this, ReceivingDetail2Activity.class);
                intent1.putExtra("temp", temp);
                intent1.putExtra("date", date);
                startActivity(intent1);
            }
        });


        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ReceivingDetailActivity.this.searchAdapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}

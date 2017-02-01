package ary.com.nld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class ExpiryDetailActivity extends AppCompatActivity {

    String string;
    ListView listView;
    EditText editText;
    ArrayAdapter<String> adapterSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expiry_detail);

        final Intent intent = getIntent();
        string = intent.getStringExtra("temp");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar14);
        setSupportActionBar(toolbar);
        toolbar.setTitle(string);

        editText = (EditText) findViewById(R.id.inputSearch);
        listView = (ListView) findViewById(R.id.mListView);

        Firebase.setAndroidContext(this);
        adapterSearch = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("expiry").child(string);
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String value = snapshot.getValue(String.class);
                    adapterSearch.add(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setAdapter(adapterSearch);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String ed = editText.getText().toString().toLowerCase(Locale.getDefault());
                adapterSearch.getFilter().filter(ed);

            }
        });


    }
}

package ary.com.nld;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListActivity extends AppCompatActivity {


    ListView mList;
    EditText inputSearch;
    ArrayAdapter<String> searchAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Firebase.setAndroidContext(this);
        mList = (ListView) findViewById(R.id.mListView);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        setTitle("Products list");

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        searchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        database.child("names-list").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String search = snapshot.getValue(String.class);
                    searchAdapter.add(search);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mList.setAdapter(searchAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = ((TextView)view).getText().toString();
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("temp", temp);
                startActivity(intent);

            }
        });



        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ListActivity.this.searchAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.action_stockist:
                startActivity(new Intent(ListActivity.this, ListStockistsActivity.class));
                break;
            case R.id.action_exp:
                startActivity(new Intent(ListActivity.this, ListExpiryActivity.class));
                break;
            case R.id.action_batch:
                startActivity(new Intent(ListActivity.this, ListBatchActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    }






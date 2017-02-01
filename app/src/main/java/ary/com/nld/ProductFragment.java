package ary.com.nld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.Locale;

/**
 * Created by Ary on 20/01/2017.
 */

public class ProductFragment extends Fragment {

    ListView mList;
    EditText inputSearch;
    ArrayAdapter<String> searchAdapter;

    public ProductFragment() {
        // Required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.product_fragment, container, false);
        perform(view);

        return view;
    }

    public void perform(View view) {

        Firebase.setAndroidContext(getContext());
        mList = (ListView) view.findViewById(R.id.mListView);
        inputSearch = (EditText) view.findViewById(R.id.inputSearch);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("names-list");
        database.keepSynced(true);
        searchAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        database.addValueEventListener(new ValueEventListener() {
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
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("temp", temp);
                startActivity(intent);

            }
        });



        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String text = inputSearch.getText().toString().toLowerCase(Locale.getDefault());
                searchAdapter.getFilter().filter(text);
            }
        });
    }

    }


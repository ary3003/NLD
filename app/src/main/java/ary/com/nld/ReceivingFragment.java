package ary.com.nld;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Created by Ary on 20/01/2017.
 */

public class ReceivingFragment extends Fragment {

    ListView listView;
    EditText inputSearch;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;
    private LinearLayout linearLayout;
    private List<AutoCompleteTextView> myList_ac = new ArrayList<AutoCompleteTextView>();
    private List<EditText> myList_ed1 = new ArrayList<EditText>();
    private List<EditText> myList_ed2 = new ArrayList<EditText>();

    public ReceivingFragment(){
        // Required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.receiving_fragment, container, false);
        perform(view);

        return view;
    }

    public void perform(View view){

        Firebase.setAndroidContext(getContext());
        listView = (ListView)view.findViewById(R.id.mListView);
        inputSearch = (EditText)view.findViewById(R.id.inputSearch);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("date-list");
        databaseReference.keepSynced(true);
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String str_date = snapshot.getValue(String.class);
                    adapter.add(str_date);
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
                Intent intent = new Intent(getContext(), ReceivingDetailActivity.class);
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
                adapter.getFilter().filter(text);

            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewReceiving();
            }
        });

    }

    private void createNewReceiving() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        final String date = sdf.format(new Date());
        final String key2 = FirebaseDatabase.getInstance().getReference().child("receiving").child(date).push().getKey();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View getListItemView2 = layoutInflater.inflate(R.layout.dialog_receive_item, null);
        linearLayout = (LinearLayout) getListItemView2.findViewById(R.id.linearLayout);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setView(getListItemView2);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();


        // stockists autocomplete start

        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        final DatabaseReference db_ref = db.child("stockists-list");
        db_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String stockists = snapshot.getValue(String.class);
                    arrayAdapter2.add(stockists);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final AutoCompleteTextView ac_stockists = (AutoCompleteTextView) getListItemView2.findViewById(R.id.autoCompleteTextView2);
        ac_stockists.setAdapter(arrayAdapter2);
        ac_stockists.setThreshold(1);

        // stockists autocomplete ends

        // Product list autocomplete start

        final ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        final DatabaseReference db_ref2 = db.child("names-list");
        db_ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String suggestions = snapshot.getValue(String.class);
                    autoCompleteAdapter.add(suggestions);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final AutoCompleteTextView product = (AutoCompleteTextView) getListItemView2.findViewById(R.id.autoCompleteTextView3);
        product.setAdapter(autoCompleteAdapter);
        product.setThreshold(1);

        // product list autocomplete stop

        final EditText quantity = (EditText) getListItemView2.findViewById(R.id.editText);
        final EditText ed_mrp = (EditText) getListItemView2.findViewById(R.id.editText3);

        alertDialog
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final String str_product = product.getText().toString().toUpperCase().trim();
                        final String str_mrp = ed_mrp.getText().toString().toUpperCase().trim();
                        final String str_stockist = ac_stockists.getText().toString().toUpperCase().trim();
                        final String str_quantity = quantity.getText().toString().toUpperCase().trim();
                        ReceivingProduct receivingProduct = new ReceivingProduct(str_stockist, str_product, str_quantity);
                        Map<String, Object> items = receivingProduct.toMap();
                        Map<String, Object> child_updates = new HashMap<>();
                        Map<String, Object> child_updates2 = new HashMap<>();
                        Map<String, Object> child_updates3 = new HashMap<>();
                        Map<String, Object> child_updates4 = new HashMap<>();
                        Map<String, Object> child_updates5 = new HashMap<>();
                        Map<String, Object> child_updates6 = new HashMap<>();
                        child_updates.put("/receiving/" + date + "/" + key2, items);
                        child_updates2.put(key2, date);
                        child_updates3.put(key2, str_product);
                        child_updates4.put(key2, str_stockist);






                        if (TextUtils.isEmpty(str_stockist) || TextUtils.isEmpty(str_product) || TextUtils.isEmpty(str_quantity) || TextUtils.isEmpty(str_mrp)) {
                            Toast.makeText(getActivity(), "Please fill all details.", Toast.LENGTH_SHORT).show();
                            return;
                        }



                        FirebaseDatabase.getInstance().getReference().updateChildren(child_updates);
                        FirebaseDatabase.getInstance().getReference().child("names-list").updateChildren(child_updates3);
                        FirebaseDatabase.getInstance().getReference().child("stockists-list").updateChildren(child_updates4);
                        FirebaseDatabase.getInstance().getReference().child("date-list").updateChildren(child_updates2);
                        FirebaseDatabase.getInstance().getReference().child("receiving-date").child(date).updateChildren(child_updates4);
                        FirebaseDatabase.getInstance().getReference().child("date-wise-sorting").child(date).child(str_stockist).updateChildren(child_updates3);




                        String[] strings_ac = new String[myList_ac.size()];
                        String[] strings_ed_mrp = new String[myList_ed1.size()];
                        String[] strings_ed_quan = new String[myList_ed2.size()];
                        for (int j = 0; j < myList_ac.size(); j++){
                            strings_ac[j] = myList_ac.get(j).getText().toString();
                            strings_ed_mrp[j] = myList_ed1.get(j).getText().toString();
                            strings_ed_quan[j] = myList_ed2.get(j).getText().toString();


                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create()
                .show();


    }

    }













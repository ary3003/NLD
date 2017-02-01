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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Ary on 20/01/2017.
 */

public class BillFragment extends Fragment {

    ListView listView;
    EditText ed_inputSearch;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;

    public BillFragment(){
        // Required Empty Constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.bill_fragment, container, false);
        perform(view);

        return view;
    }

    public void perform(View view) {

        Firebase.setAndroidContext(getContext());
        listView = (ListView) view.findViewById(R.id.mListView);
        ed_inputSearch = (EditText) view.findViewById(R.id.inputSearch);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("bill-list");
        databaseReference.keepSynced(true);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1);
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
                Intent intent = new Intent(getContext(), BillDetailActivity.class);
                Intent intent1 = new Intent(getContext(), BillProductDetail.class);
                intent1.putExtra("temp1", temp);
                intent.putExtra("temp", temp);
                startActivity(intent);
            }
        });

        ed_inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {



            }

            @Override
            public void afterTextChanged(Editable editable) {

                String text = ed_inputSearch.getText().toString().toLowerCase(Locale.getDefault());
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

        final String key = FirebaseDatabase.getInstance().getReference().child("products").push().getKey();
        LayoutInflater li = LayoutInflater.from(getContext());
        View getListItemView = li.inflate(R.layout.dialog_get_list_item, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(getListItemView);


        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // Product list autocomplete start

        final ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        final DatabaseReference dbRef = database.child("names-list");
        dbRef.addValueEventListener(new ValueEventListener() {
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

        final AutoCompleteTextView userInput = (AutoCompleteTextView) getListItemView.findViewById(R.id.autoCompleteTextView);
        userInput.setAdapter(autoCompleteAdapter);
        userInput.setThreshold(1);

        // product list autocomplete stop

        // stockists autocomplete start

        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        final DatabaseReference dbRef4 = database.child("stockists-list");
        dbRef4.addValueEventListener(new ValueEventListener() {
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

        final AutoCompleteTextView stockists = (AutoCompleteTextView) getListItemView.findViewById(R.id.autoCompleteTextView4);
        stockists.setAdapter(arrayAdapter2);
        stockists.setThreshold(1);

        // stockists autocomplete ends

        // batch autocomplete starts

        final ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        final DatabaseReference dbRef5 = database.child("batch-list");
        dbRef5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String str_batch = snapshot.getValue(String.class);
                    arrayAdapter3.add(str_batch);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final AutoCompleteTextView batch = (AutoCompleteTextView) getListItemView.findViewById(R.id.autoCompleteTextView5);
        batch.setAdapter(arrayAdapter3);
        batch.setThreshold(1);

        // bill no. autocomplete start

        final ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        final DatabaseReference dbRef6 = database.child("bill-list");
        dbRef6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String str_batch = snapshot.getValue(String.class);
                    arrayAdapter4.add(str_batch);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final AutoCompleteTextView bill = (AutoCompleteTextView) getListItemView.findViewById(R.id.autoCompleteTextView6);
        bill.setAdapter(arrayAdapter4);
        bill.setThreshold(1);

        // bill no. autocomplete ends

        final EditText trade = (EditText) getListItemView.findViewById(R.id.editText2);
        final EditText mrp = (EditText) getListItemView.findViewById(R.id.editText4);
        final EditText exp = (EditText) getListItemView.findViewById(R.id.editText5);
        final EditText quantity = (EditText) getListItemView.findViewById(R.id.editText6);

        exp.addTextChangedListener(new TextWatcher() {

            private  static final int TOTAL_SYMBOLS = 5;
            private static final int TOTAL_DIGITS = 4;
            private static final int DIVIDER_MODULE = 3;
            private static final int DIVIDER_POSITION = DIVIDER_MODULE - 1;
            private static final char DIVIDER = '-' ;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isInputCorrect(editable, TOTAL_SYMBOLS, DIVIDER_MODULE,DIVIDER)){
                    editable.replace(0, editable.length(), buildCorrectString(getDigitArray(editable, TOTAL_DIGITS), DIVIDER_POSITION, DIVIDER));

                }

            }

            private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider){
                boolean isCorrect = s.length() <= totalSymbols;
                for (int i = 0; i < s.length(); i++){
                    if (i > 0 && (i + 1) % dividerModulo == 0){
                        isCorrect &= divider == s.charAt(i);
                    }
                    else {
                        isCorrect &= Character.isDigit(s.charAt(i));
                    }
                }
                return isCorrect;
            }

            private String buildCorrectString(char[] digit, int dividerPosition, char divider){
                final StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < digit.length; i++){
                    if (digit[i] != 0){
                        formatted.append(digit[i]);
                        if ((i > 0) && (i < (digit.length - 1)) && (((i + 1) % dividerPosition) == 0)){
                            formatted.append(divider);
                        }
                    }
                }
                return formatted.toString();
            }

            private char[] getDigitArray(final Editable s, final int size){
                char[] digits = new char[size];
                int index = 0;
                for (int i = 0; i < s.length() && index < size; i++){
                    char current = s.charAt(i);
                    if (Character.isDigit(current)){
                        digits[index] = current;
                        index++;
                    }
                }
                return digits;
            }
        });


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        final String newProduct = userInput.getText().toString().toUpperCase().trim();
                        final String MRP = mrp.getText().toString();
                        final Long newMRP = Long.parseLong(MRP);
                        final String newEXP = exp.getText().toString().toUpperCase().trim();

                        final String newStockist = stockists.getText().toString().toUpperCase().trim();
                        final String newBatch = batch.getText().toString().toUpperCase().trim();
                        final String Trade = trade.getText().toString().trim();
                        final Long newTrade = Long.parseLong(Trade);

                        final String newBill = bill.getText().toString().toUpperCase().trim();
                        final String newQuantity = quantity.getText().toString().toUpperCase().trim();

                        AddingNewProduct addingNewProduct = new AddingNewProduct(newProduct, newMRP, newEXP, newStockist, newBatch, newTrade, newBill, newQuantity );
                        Map<String, Object> listItemValues = addingNewProduct.toMap();
                        Map<String, Object> childUpdates = new HashMap<>();
                        Map<String, Object> childUpdates2 = new HashMap<>();
                        Map<String, Object> childUpdates3 = new HashMap<>();
                        Map<String, Object> childUpdates4 = new HashMap<>();
                        Map<String, Object> childUpdates5 = new HashMap<>();
                        Map<String, Object> childUpdates6 = new HashMap<>();
                        Map<String, Object> childUpdates7 = new HashMap<>();
                        Map<String, Object> childUpdates8 = new HashMap<>();
                        Map<String, Object> childUpdates9 = new HashMap<>();
                        childUpdates.put("/products/" + key, listItemValues);
                        childUpdates2.put(key, newProduct);
                        childUpdates3.put(key, newStockist);
                        childUpdates4.put(key, newEXP);
                        childUpdates5.put(key, newMRP);
                        childUpdates6.put(key, newBatch);
                        childUpdates7.put(key, newTrade);
                        childUpdates8.put(key, newBill);
                        childUpdates9.put(key, newQuantity);


                        if (TextUtils.isEmpty(newProduct)  || TextUtils.isEmpty(newStockist)) {
                            Toast.makeText(getContext(), "Please fill all details.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        FirebaseDatabase.getInstance().getReference().updateChildren(childUpdates);
                        FirebaseDatabase.getInstance().getReference().child("names-list").updateChildren(childUpdates2);
                        FirebaseDatabase.getInstance().getReference().child("stockists-list").updateChildren(childUpdates3);
                        FirebaseDatabase.getInstance().getReference().child("expiry-list").updateChildren(childUpdates4);
                        FirebaseDatabase.getInstance().getReference().child("batch-list").updateChildren(childUpdates6);
                        FirebaseDatabase.getInstance().getReference().child("mrp-list").updateChildren(childUpdates5);
                        FirebaseDatabase.getInstance().getReference().child("trade-list").updateChildren(childUpdates7);
                        FirebaseDatabase.getInstance().getReference().child("bill-list").updateChildren(childUpdates8);
                        FirebaseDatabase.getInstance().getReference().child("bill-product-list").child(newBill).updateChildren(childUpdates2);
                        Toast.makeText(getContext(), "Product Successfully Added.", Toast.LENGTH_SHORT).show();


                        FirebaseDatabase.getInstance().getReference().child("productNames").child(newProduct).updateChildren(listItemValues);
                        FirebaseDatabase.getInstance().getReference().child("stockists").child(newStockist).updateChildren(childUpdates2);
                        FirebaseDatabase.getInstance().getReference().child("expiry").child(newEXP).updateChildren(childUpdates2);
                        FirebaseDatabase.getInstance().getReference().child("batch").child(newBatch).updateChildren(childUpdates2);


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


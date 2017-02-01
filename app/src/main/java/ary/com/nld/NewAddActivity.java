package ary.com.nld;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static ary.com.nld.R.id.autoCompleteTextView;
import static ary.com.nld.R.id.editText;
import static ary.com.nld.R.id.editText2;

public class NewAddActivity extends AppCompatActivity {

    private TextView textView;
    AutoCompleteTextView autoCompleteTextView;
    EditText editText, editText2;
    List<AutoCompleteTextView> myList_ac = new ArrayList<AutoCompleteTextView>();
    List<EditText> myList_ed1 = new ArrayList<EditText>();
    List<EditText> myList_ed2 = new ArrayList<EditText>();
    RelativeLayout RelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar11);
        setSupportActionBar(toolbar);
        setTitle("Add a new receiving");


        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_add.setOnClickListener(onClick());

        RelativeLayout = (android.widget.RelativeLayout) findViewById(R.id.content_home);

    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoCompleteTextView = new AutoCompleteTextView(NewAddActivity.this);
                editText = new EditText(NewAddActivity.this);
                editText2 = new EditText(NewAddActivity.this);
                autoCompleteTextView.setId(autoCompleteTextView.generateViewId());
                editText.setId(editText.generateViewId());
                editText2.setId(editText2.generateViewId());
                Integer int_ac = autoCompleteTextView.getId();
                Integer int_ed1 = editText.getId();
                Integer int_ed2 = editText2.getId();
                autoCompleteTextView.setPadding(15, 15, 15, 15);
                autoCompleteTextView.setHeight(90);
                autoCompleteTextView.setWidth(90);
                editText.setPadding(15, 15, 15, 15);
                editText.setHeight(90);
                editText.setWidth(90);
                editText2.setPadding(15, 15, 15, 15);
                editText2.setHeight(90);
                editText2.setWidth(90);

                autoCompleteTextView.setHint("Product Name");
                editText.setHint("Quantity");
                editText2.setHint("MRP");
                myList_ac.add(autoCompleteTextView);
                myList_ed1.add(editText);
                myList_ed2.add(editText2);

                RelativeLayout.addView(autoCompleteTextView);
                RelativeLayout.addView(editText);
                RelativeLayout.addView(editText2);

            }

        };
    }
}

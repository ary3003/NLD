package ary.com.nld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Ary on 22/01/2017.
 */

public class SingleProductView extends Activity {
    TextView txtproduct;
    TextView txtmrp;
    TextView txttrade;
    TextView txtexp;
    TextView txtbatch;

    String product;
    String mrp;
    String trade;
    String exp;
    String batch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);

        Intent i = getIntent();
        product = i.getStringExtra("product");
        mrp = i.getStringExtra("mrp");
        trade = i.getStringExtra("trade");
        exp = i.getStringExtra("exp");
        batch = i.getStringExtra("batch");

        txtproduct = (TextView) findViewById(R.id.product);
        txtmrp = (TextView) findViewById(R.id.mrp);
        txttrade = (TextView) findViewById(R.id.trade);
        txtexp = (TextView) findViewById(R.id.exp);
        txtbatch = (TextView) findViewById(R.id.batch);

        txtproduct.setText(product);
        txtmrp.setText(mrp);
        txttrade.setText(trade);
        txtexp.setText(exp);
        txtbatch.setText(batch);


    }
}

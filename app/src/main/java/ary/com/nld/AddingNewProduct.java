package ary.com.nld;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ary on 29/12/2016.
 */
@IgnoreExtraProperties
public class AddingNewProduct {
    public String name;
    public Long mrp;
    public String exp;
    public String stockist;
    public String batch;
    public Long trade;
    public String bill;
    public String quantity;

    public String getName() { return name; }


    @Override
    public String toString() { return this.quantity + "\n" + this.name + "\n" + this.stockist + "\n" + this.batch + "\n" + this.bill + "\n" + this.exp;
    }

    public Long getMrp() { return mrp; }
    public String getExp() { return exp; }
    public String getStockist() { return stockist; }
    public String getBatch() { return batch; }
    public Long getTrade() { return trade; }
    public String getBill() { return bill; }
    public String getQuantity() { return quantity; }

    public AddingNewProduct(){
        // Intentionally left blank required for DataSnapshot.getValue(AddingNewProduct.class)
    }

    public AddingNewProduct(String name, Long mrp, String exp, String stockist, String batch, Long trade, String bill, String quantity) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        this.name = name;
        this.mrp = mrp;
        this.stockist = stockist;
        this.exp = exp;
        this.batch = batch;
        this.trade = trade;
        this.bill = bill;
        this.quantity = quantity;



    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("mrp", mrp);
        result.put("exp", exp);
        result.put("stockist", stockist);
        result.put("batch", batch);
        result.put("trade", trade);
        result.put("bill number", bill);
        result.put("quantity", quantity);
        return result;
    }

}




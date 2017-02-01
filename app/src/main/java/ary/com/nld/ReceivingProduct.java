package ary.com.nld;

import com.google.firebase.database.Exclude;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ary on 16/01/2017.
 */

public class ReceivingProduct {
    public String stockist;
    public String productName;
    public String quantity;
    public String receivingDate;

    public String getStockist() { return stockist; }
    public String getProductName() { return productName; }
    public String getQuantity() { return quantity; }
    public String getReceivingDate() { return receivingDate; }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setStockist(String stockist) {
        this.stockist = stockist;
    }

    public void setReceivingDate(String receivingDate) {
        this.receivingDate = receivingDate;
    }

    @Override
    public  String toString() {
        return this.stockist + "\n" + this.productName + "\n" + this.quantity + "\n" + this.receivingDate;
    }

    public ReceivingProduct(){

    }

    public ReceivingProduct(String stockist, String productName, String quantity){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        this.receivingDate = sdf.format(new Date());
        this.stockist = stockist;
        this.productName = productName;
        this.quantity = quantity;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("stockist", stockist);
        result.put("product-name", productName);
        result.put("quantity", quantity);
        result.put("receiving-date", receivingDate);
        return result;
    }
}

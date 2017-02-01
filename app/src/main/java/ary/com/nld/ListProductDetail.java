package ary.com.nld;

/**
 * Created by Ary on 22/01/2017.
 */

public class ListProductDetail {
    private String product;
    private String mrp;
    private String trade;
    private String exp;
    private String batch;

    public ListProductDetail(String product, String mrp, String trade, String exp, String batch){
        this.product = product;
        this.mrp = mrp;
        this.trade = trade;
        this.exp = exp ;
        this.batch = batch;
    }

    public String getProduct(){
        return this.product;
    }
    public String getMrp(){
        return this.mrp;
    }

    public String getTrade(){
        return this.trade;
    }

    public String getExp(){
        return exp;
    }

    public String getBatch(){
       return this.batch;
    }
}

package ary.com.nld;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ary on 22/01/2017.
 */

public class ListProductAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<ListProductDetail> listProductDetails = null;
    private ArrayList<ListProductDetail> arrayList;

    public ListProductAdapter(Context context,List<ListProductDetail> listProductDetails){
        mContext = context;
        this.listProductDetails = listProductDetails;
        inflater = LayoutInflater.from(context);
        this.arrayList = new ArrayList<ListProductDetail>();
        this.arrayList.addAll(listProductDetails);

    }

    public class ViewHolder{
        TextView product;
        TextView mrp;
        TextView trade;
        TextView exp;
        TextView batch;


    }

    @Override
    public int getCount(){
        return listProductDetails.size();
    }

    @Override
    public ListProductDetail getItem(int position){
        return listProductDetails.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_listproduct_item, null);
            holder.product = (TextView) view.findViewById(R.id.product);
            holder.mrp = (TextView) view.findViewById(R.id.mrp);
            holder.trade = (TextView) view.findViewById(R.id.trade);
            holder.exp = (TextView) view.findViewById(R.id.exp);
            holder.batch = (TextView) view.findViewById(R.id.batch);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.product.setText(listProductDetails.get(position).getProduct());
        holder.mrp.setText(listProductDetails.get(position).getMrp());
        holder.trade.setText(listProductDetails.get(position).getTrade());
        holder.exp.setText(listProductDetails.get(position).getExp());
        holder.batch.setText(listProductDetails.get(position).getBatch());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SingleProductView.class);
                intent.putExtra("product", (listProductDetails.get(position).getProduct()));
                intent.putExtra("mrp", (listProductDetails.get(position).getMrp()));
                intent.putExtra("trade", (listProductDetails.get(position).getTrade()));
                intent.putExtra("exp", (listProductDetails.get(position).getExp()));
                intent.putExtra("batch", (listProductDetails.get(position).getBatch()));
                mContext.startActivity(intent);
            }
        });
        return view;
    }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            listProductDetails.clear();
            if (charText.length() == 0) {
                listProductDetails.addAll(arrayList);
            }
            else
            {
                for (ListProductDetail wp : arrayList)
                {
                    if (wp.getProduct().toLowerCase(Locale.getDefault()).contains(charText))
                    {
                        listProductDetails.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }
}

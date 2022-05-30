package com.example.nyayur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder>  {
    Context context;
    ArrayList<ProductModel> productModels;

    public TransactionAdapter(Context context, ArrayList<ProductModel> productModels){
        this.context = context;
        this.productModels = productModels;
    }

    public void addItem(ProductModel newModel){
        productModels.add(newModel);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TransactionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.transaction_row,parent,false);
        return new TransactionAdapter.MyViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.MyViewHolder holder, int position) {

        Context context = holder.thumbnail.getContext();
        int id = context.getResources().getIdentifier(productModels.get(position).thumbnail, "drawable", context.getPackageName());
        holder.thumbnail.setImageResource(id);
        holder.tittle.setText(productModels.get(position).nama);
        holder.harga.setText("Rp."+Integer.toString(productModels.get(position).harga));
        holder.qty.setText("Qty : x1");

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView tittle, harga, qty;
        private TransactionAdapter adapter;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.product);
            tittle = itemView.findViewById(R.id.productName);
            harga = itemView.findViewById(R.id.harga);
            qty = itemView.findViewById(R.id.qty);

        }
        public TransactionAdapter.MyViewHolder linkAdapter(TransactionAdapter adapter){
            this.adapter = adapter;
            return this;
        }
    }
}

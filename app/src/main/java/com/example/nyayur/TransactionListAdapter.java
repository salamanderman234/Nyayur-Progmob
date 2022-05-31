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

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.MyViewHolder>  {

    Context context;
    ArrayList<TransactionModel> transactionModels;

    public TransactionListAdapter(Context context, ArrayList<TransactionModel> transactionModels) {
        this.context = context;
        this.transactionModels = transactionModels;
    }

    @NonNull
    @Override
    public TransactionListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.transaction_list_row,parent,false);
        return new TransactionListAdapter.MyViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tanggal.setText("Transaksi pada " + transactionModels.get(position).tanggal);
        holder.harga.setText("Rp."+Integer.toString(transactionModels.get(position).total));
        holder.status.setText(transactionModels.get(position).status);
    }
    public void addItem(TransactionModel newModel){
        transactionModels.add(newModel);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tanggal, status, harga;
        private TransactionListAdapter adapter;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tanggal);
            harga = itemView.findViewById(R.id.harga);
            status = itemView.findViewById(R.id.status);

        }
        public TransactionListAdapter.MyViewHolder linkAdapter(TransactionListAdapter adapter){
            this.adapter = adapter;
            return this;
        }
    }
}

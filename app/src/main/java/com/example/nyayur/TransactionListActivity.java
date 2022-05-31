package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class TransactionListActivity extends AppCompatActivity {
    ArrayList<TransactionModel> transactionList = new ArrayList<>();
    TransactionListAdapter adapter;
    Bundle extras;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        extras = getIntent().getExtras();
        if(extras != null){
            String tanggal = extras.getString("tanggal");
            String status = extras.getString("status");
            int total = Integer.parseInt(extras.getString("total"));

            adapter.addItem(new TransactionModel(tanggal,status,total));

            getIntent().removeExtra("tanggal");
            getIntent().removeExtra("status");
            getIntent().removeExtra("total");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new TransactionListAdapter(this,transactionList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}

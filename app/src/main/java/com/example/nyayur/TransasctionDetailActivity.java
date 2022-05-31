package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TransasctionDetailActivity extends AppCompatActivity {

    ArrayList<ProductModel> productModels = new ArrayList<>();
    TransactionAdapter adapter;
    Bundle extras;
    TextView total;
    int totalHarga;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        extras = getIntent().getExtras();
        if(extras != null && extras.getSerializable("data") != null){
            ArrayList<ProductModel> data = (ArrayList<ProductModel>) extras.getSerializable("data");

            for(int i=0 ; i<data.size();i++){
                adapter.addItem(data.get(i));
                totalHarga += productModels.get(i).harga;
            }

            total = findViewById(R.id.total);
            total.setText("Rp."+Integer.toString(totalHarga));

            getIntent().removeExtra("data");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transasction_detail);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new TransactionAdapter(this,productModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void transactionSave(View view){
        Intent intent = new Intent(this,TransactionListActivity.class);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        intent.putExtra("tanggal",currentDate);
        intent.putExtra("total",Integer.toString(totalHarga));
        intent.putExtra("status","Berhasil");


        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}

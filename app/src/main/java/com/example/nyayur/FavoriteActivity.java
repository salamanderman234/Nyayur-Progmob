package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    ArrayList<ProductModel> productModels = new ArrayList<>();
    ProductRecycleViewAdapter adapter;
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
            String image = extras.getString("image");
            String nama = extras.getString("nama");
            String pembelian = extras.getString("pembelian");
            String kondisi = extras.getString("kondisi");
            String deskripsi = extras.getString("deskripsi");
            int harga = Integer.parseInt(extras.getString("harga"));
            String stock= extras.getString("stock");

            adapter.addItem(new ProductModel(
                image,
                nama,
                pembelian,
                kondisi,
                deskripsi,
                harga,
                stock
            ));

            getIntent().removeExtra("image");
            getIntent().removeExtra("nama");
            getIntent().removeExtra("pembelian");
            getIntent().removeExtra("kondisi");
            getIntent().removeExtra("deskripsi");
            getIntent().removeExtra("harga");
            getIntent().removeExtra("stock");


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new ProductRecycleViewAdapter(this,productModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }


}

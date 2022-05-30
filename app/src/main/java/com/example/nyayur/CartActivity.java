package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ArrayList<ProductModel> productModels = new ArrayList<>();
    Bundle extras;
    ProductRecycleViewAdapter adapter;

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
            Toast.makeText(getApplicationContext(),extras.getString("name"),Toast.LENGTH_LONG);

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
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        setUpProductModels();

        adapter = new ProductRecycleViewAdapter(this,productModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    private void setUpProductModels(){
        String[] productNames = getResources().getStringArray(R.array.product_name_strings);
        String[] productDeskripsis = getResources().getStringArray(R.array.product_deskripsi_strings);
        String[] productHargas = getResources().getStringArray(R.array.product_price_strings);
        String[] productPembelians = getResources().getStringArray(R.array.product_pembelian_strings);
        String[] productThumbnails = getResources().getStringArray(R.array.product_thumbnail_strings);
        String[] productStock = getResources().getStringArray(R.array.product_stock_strings);

        for (int i=0;i< productNames.length;i++){
            productModels.add(new ProductModel(
                productThumbnails[i],
                productNames[i],
                productPembelians[i],
                "Segar",
                productDeskripsis[i],
                Integer.parseInt(productHargas[i]),
                productStock[i]
                )
            );
        }

    }
}

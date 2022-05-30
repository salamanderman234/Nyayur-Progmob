package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailActivity extends AppCompatActivity {
    TextView price, name, stockValue, pembelianValue, kondisiValue, deskripsi;
    Bundle extras;
    ImageView gambar;

    @Override
    protected void onResume() {
        super.onResume();
        extras = getIntent().getExtras();
        if(extras != null){
            price.setText(extras.getString("price"));
            name.setText(extras.getString("tittle"));
            stockValue.setText(extras.getString("stock"));
            pembelianValue.setText(extras.getString("pembelian"));
            kondisiValue.setText(extras.getString("kondisi"));
            deskripsi.setText(extras.getString("deskripsi"));

            Context context = gambar.getContext();
            int id = context.getResources().getIdentifier(extras.getString("image"), "drawable", context.getPackageName());
            gambar.setImageResource(id);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        price = (TextView) findViewById(R.id.price);
        name = (TextView) findViewById(R.id.name);
        stockValue = (TextView) findViewById(R.id.stockValue);
        pembelianValue = (TextView) findViewById(R.id.pembelianValue);
        kondisiValue = (TextView) findViewById(R.id.kondisiValue);
        deskripsi = (TextView) findViewById(R.id.deskripsi);
        gambar = (ImageView) findViewById(R.id.imageView3);


    }
    public void favorite(View view){
        Intent intent = new Intent(ProductDetailActivity.this,FavoriteActivity.class);
        intent.putExtra("nama",extras.getString("tittle"));
        intent.putExtra("harga","20000");
        intent.putExtra("stock",extras.getString("stock"));
        intent.putExtra("pembelian",extras.getString("pembelian"));
        intent.putExtra("image",extras.getString("image"));
        intent.putExtra("deskripsi",extras.getString("deskipsi"));
        intent.putExtra("kondisi",extras.getString("kondisi"));
        Log.d("productModels",extras.getString("tittle"));

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void wishlist(View view){
        Intent intent = new Intent(ProductDetailActivity.this,WishListActivity.class);
        intent.putExtra("nama",extras.getString("tittle"));
        intent.putExtra("harga","20000");
        intent.putExtra("stock",extras.getString("stock"));
        intent.putExtra("pembelian",extras.getString("pembelian"));
        intent.putExtra("image",extras.getString("image"));
        intent.putExtra("deskripsi",extras.getString("deskipsi"));
        intent.putExtra("kondisi",extras.getString("kondisi"));
        Log.d("productModels",extras.getString("tittle"));

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void cart(View view){
        Intent intent = new Intent(getApplicationContext(),CartActivity.class);
        intent.putExtra("nama",extras.getString("tittle"));
        intent.putExtra("harga","20000");
        intent.putExtra("stock",extras.getString("stock"));
        intent.putExtra("pembelian",extras.getString("pembelian"));
        intent.putExtra("image",extras.getString("image"));
        intent.putExtra("deskripsi",extras.getString("deskipsi"));
        intent.putExtra("kondisi",extras.getString("kondisi"));
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void share(View view) {
        String txt = extras.getString("tittle");
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Bagikan Ke")
            .setText(txt)
            .startChooser();
    }

    public void transaction(View view){
        Intent intent = new Intent(getApplicationContext(),TransasctionDetailActivity.class);
        intent.putExtra("nama",extras.getString("tittle"));
        intent.putExtra("harga","20000");
        intent.putExtra("stock",extras.getString("stock"));
        intent.putExtra("pembelian",extras.getString("pembelian"));
        intent.putExtra("image",extras.getString("image"));
        intent.putExtra("deskripsi",extras.getString("deskipsi"));
        intent.putExtra("kondisi",extras.getString("kondisi"));
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void finish(View view){
        finish();
    }
}

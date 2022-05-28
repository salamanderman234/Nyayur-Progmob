package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailActivity extends AppCompatActivity {
    TextView price, name, stockValue, pembelianValue, kondisiValue, deskripsi;
    ImageView gambar;
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

        Bundle extras = getIntent().getExtras();
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
    public void share(View view) {
        String txt = "Buah Kiwi Segar";
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Bagikan Ke")
            .setText(txt)
            .startChooser();
    }
    public void finish(View view){
        finish();
    }
}

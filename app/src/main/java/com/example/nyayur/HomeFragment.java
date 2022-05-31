package com.example.nyayur;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ArrayList<ProductModel> productModels = new ArrayList<>();
    ImageView vegetable, fruit, dapur;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public int[] products = {0,1,2,3};
    public String[][] detail_product = new String[4][6];

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vegetable = view.findViewById(R.id.vegetableIcon);
        fruit = view.findViewById(R.id.fruitIcon);
        dapur = view.findViewById(R.id.kebutuhanDapurIcon);
        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("search","Vegetable");
                startActivity(intent);
            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("search","Fruit");
                startActivity(intent);
            }
        });

        dapur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("search","Kebutuhan Dapur");
                startActivity(intent);
            }
        });


        TextView[] tittles =  {
            (TextView) view.findViewById(R.id.titleProduct1),
            (TextView) view.findViewById(R.id.titleProduct2),
            (TextView) view.findViewById(R.id.titleProduct3),
            (TextView) view.findViewById(R.id.titleProduct4)
        };
        ImageView[] images = {
            (ImageView) view.findViewById(R.id.imageProduct1),
            (ImageView) view.findViewById(R.id.imageProduct2),
            (ImageView) view.findViewById(R.id.imageProduct3),
            (ImageView) view.findViewById(R.id.imageProduct4),
        };
        TextView[] prices = {
            (TextView) view.findViewById(R.id.hargaProduct1),
            (TextView) view.findViewById(R.id.hargaProduct2),
            (TextView) view.findViewById(R.id.hargaProduct3),
            (TextView) view.findViewById(R.id.hargaProduct4),
        };
        setUpProductModels();

        for(int i=0;i<4;i++){
            tittles[i].setText(productModels.get(i).nama);
            prices[i].setText("Rp."+Integer.toString(productModels.get(i).harga));
            Context context = images[i].getContext();
            int id = context.getResources().getIdentifier(productModels.get(i).thumbnail, "drawable", context.getPackageName());
            images[i].setImageResource(id);

            tittles[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = Integer.parseInt(view.getTag().toString()) -1;

                    Intent intent = new Intent(getActivity(),ProductDetailActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("tittle",productModels.get(id).nama);
                    intent.putExtra("price",Integer.toString(productModels.get(id).harga));
                    intent.putExtra("pembelian",productModels.get(id).pembelian);
                    intent.putExtra("stock",productModels.get(id).stock);
                    intent.putExtra("kondisi",productModels.get(id).kondisi);
                    intent.putExtra("deskripsi",productModels.get(id).deskripsi);
                    intent.putExtra("image",productModels.get(id).thumbnail);


                    startActivity(intent);
                }
            });
        }

        MaterialToolbar toolbar = (MaterialToolbar) view.findViewById(R.id.topAppBar);
        ImageSlider imageSlider = (ImageSlider) view.findViewById(R.id.promoSlider);
        TextInputEditText search = (TextInputEditText) view.findViewById(R.id.searchBar);

        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.cart:
                    Intent intent = new Intent(getActivity(),CartActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    break;
            }
            return true;
        });
        search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    String keyword = search.getText().toString();

                    Intent intent = new Intent(getActivity(),RegisterActivity.class);
                    intent.putExtra("keyword",keyword);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });


        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://bit.ly/2YoJ77H",ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://bit.ly/2BteuF2",ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://bit.ly/3fLJf72",ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
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

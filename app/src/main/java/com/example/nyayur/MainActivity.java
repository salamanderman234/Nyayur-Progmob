package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nyayur.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding dan setting itemselected di navigation bawah
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ///////////////////////////////////////////////////////

        //saat home berjalan pertama kali
        replaceLayout(new HomeFragment(),null);
        /////////////////////////////////

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            email = extras.getString("email");
        }

        // set onselectlistener on navigation
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceLayout(new HomeFragment(),null);
                    break;
                case R.id.setting:
                    replaceLayout(new SettingFragment(),null);
                    break;
                case R.id.user:
                    replaceLayout(new ProfileFragment(),email);
                    break;
            }
            return true;
        });
        //////////////////////////////////////

        // set default selected item navigation
        binding.bottomNavigationView.setSelectedItemId(R.id.home);
        ///////////////////////////////////////
    }

    //mengganti layout dengan fragment yang spesifik
    public void replaceLayout(Fragment fragment,String extras){
        if(extras!=null){
            Bundle bundle = new Bundle();
            bundle.putString("email",extras);
            fragment.setArguments(bundle);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout,fragment);
        fragmentTransaction.commit();
    }


}

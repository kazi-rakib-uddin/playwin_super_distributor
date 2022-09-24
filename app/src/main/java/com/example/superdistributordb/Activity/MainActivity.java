package com.example.superdistributordb.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.R;
import com.example.superdistributordb.ViewModel.ApiResponse;
import com.example.superdistributordb.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public MenuItem item;
    private TextView txt_amount;
    private ApiResponse apiResponse;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {

        apiResponse = new ApiResponse(this);
        user = new User(this);

        getSupportActionBar().setTitle(user.getType());

        if (user.getType().equals("Super Distributor"))
        {
            binding.txtUser.setText("Add Distributor");
            binding.txtViewUser.setText("View Distributor");
        }
        else if (user.getType().equals("Distributor"))
        {
            binding.txtUser.setText("Add Retailer");
            binding.txtViewUser.setText("View Retailer");
        }
        else if (user.getType().equals("Retailer"))
        {
            binding.txtUser.setText("Add Users");
            binding.txtViewUser.setText("View Users");
        }


        binding.addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,AddUserActivity.class));
            }
        });



        binding.viewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user.getType().equals("Super Distributor"))
                {
                    startActivity(new Intent(MainActivity.this, ViewDistributorActivity.class));
                }
                else if (user.getType().equals("Distributor"))
                {
                    startActivity(new Intent(MainActivity.this,ViewRetailerActivity.class));
                }
                else if (user.getType().equals("Retailer"))
                {
                    startActivity(new Intent(MainActivity.this,ViewUserActivity.class));
                }

            }
        });


        binding.moneyRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,MoneyRequestActivity.class));
            }
        });

        binding.viewMoneyRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,ViewMoneyRequestActivity.class));
            }
        });


        binding.commissionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,CommissionActivity.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        item = menu.findItem(R.id.action_duty);
        MenuItemCompat.setActionView(item, R.layout.custom_wallet);
        LinearLayout notifcount = (LinearLayout) MenuItemCompat.getActionView(item);
        txt_amount = notifcount.findViewById(R.id.txt_amount);

        apiResponse.fetchWalletAmount(txt_amount);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.logout:
                user.remove();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finishAffinity();
                break;
        }

        return true;
    }
}
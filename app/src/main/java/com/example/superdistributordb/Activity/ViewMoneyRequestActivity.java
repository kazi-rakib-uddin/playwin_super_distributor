package com.example.superdistributordb.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.superdistributordb.Adapter.MoneyRequestAdapter;
import com.example.superdistributordb.Model.Money_Request_Model;
import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.R;
import com.example.superdistributordb.ViewModel.ApiResponse;
import com.example.superdistributordb.databinding.ActivityViewMoneyRequestBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewMoneyRequestActivity extends AppCompatActivity {

    private ActivityViewMoneyRequestBinding binding;
    private ApiResponse apiResponse;
    private User user;
    private List<Money_Request_Model> request_models = new ArrayList<>();
    private MoneyRequestAdapter adapter;
    public MenuItem item;
    public static TextView txt_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMoneyRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }


    private void initView() {

        apiResponse = new ApiResponse(this);
        user = new User(this);

        getSupportActionBar().setTitle("View Money Request");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.rvMoneyRequest.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        adapter = new MoneyRequestAdapter(this,request_models);
        binding.rvMoneyRequest.setAdapter(adapter);


        apiResponse.fetchMoneyRequest();
        apiResponse.getMoneyRequest().observe(this, new Observer<List<Money_Request_Model>>() {
            @Override
            public void onChanged(List<Money_Request_Model> money_request_models) {

                request_models = money_request_models;

                adapter.updateList(request_models);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_wallet, menu);
        item = menu.findItem(R.id.action_duty);
        MenuItemCompat.setActionView(item, R.layout.custom_wallet);
        LinearLayout notifcount = (LinearLayout) MenuItemCompat.getActionView(item);
        txt_amount = notifcount.findViewById(R.id.txt_amount);

        apiResponse.fetchWalletAmount(txt_amount);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(ViewMoneyRequestActivity.this,MainActivity.class));
                finishAffinity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

       startActivity(new Intent(ViewMoneyRequestActivity.this,MainActivity.class));
       finishAffinity();
    }
}
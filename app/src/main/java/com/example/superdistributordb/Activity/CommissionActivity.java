package com.example.superdistributordb.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;


import com.example.superdistributordb.Adapter.CommissionAdapter;
import com.example.superdistributordb.Model.Transaction_Model;
import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.R;
import com.example.superdistributordb.ViewModel.ApiResponse;
import com.example.superdistributordb.databinding.ActivityCommissionBinding;

import java.util.ArrayList;
import java.util.List;

public class CommissionActivity extends AppCompatActivity {

    private ActivityCommissionBinding binding;
    private ApiResponse apiResponse;
    private User user;
    private List<Transaction_Model> transaction_models = new ArrayList<>();
    private CommissionAdapter adapter;
    public MenuItem item;
    private TextView txt_amount;
    private String hold_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {

        apiResponse = new ApiResponse(this);
        user = new User(this);

        getSupportActionBar().setTitle("Withdraw Request");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.recyclerTransaction.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        adapter = new CommissionAdapter(this,transaction_models);
        binding.recyclerTransaction.setAdapter(adapter);

        binding.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String amount = binding.amount.getText().toString();

                if (amount.isEmpty())
                {
                    Toast.makeText(CommissionActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }
                else if (amount.equals("0"))
                {
                    Toast.makeText(CommissionActivity.this, "Invalid Amount", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(binding.amount.getText().toString()) < 500)
                {
                    Toast.makeText(CommissionActivity.this, "Minimum amount ₹500", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(txt_amount.getText().toString())< Integer.parseInt(binding.amount.getText().toString()))
                {
                    Toast.makeText(CommissionActivity.this, "Insufficient Amount", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    apiResponse.addWithdrawRequest(user.getRef_code(),amount,user.getType());
                }
            }
        });

        if (user.getType().equals("Retailer"))
        {
            hold_id = user.getRef_code();
        }
        else
        {
            hold_id = user.getUser_id();
        }

        apiResponse.fetchWithdrawTransaction(hold_id);
        apiResponse.getWithdrawTransaction().observe(this, new Observer<List<Transaction_Model>>() {
            @Override
            public void onChanged(List<Transaction_Model> transaction_modelsList) {

                transaction_models = transaction_modelsList;

                adapter.updateList(transaction_models);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_commission, menu);
        item = menu.findItem(R.id.action_duty);
        MenuItemCompat.setActionView(item, R.layout.custom_rupees);
        LinearLayout notifcount = (LinearLayout) MenuItemCompat.getActionView(item);
        txt_amount = notifcount.findViewById(R.id.txt_amount);

        apiResponse.fetchCommissionAmount(txt_amount);

        return true;
    }



    @Override
    public void onBackPressed() {

        finish();
    }

}
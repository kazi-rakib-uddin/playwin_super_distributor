package com.example.superdistributordb.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;


import com.example.superdistributordb.Adapter.TransactionAdapter;
import com.example.superdistributordb.Model.Transaction_Model;
import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.ViewModel.ApiResponse;
import com.example.superdistributordb.databinding.ActivityMoneyRequestBinding;

import java.util.ArrayList;
import java.util.List;

public class MoneyRequestActivity extends AppCompatActivity {

    private ActivityMoneyRequestBinding binding;
    private ApiResponse apiResponse;
    private User user;
    private List<Transaction_Model> transaction_models = new ArrayList<>();
    private TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoneyRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

    }

    private void initView() {

        apiResponse = new ApiResponse(this);
        user = new User(this);

        getSupportActionBar().setTitle("Money Request");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.recyclerTransaction.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        adapter = new TransactionAdapter(this,transaction_models);
        binding.recyclerTransaction.setAdapter(adapter);

        binding.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String amount = binding.amount.getText().toString();

                if (amount.isEmpty())
                {
                    Toast.makeText(MoneyRequestActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }
                else if (amount.equals("0"))
                {
                    Toast.makeText(MoneyRequestActivity.this, "Invalid Amount", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    apiResponse.addMoneyRequest(user.getUser_id(),amount,user.getType(), user.getUnder_by());
                }
            }
        });


        apiResponse.fetchSuperDistributorTransaction();
        apiResponse.getTransaction().observe(this, new Observer<List<Transaction_Model>>() {
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
    public void onBackPressed() {

        finish();
    }
}
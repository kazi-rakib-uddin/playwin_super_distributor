package com.example.superdistributordb.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;


import com.example.superdistributordb.Adapter.ViewDistributorAdpater;
import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.Model.View_User_Model;
import com.example.superdistributordb.ViewModel.ApiResponse;
import com.example.superdistributordb.databinding.ActivityViewDistributorBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewDistributorActivity extends AppCompatActivity {

    private ActivityViewDistributorBinding binding;
    private List<View_User_Model> user_models = new ArrayList<>();
    private ApiResponse apiResponse;
    private ViewDistributorAdpater adpater;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityViewDistributorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

    }

    private void initView() {

        apiResponse = new ApiResponse(this);
        user = new User(this);

        getSupportActionBar().setTitle("Distributor");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adpater = new ViewDistributorAdpater(this,user_models);
        binding.rvUser.setAdapter(adpater);

            apiResponse.fetchDistributor();

            apiResponse.getDistributor().observe(this, new Observer<List<View_User_Model>>() {
                @Override
                public void onChanged(List<View_User_Model> view_user_models) {

                    user_models = view_user_models;
                    if (user_models != null)
                    {
                        adpater.updateUserList(user_models);
                    }
                    else
                    {
                        Toast.makeText(ViewDistributorActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    }

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
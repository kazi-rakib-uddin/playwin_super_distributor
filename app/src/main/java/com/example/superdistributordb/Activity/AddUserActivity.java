package com.example.superdistributordb.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.ViewModel.ApiResponse;
import com.example.superdistributordb.databinding.ActivityAddUserBinding;

public class AddUserActivity extends AppCompatActivity {

    private ActivityAddUserBinding binding;
    private ApiResponse apiResponse;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

    }

    private void initView() {

        apiResponse = new ApiResponse(this);
        user = new User(this);

        if (user.getType().equals("Super Distributor"))
        {
            getSupportActionBar().setTitle("Add Distributor");
        }
        else if (user.getType().equals("Distributor"))
        {
            getSupportActionBar().setTitle("Add Retailer");
        }
        else if (user.getType().equals("Retailer"))
        {
            getSupportActionBar().setTitle("Add Users");
        }


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.name.getText().toString();
                String email = binding.email.getText().toString();
                String phone = binding.phoneNo.getText().toString();
                String password = binding.password.getText().toString();

                if (name.isEmpty())
                {
                    toastShort("Name is required");
                }
                else if (phone.isEmpty())
                {
                    toastShort("Phone Number is required");
                }
                else if (email.isEmpty())
                {
                    toastShort("Email is required");
                }
                else if (password.isEmpty())
                {
                    toastShort("Password is required");
                }
                else
                {
                    if (user.getType().equals("Super Distributor"))
                    {
                        apiResponse.addDistributor(name,email,phone,user.getUser_id(),password);
                    }
                    else if (user.getType().equals("Distributor"))
                    {
                        apiResponse.addRetailer(name,email,phone,user.getUser_id(),password);
                    }
                    else if (user.getType().equals("Retailer"))
                    {
                        apiResponse.addUser(name,email,phone,user.getRef_code(),password);
                    }

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

    private void toastShort(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
package com.example.superdistributordb.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.ViewModel.ApiResponse;
import com.example.superdistributordb.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private static final String[] TYPE = new String[] {
            "Super Distributor", "Distributor", "Retailer"
    };
    private ApiResponse apiResponse;
    public static String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (new User(this).getUser_id() != "")
        {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        initView();

    }

    private void initView() {

        apiResponse = new ApiResponse(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, TYPE);

        binding.spinnerType.setAdapter(adapter);

        binding.spinnerType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                type = (String) adapterView.getItemAtPosition(i);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type.equals(""))
                {
                    toastShort("Please Select Type");
                }
                else if (binding.phoneNo.getText().toString().isEmpty())
                {
                    toastShort("Phone Number is Required");
                }
                else if (binding.password.getText().toString().isEmpty())
                {
                    toastShort("Password is Required");
                }
                else
                {
                    apiResponse.login(binding.phoneNo.getText().toString(),binding.password.getText().toString(), type);

                }
                
            }
        });

    }

    private void toastShort(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
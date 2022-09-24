package com.example.superdistributordb.ViewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.superdistributordb.Activity.LoginActivity;
import com.example.superdistributordb.Activity.MainActivity;
import com.example.superdistributordb.ApiClient.ApiClient;
import com.example.superdistributordb.Interfaces.MyInterface;
import com.example.superdistributordb.Model.Money_Request_Model;
import com.example.superdistributordb.Model.Transaction_Model;
import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.Model.View_User_Model;
import com.example.superdistributordb.Utils.ProgressUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponse extends ViewModel {

    private MyInterface myInterface;
    private Context context;
    private User user;
    private MutableLiveData<List<View_User_Model>> modelList_distributor;
    private MutableLiveData<List<View_User_Model>> modelList_retailer;
    private MutableLiveData<List<View_User_Model>> modelList_user;
    private MutableLiveData<List<Transaction_Model>> modelList_tranaction;
    private MutableLiveData<List<Transaction_Model>> modelList_withdraw_tranaction;
    private MutableLiveData<List<Money_Request_Model>> modelList_money_request;

    public ApiResponse(Context context) {

        this.context = context;
        user = new User(context);
        myInterface = ApiClient.getApiClient().create(MyInterface.class);

        modelList_distributor = new MutableLiveData<>();
        modelList_retailer = new MutableLiveData<>();
        modelList_user = new MutableLiveData<>();
        modelList_tranaction = new MutableLiveData<>();
        modelList_money_request = new MutableLiveData<>();
        modelList_withdraw_tranaction = new MutableLiveData<>();

    }

    public LiveData<List<View_User_Model>> getDistributor()
    {
        return modelList_distributor;
    }
    public LiveData<List<View_User_Model>> getRetailer()
    {
        return modelList_retailer;
    }

    public LiveData<List<View_User_Model>> getUser()
    {
        return modelList_user;
    }

    public LiveData<List<Money_Request_Model>> getMoneyRequest()
    {
        return modelList_money_request;
    }

    public LiveData<List<Transaction_Model>> getTransaction()
    {
        return modelList_tranaction;
    }

    public LiveData<List<Transaction_Model>> getWithdrawTransaction()
    {
        return modelList_withdraw_tranaction;
    }

    public void login(String phone, String password, String type)
    {

        Call<String> call = myInterface.login(phone,password,type);
        ProgressUtils.showLoadingDialog(context);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());

                        if (jsonObject.getString("rec").equals("1"))
                        {
                            ProgressUtils.cancelLoading();
                            user.setUser_id(jsonObject.getString("id"));
                            user.setUnder_by(jsonObject.getString("under_by"));
                            user.setType(LoginActivity.type);

                            if (LoginActivity.type.equals("Retailer"))
                            {
                                user.setRef_code(jsonObject.getString("ref_code"));
                            }
                            else
                            {
                                user.setRef_code("");
                            }

                            context.startActivity(new Intent(context, MainActivity.class));
                            ((Activity)context).finish();
                        }
                        else
                        {
                            Toast.makeText(context, "Invalid Phone Number & Password", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }

                    } catch (JSONException e) {

                        Toast.makeText(context, "Somthing Went wrong", Toast.LENGTH_SHORT).show();
                        ProgressUtils.cancelLoading();
                    }
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    ProgressUtils.cancelLoading();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
                ProgressUtils.cancelLoading();
            }
        });
    }


    public void addDistributor(String name, String email, String phone, String under_by, String password)
    {

        Call<String> call = myInterface.add_distributor(name,email,phone,under_by,password);
        ProgressUtils.showLoadingDialog(context);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());

                        if (jsonObject.getString("rec").equals("1"))
                        {
                            Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }
                        else if (jsonObject.getString("rec").equals("2"))
                        {
                            Toast.makeText(context, "Distributor already excites", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }
                        else
                        {
                            Toast.makeText(context, "Not Add", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }

                    } catch (JSONException e) {

                        Toast.makeText(context, "Somthing Went wrong", Toast.LENGTH_SHORT).show();
                        ProgressUtils.cancelLoading();
                    }
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    ProgressUtils.cancelLoading();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
                ProgressUtils.cancelLoading();
            }
        });
    }

    public void addRetailer(String name, String email, String phone, String under_by, String password)
    {

        Call<String> call = myInterface.add_retailer(name,email,phone,under_by,password);
        ProgressUtils.showLoadingDialog(context);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());

                        if (jsonObject.getString("rec").equals("1"))
                        {
                            Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }
                        else if (jsonObject.getString("rec").equals("2"))
                        {
                            Toast.makeText(context, "Retailer already excites", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }
                        else
                        {
                            Toast.makeText(context, "Not Add", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }

                    } catch (JSONException e) {

                        Toast.makeText(context, "Somthing Went wrong", Toast.LENGTH_SHORT).show();
                        ProgressUtils.cancelLoading();
                    }
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    ProgressUtils.cancelLoading();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
                ProgressUtils.cancelLoading();
            }
        });
    }

    public void addUser(String name, String email, String phone, String under_by, String password)
    {

        Call<String> call = myInterface.add_user(name,email,phone,under_by,password);
        ProgressUtils.showLoadingDialog(context);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());

                        if (jsonObject.getString("rec").equals("1"))
                        {
                            Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }
                        else if (jsonObject.getString("rec").equals("2"))
                        {
                            Toast.makeText(context, "User already excites", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }
                        else
                        {
                            Toast.makeText(context, "Not Add", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }

                    } catch (JSONException e) {

                        Toast.makeText(context, "Somthing Went wrong", Toast.LENGTH_SHORT).show();
                        ProgressUtils.cancelLoading();
                    }
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    ProgressUtils.cancelLoading();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
                ProgressUtils.cancelLoading();
            }
        });
    }

    public void fetchDistributor()
    {
        Call<List<View_User_Model>> call = myInterface.fetch_distributor(user.getUser_id());
        call.enqueue(new Callback<List<View_User_Model>>() {
            @Override
            public void onResponse(Call<List<View_User_Model>> call, Response<List<View_User_Model>> response) {

                if (response.isSuccessful() && response.body() !=null)
                {
                    modelList_distributor.postValue(response.body());
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<View_User_Model>> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchRetailer(String id)
    {
        Call<List<View_User_Model>> call = myInterface.fetch_retailer(id);
        call.enqueue(new Callback<List<View_User_Model>>() {
            @Override
            public void onResponse(Call<List<View_User_Model>> call, Response<List<View_User_Model>> response) {

                if (response.isSuccessful() && response.body() !=null)
                {
                    modelList_retailer.postValue(response.body());
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<View_User_Model>> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchUser(String ref_code)
    {
        Call<List<View_User_Model>> call = myInterface.fetch_user(ref_code);
        call.enqueue(new Callback<List<View_User_Model>>() {
            @Override
            public void onResponse(Call<List<View_User_Model>> call, Response<List<View_User_Model>> response) {

                if (response.isSuccessful() && response.body() !=null)
                {
                    modelList_user.postValue(response.body());
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<View_User_Model>> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addMoneyRequest(String user_id, String amount, String type, String under_by)
    {
        Call<String> call = myInterface.add_money_request(user_id,amount,type,under_by);
        ProgressUtils.showLoadingDialog(context);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());

                        if (jsonObject.getString("rec").equals("1"))
                        {
                            Toast.makeText(context, "Request Successfully", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                            fetchSuperDistributorTransaction();
                        }
                        else
                        {
                            Toast.makeText(context, "Request Not Successfully", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }

                    } catch (JSONException e) {

                        Toast.makeText(context, "Somthing Went wrong", Toast.LENGTH_SHORT).show();
                        ProgressUtils.cancelLoading();
                    }
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    ProgressUtils.cancelLoading();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
                ProgressUtils.cancelLoading();
            }
        });
    }


    public void fetchSuperDistributorTransaction()
    {
        Call<List<Transaction_Model>> call = null;

        if (user.getType().equals("Super Distributor"))
        {
            call = myInterface.fetch_super_dis_transaction(user.getUser_id());
        }
        else if (user.getType().equals("Distributor"))
        {
            call = myInterface.fetch_distributor_transaction(user.getUser_id());
        }
        else if (user.getType().equals("Retailer"))
        {
            call = myInterface.fetch_retailer_transaction(user.getUser_id());
        }

        call.enqueue(new Callback<List<Transaction_Model>>() {
            @Override
            public void onResponse(Call<List<Transaction_Model>> call, Response<List<Transaction_Model>> response) {

                if (response.isSuccessful() && response.body() !=null)
                {
                    modelList_tranaction.postValue(response.body());
                }
                else
                {

                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Transaction_Model>> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchMoneyRequest()
    {
        Call<List<Money_Request_Model>> call = null;

        if (user.getType().equals("Super Distributor"))
        {
            call = myInterface.fetch_super_dis_request(user.getUser_id());
        }
        else if (user.getType().equals("Distributor"))
        {
            call = myInterface.fetch_distributor_request(user.getUser_id());
        }
        else if (user.getType().equals("Retailer"))
        {
            call = myInterface.fetch_retailer_request(user.getRef_code());
        }

        call.enqueue(new Callback<List<Money_Request_Model>>() {
            @Override
            public void onResponse(Call<List<Money_Request_Model>> call, Response<List<Money_Request_Model>> response) {

                if (response.isSuccessful() && response.body() !=null)
                {
                    JSONArray jsonArray = new JSONArray(response.body());

                    if (jsonArray.length()==0)
                    {
                        Toast.makeText(context, "No Money Request Found", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        modelList_money_request.postValue(response.body());
                    }


                }
                else
                {

                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Money_Request_Model>> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchWalletAmount(TextView textView)
    {

        Call<String> call = myInterface.fetch_wallet_amount(user.getUser_id(), user.getType());
        ProgressUtils.showLoadingDialog(context);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());

                        if (jsonObject.getString("rec").equals("1"))
                        {
                            textView.setText(jsonObject.getString("wallet_amount"));
                            ProgressUtils.cancelLoading();
                        }
                        else
                        {
                            Toast.makeText(context, "Amount Not Fetch", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }

                    } catch (JSONException e) {

                        Toast.makeText(context, "Somthing Went wrong", Toast.LENGTH_SHORT).show();
                        ProgressUtils.cancelLoading();
                    }
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    ProgressUtils.cancelLoading();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
                ProgressUtils.cancelLoading();
            }
        });
    }

    public void fetchCommissionAmount(TextView textView)
    {

        Call<String> call = myInterface.fetch_wallet_amount(user.getUser_id(), user.getType());
        ProgressUtils.showLoadingDialog(context);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());

                        if (jsonObject.getString("rec").equals("1"))
                        {
                            textView.setText(jsonObject.getString("commission"));
                            ProgressUtils.cancelLoading();
                        }
                        else
                        {
                            Toast.makeText(context, "Amount Not Fetch", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }

                    } catch (JSONException e) {

                        Toast.makeText(context, "Somthing Went wrong", Toast.LENGTH_SHORT).show();
                        ProgressUtils.cancelLoading();
                    }
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    ProgressUtils.cancelLoading();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
                ProgressUtils.cancelLoading();
            }
        });
    }

    public void approvedRequest(String my_id, String user_id, String amount, String id, TextView txt_amount)
    {

        Call<String> call = myInterface.approved_request(amount, my_id, user_id, id, user.getType());
        ProgressUtils.showLoadingDialog(context);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());

                        if (jsonObject.getString("rec").equals("1"))
                        {

                            Toast.makeText(context, "Approved Successfully", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                            fetchWalletAmount(txt_amount);
                            //context.startActivity(new Intent(context, ViewMoneyRequestActivity.class));
                            ((Activity)context).finish();
                        }
                        else
                        {
                            Toast.makeText(context, "Not Approved", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }

                    } catch (JSONException e) {

                        Toast.makeText(context, "Somthing Went wrong", Toast.LENGTH_SHORT).show();
                        ProgressUtils.cancelLoading();
                    }
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    ProgressUtils.cancelLoading();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
                ProgressUtils.cancelLoading();
            }
        });
    }


    public void addWithdrawRequest(String user_id, String amount, String user_type)
    {
        Call<String> call = myInterface.add_withdraw_request(user_id,amount,user_type);
        ProgressUtils.showLoadingDialog(context);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());

                        if (jsonObject.getString("rec").equals("1"))
                        {
                            Toast.makeText(context, "Request Successfully", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                            fetchWithdrawTransaction(user_id);
                        }
                        else
                        {
                            Toast.makeText(context, "Request Not Successfully", Toast.LENGTH_SHORT).show();
                            ProgressUtils.cancelLoading();
                        }

                    } catch (JSONException e) {

                        Toast.makeText(context, "Somthing Went wrong", Toast.LENGTH_SHORT).show();
                        ProgressUtils.cancelLoading();
                    }
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    ProgressUtils.cancelLoading();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
                ProgressUtils.cancelLoading();
            }
        });
    }

    public void fetchWithdrawTransaction(String user_id)
    {
        Call<List<Transaction_Model>> call = myInterface.fetch_withdraw_transaction(user_id,user.getType());
        call.enqueue(new Callback<List<Transaction_Model>>() {
            @Override
            public void onResponse(Call<List<Transaction_Model>> call, Response<List<Transaction_Model>> response) {

                if (response.isSuccessful() && response.body() !=null)
                {
                    modelList_withdraw_tranaction.postValue(response.body());
                }
                else
                {

                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Transaction_Model>> call, Throwable t) {

                Toast.makeText(context, "Slow Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

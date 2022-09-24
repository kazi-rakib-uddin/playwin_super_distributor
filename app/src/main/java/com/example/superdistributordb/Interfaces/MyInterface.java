package com.example.superdistributordb.Interfaces;

import com.example.superdistributordb.Model.Money_Request_Model;
import com.example.superdistributordb.Model.Transaction_Model;
import com.example.superdistributordb.Model.View_User_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<String> login(@Field("phone") String phone, @Field("password") String password, @Field("type") String type);

    @FormUrlEncoded
    @POST("add_distributor.php")
    Call<String> add_distributor(@Field("name") String name, @Field("email") String email, @Field("phone") String phone,
                                 @Field("under_by") String under_by, @Field("password") String password);

    @FormUrlEncoded
    @POST("add_retailer.php")
    Call<String> add_retailer(@Field("name") String name, @Field("email") String email, @Field("phone") String phone,
                                 @Field("under_by") String under_by, @Field("password") String password);

    @FormUrlEncoded
    @POST("add_user.php")
    Call<String> add_user(@Field("name") String name, @Field("email") String email, @Field("phone") String phone,
                              @Field("under_by") String under_by, @Field("password") String password);

    @FormUrlEncoded
    @POST("fetch_distributor.php")
    Call<List<View_User_Model>> fetch_distributor(@Field("s_distributor_id") String s_distributor_id);

    @FormUrlEncoded
    @POST("fetch_retailer.php")
    Call<List<View_User_Model>> fetch_retailer(@Field("distributor_id") String distributor_id);

    @FormUrlEncoded
    @POST("fetch_user.php")
    Call<List<View_User_Model>> fetch_user(@Field("ref_code") String ref_code);

    @FormUrlEncoded
    @POST("add_money_request.php")
    Call<String> add_money_request(@Field("user_id") String user_id, @Field("amount") String amount, @Field("type") String type,
                                   @Field("under_by") String under_by);

    @FormUrlEncoded
    @POST("fetch_super_dis_transaction.php")
    Call<List<Transaction_Model>> fetch_super_dis_transaction(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("fetch_distributor_transaction.php")
    Call<List<Transaction_Model>> fetch_distributor_transaction(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("fetch_retailer_transaction.php")
    Call<List<Transaction_Model>> fetch_retailer_transaction(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("fetch_super_dis_request.php")
    Call<List<Money_Request_Model>> fetch_super_dis_request(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("fetch_distributor_request.php")
    Call<List<Money_Request_Model>> fetch_distributor_request(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("fetch_retailer_request.php")
    Call<List<Money_Request_Model>> fetch_retailer_request(@Field("ref_code") String ref_code);


    @FormUrlEncoded
    @POST("fetch_wallet_amount.php")
    Call<String> fetch_wallet_amount(@Field("user_id") String user_id, @Field("type") String type);

    @FormUrlEncoded
    @POST("approved_request.php")
    Call<String> approved_request(@Field("amount") String amount, @Field("my_id") String my_id, @Field("user_id")String user_id,
                                  @Field("id") String id, @Field("type") String type);

    @FormUrlEncoded
    @POST("add_withdraw_request.php")
    Call<String> add_withdraw_request(@Field("user_id") String user_id, @Field("amount") String amount, @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("fetch_withdraw_transaction.php")
    Call<List<Transaction_Model>> fetch_withdraw_transaction(@Field("user_id") String user_id, @Field("user_type") String user_type);

}


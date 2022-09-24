package com.example.superdistributordb.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private String user_id, type, under_by, ref_code;

    private Context context;

    SharedPreferences sharedPreferences;


    public User(Context context) {

        this.context = context;

        sharedPreferences = context.getSharedPreferences("login_details", Context.MODE_PRIVATE);

    }

    public String getUser_id() {
        user_id = sharedPreferences.getString("user_id", "");
        return user_id;
    }


    public void setUser_id(String user_id) {
        sharedPreferences.edit().putString("user_id", user_id).commit();
        this.user_id = user_id;
    }

    public String getType() {
        type = sharedPreferences.getString("type", "");
        return type;
    }

    public void setType(String type) {

        sharedPreferences.edit().putString("type", type).commit();
        this.type = type;
    }

    public String getUnder_by() {
        under_by = sharedPreferences.getString("under_by", "");
        return under_by;
    }

    public void setUnder_by(String under_by) {

        sharedPreferences.edit().putString("under_by", under_by).commit();
        this.under_by = under_by;
    }

    public String getRef_code() {

        ref_code = sharedPreferences.getString("ref_code", "");
        return ref_code;
    }

    public void setRef_code(String ref_code) {

        sharedPreferences.edit().putString("ref_code", ref_code).commit();
        this.ref_code = ref_code;
    }

    public void remove() {
        sharedPreferences.edit().clear().commit();
    }

}

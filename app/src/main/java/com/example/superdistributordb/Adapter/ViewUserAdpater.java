package com.example.superdistributordb.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.Model.View_User_Model;
import com.example.superdistributordb.databinding.SingleViewDetailsBinding;

import java.util.List;

public class ViewUserAdpater extends RecyclerView.Adapter<ViewUserAdpater.ViewHolder> {

    private Context context;
    private List<View_User_Model> user_models;
    private User user;

    public ViewUserAdpater(Context context, List<View_User_Model> user_models) {
        this.context = context;
        this.user_models = user_models;

        user = new User(context);
    }

    public void updateUserList(List<View_User_Model> view_user_models)
    {
        user_models = view_user_models;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SingleViewDetailsBinding binding = SingleViewDetailsBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.name.setText(user_models.get(position).getName());
        holder.binding.email.setText("Email ID : "+user_models.get(position).getEmail());
        holder.binding.phoneNo.setText("Phone No : "+user_models.get(position).getPhone());

        holder.binding.btnView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return user_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SingleViewDetailsBinding binding;

        public ViewHolder(@NonNull SingleViewDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }
}

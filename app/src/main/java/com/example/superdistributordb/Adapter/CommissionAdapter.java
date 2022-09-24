package com.example.superdistributordb.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.superdistributordb.Model.Transaction_Model;
import com.example.superdistributordb.databinding.SingleAmountTransactionBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommissionAdapter extends RecyclerView.Adapter<CommissionAdapter.ViewHolder> {

    private Context context;
    private List<Transaction_Model> transaction_models;
    private String formatDate;

    public CommissionAdapter(Context context, List<Transaction_Model> transaction_models) {
        this.context = context;
        this.transaction_models = transaction_models;
    }

    public void updateList(List<Transaction_Model> view_user_models)
    {
        transaction_models = view_user_models;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SingleAmountTransactionBinding binding = SingleAmountTransactionBinding.inflate(inflater,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Transaction_Model model = transaction_models.get(position);


        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(model.getDate());
            SimpleDateFormat sdf = new SimpleDateFormat("dd, LLL yyyy");
            formatDate = sdf.format(date);

        } catch (ParseException e) {

            e.printStackTrace();
        }


        holder.binding.name.setText(model.getName());
        holder.binding.status.setText(model.getStatus());
        holder.binding.date.setText(formatDate);

        holder.binding.amount.setTextColor(Color.parseColor("#33A01D")); //Green

        if (model.getStatus().equals("ADDED"))
        {
            holder.binding.status.setTextColor(Color.parseColor("#33A01D")); //Green
            holder.binding.amount.setText("+ ₹ "+model.getAmount());
        }
        else
        {
            holder.binding.status.setTextColor(Color.parseColor("#EA392E")); //Red
            holder.binding.amount.setTextColor(Color.parseColor("#EA392E")); //Red
            holder.binding.amount.setText("- ₹ "+model.getAmount());
        }

    }

    @Override
    public int getItemCount() {
        return transaction_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SingleAmountTransactionBinding binding;

        public ViewHolder(@NonNull SingleAmountTransactionBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

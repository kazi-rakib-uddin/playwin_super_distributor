package com.example.superdistributordb.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superdistributordb.Activity.ViewMoneyRequestActivity;
import com.example.superdistributordb.Model.Money_Request_Model;
import com.example.superdistributordb.Model.User;
import com.example.superdistributordb.ViewModel.ApiResponse;
import com.example.superdistributordb.databinding.SingleMoneyRequestBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoneyRequestAdapter extends RecyclerView.Adapter<MoneyRequestAdapter.ViewHolder> {

    private Context context;
    private List<Money_Request_Model> money_request_models;
    private String formatDate;
    private ApiResponse apiResponse;
    private User user;

    public MoneyRequestAdapter(Context context, List<Money_Request_Model> money_request_models) {
        this.context = context;
        this.money_request_models = money_request_models;

        apiResponse = new ApiResponse(context);
        user = new User(context);
    }

    public void updateList(List<Money_Request_Model> request_models)
    {
        money_request_models = request_models;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SingleMoneyRequestBinding binding = SingleMoneyRequestBinding.inflate(inflater,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Money_Request_Model model = money_request_models.get(position);


        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(model.getDate());
            SimpleDateFormat sdf = new SimpleDateFormat("dd, LLL yyyy");
            formatDate = sdf.format(date);

        } catch (ParseException e) {

            e.printStackTrace();
        }


        holder.binding.name.setText(model.getName());
        holder.binding.amount.setText("₹ "+model.getAmount());
        holder.binding.date.setText(formatDate);

        holder.binding.amount.setTextColor(Color.parseColor("#33A01D")); //Green

        holder.binding.btnApproed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int myAmount = Integer.parseInt(ViewMoneyRequestActivity.txt_amount.getText().toString());

                if (myAmount < Integer.parseInt(model.getAmount()))
                {
                    Toast.makeText(context, "Insufficient Amount", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    apiResponse.approvedRequest(user.getUser_id(), model.getUser_id(),model.getAmount(), model.getId(), ViewMoneyRequestActivity.txt_amount);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return money_request_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SingleMoneyRequestBinding binding;

        public ViewHolder(@NonNull SingleMoneyRequestBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

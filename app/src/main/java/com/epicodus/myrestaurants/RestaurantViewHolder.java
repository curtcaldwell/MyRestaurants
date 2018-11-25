package com.epicodus.myrestaurants;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {
TextView nameText;
    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.restaurant_name);
    }
}
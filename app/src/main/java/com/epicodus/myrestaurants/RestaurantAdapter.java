package com.epicodus.myrestaurants;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter <RestaurantViewHolder> {
List restaurantList = null;
Context context;

    public RestaurantAdapter(Context c) {
        context = c;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.restaurant_item,viewGroup,false);
        RestaurantViewHolder vh = new RestaurantViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int i) {
        restaurantList.get(i);
        restaurantViewHolder.itemView.


    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
    public void updateList(List list) {
        restaurantList = list;
    }
}
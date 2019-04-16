package com.epicodus.myrestaurants;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;

public class RestaurantAdapter extends RecyclerView.Adapter <RestaurantViewHolder> {
    List<Restaurant> restaurantList = new ArrayList<>();
    Context context;
    RestaurantsActivity.RestaurantClickListener listener;



    public RestaurantAdapter(Context c, List<Restaurant> list, RestaurantsActivity.RestaurantClickListener l) {
        context = c;
        restaurantList = list;
        listener = l;
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
    public void onBindViewHolder(@NonNull final RestaurantViewHolder restaurantViewHolder, int i) {
        restaurantViewHolder.onBind(restaurantList.get(i), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRestaurantClicked(restaurantList.get(restaurantViewHolder.getAdapterPosition()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
    public void updateList(List<Restaurant> list) {
        restaurantList = list;
        notifyDataSetChanged();
    }
}
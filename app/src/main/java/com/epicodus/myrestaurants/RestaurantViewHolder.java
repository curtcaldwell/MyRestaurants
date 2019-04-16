package com.epicodus.myrestaurants;


        import android.support.annotation.NonNull;
        import android.support.v7.widget.DividerItemDecoration;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.TextView;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    TextView nameText;
    View root;


    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView;
        nameText = itemView.findViewById(R.id.restaurant_name);
    }
    public void onBind(Restaurant restaurant, View.OnClickListener listener) {
        nameText.setText(restaurant.getName());
        root.setOnClickListener(listener);

    }
}
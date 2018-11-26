package com.epicodus.myrestaurants;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RestaurantInfoActivity extends AppCompatActivity {

    Restaurant restaurant = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);

        TextView nameText = findViewById(R.id.name);
        Intent intent = getIntent();
        restaurant = intent.getParcelableExtra("restaurant");
        nameText.setText(restaurant.getName());

        TextView addressText = findViewById(R.id.address);
        addressText.setText(createAddress());


        TextView priceText = findViewById(R.id.price);
        priceText.setText(restaurant.getPrice().toString());


        TextView phoneText = findViewById(R.id.phone);
        phoneText.setText(formattedPhoneNumber());

        TextView resText = findViewById(R.id.res);
        resText.setText(restaurant.getMobileReserveUrl());    }

        public String createAddress() {
        return restaurant.getAddress() + "\n" + restaurant.getCity() + " " + restaurant.getState() + " " + restaurant.getPostalCode();

        }

        public String formattedPhoneNumber() {
        String formattedString = restaurant.getPhone().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            return formattedString.substring(0,14);
        }

        
}

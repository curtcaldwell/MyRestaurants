package com.epicodus.myrestaurants;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


        ImageView priceText = findViewById(R.id.dollar);
        ImageView dollarView = findViewById(R.id.dollar);


        TextView phoneText = findViewById(R.id.phone);
        phoneText.setText(formattedPhoneNumber());
        phoneText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + formattedPhoneNumber()));
                startActivity(intent);

            }
        });

        TextView resText = findViewById(R.id.res);
        resText.setText("Click Here To Make Reservation");
        resText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = makeReservations();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });





    }

        public String createAddress() {
        return restaurant.getAddress() + "\n" + restaurant.getCity() + " " + restaurant.getState() + " " + restaurant.getPostalCode();

        }

        public String formattedPhoneNumber() {
        String formattedString = restaurant.getPhone().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            return formattedString.substring(0,14);
        }

        public String makeReservations() {
        return restaurant.getMobileReserveUrl();
        }


    }


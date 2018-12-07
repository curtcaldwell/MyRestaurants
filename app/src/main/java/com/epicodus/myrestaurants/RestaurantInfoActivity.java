package com.epicodus.myrestaurants;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantInfoActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String FAVORITES_PREFS = "favorite_prefs";
    Restaurant restaurant = null;
    ImageView dollarView = null;
    ImageView dollarView2 = null;
    ImageView dollarView3 = null;
    ImageView dollarView4 = null;

    GoogleMap map;

    boolean isfavorited = false;
    ImageView starImage;
    ImageView clickedStarImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);

        initializeMap();

        TextView nameText = findViewById(R.id.name);
        Intent intent = getIntent();
        restaurant = intent.getParcelableExtra("restaurant");
        nameText.setText(restaurant.getName());

        TextView addressText = findViewById(R.id.address);
        addressText.setText(createAddress());


        dollarView = findViewById(R.id.dollar);
        dollarView2 = findViewById(R.id.dollar2);
        dollarView3 = findViewById(R.id.dollar3);
        dollarView4 = findViewById(R.id.dollar4);

        starImage = findViewById(R.id.unclickedstar);
        clickedStarImage = findViewById(R.id.clickedstar);

        SharedPreferences prefs = getSharedPreferences(FAVORITES_PREFS, MODE_PRIVATE);
        isfavorited = prefs.getBoolean(restaurant.getId().toString(), false);
        if (isfavorited) {
            starImage.setVisibility(View.VISIBLE);
            clickedStarImage.setVisibility(View.GONE);
        } else {
            starImage.setVisibility(View.GONE);
            clickedStarImage.setVisibility(View.VISIBLE);

        }



        setDollarSignStyles();


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

        View favoritiesLayout = findViewById(R.id.favorities_icon_layout);
        favoritiesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          toggleFavorite();

            }
        });


    }
    private void setUpMap() {

        map.addMarker(new MarkerOptions().position(new LatLng(restaurant.getLat(), restaurant.getLng())).title(
                "Marker"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(restaurant.getLat(), restaurant.getLng()), 14));
    }

    private void initializeMap() {
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            if (mapFrag != null) {
                mapFrag.getMapAsync(this);
            }
        }
    }

    public String createAddress() {
        return restaurant.getAddress() + "\n" + restaurant.getCity() + " " + restaurant.getState() + " " + restaurant.getPostalCode();

    }

    public String formattedPhoneNumber() {
        if (!restaurant.getPhone().isEmpty()) {
            String formattedString = restaurant.getPhone().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            if (formattedString.length() >= 13) {
                return formattedString.substring(0, 14);
            }
        }
        return "";
    }

    public String makeReservations() {
        return restaurant.getMobileReserveUrl();
    }

    public void setDollarSignStyles() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (restaurant.getPrice() == 1) {
                dollarView.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
            } else if (restaurant.getPrice() == 2) {
                dollarView.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
                dollarView2.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
            } else if (restaurant.getPrice() == 3) {
                dollarView.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
                dollarView2.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
                dollarView3.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
            } else if (restaurant.getPrice() == 4) {
                dollarView.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
                dollarView2.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
                dollarView3.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
                dollarView4.setColorFilter(new
                        PorterDuffColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_ATOP));
            }
        }

    }

    private void toggleFavorite() {
        SharedPreferences.Editor editor = getSharedPreferences(FAVORITES_PREFS,MODE_PRIVATE).edit();

        if (isfavorited) {
            isfavorited = false;
            starImage.setVisibility(View.VISIBLE);
            clickedStarImage.setVisibility(View.GONE);
        } else {
            isfavorited = true;
            starImage.setVisibility(View.GONE);
            clickedStarImage.setVisibility(View.VISIBLE);

        }
        editor.putBoolean(restaurant.getId().toString(),isfavorited).apply();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        setUpMap();
    }
}


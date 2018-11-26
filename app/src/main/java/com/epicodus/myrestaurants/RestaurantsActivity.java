package com.epicodus.myrestaurants;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RestaurantsActivity extends AppCompatActivity {
    private TextView mLocationTextView;
    private RestaurantAdapter adapter;
    private RecyclerView recyclerView;
    private List<Restaurant> restaurantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        recyclerView = findViewById(R.id.restaurant_list);
        mLocationTextView = findViewById(R.id.locationTextView);
        adapter = new RestaurantAdapter(this, restaurantList, new RestaurantClickListener() {
            @Override
            public void onRestaurantClicked(Restaurant restaurant) {
                Intent intent = new Intent(RestaurantsActivity.this, RestaurantInfoActivity.class);
                intent.putExtra("restaurant", restaurant);

//                intent.putExtra("name", restaurant.getName());
//                intent.putExtra("address", restaurant.getAddress());
//                if (restaurant.getPrice() != null) {
//                    intent.putExtra("price", restaurant.getPrice());
//                }
//                intent.putExtra("phone", restaurant.getPhone());
//                intent.putExtra("reservation", restaurant.getMobileReserveUrl());
                startActivity(intent);
                Toast.makeText(RestaurantsActivity.this, restaurant.getName(), Toast.LENGTH_SHORT).show();
                //TODO start intent for restaurant info activity and pass restaurant as parcelable as extra

            }
        });


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.updateList(restaurantList);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);
        getRestaurants(location);
    }

    private OpenTableService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://opentable.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenTableService service = retrofit.create(OpenTableService.class);
        return service;

    }

    private void getRestaurants(final String zipcode) {
        Call call = getService().getOpenTableResponse(zipcode);
        call.enqueue(new Callback<OpenTableResponse>() {
            @Override
            public void onResponse(Call<OpenTableResponse> call, Response<OpenTableResponse> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getTotalEntries() == 0 && zipcode.length() == 5) {
                        Toast.makeText(RestaurantsActivity.this, "There are no restaurants in your area", Toast.LENGTH_SHORT).show();
                    } else {
                        restaurantList = response.body().getRestaurants();
                        adapter.updateList(restaurantList);
                    }

                }

            }

            @Override
            public void onFailure(Call<OpenTableResponse> call, Throwable t) {


            }
        });
    }

    public interface OpenTableService {
        @GET("api/restaurants")
        Call<OpenTableResponse> getOpenTableResponse(@Query("zip") String zip);
    }

    public interface RestaurantClickListener {
        void onRestaurantClicked(Restaurant restaurant);
    }

}
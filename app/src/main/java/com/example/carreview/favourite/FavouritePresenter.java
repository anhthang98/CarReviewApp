package com.example.carreview.favourite;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.carreview.home.HomeAdapter;
import com.example.carreview.model.Favorite;
import com.example.carreview.model.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class FavouritePresenter {
    IFavourite iFavourite;
    Context context;

    private static final String URL_GET_FAVORITE = "http://10.30.50.33/car_project/getFavorite.php";

    public FavouritePresenter(IFavourite iFavourite, Context context) {
        this.iFavourite = iFavourite;
        this.context = context;
    }
    public void getFavorite(final FavoriteAdapter favoriteAdapter, final List<Favorite> favoriteList) {
        JsonArrayRequest request = new JsonArrayRequest(URL_GET_FAVORITE,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(context, "Couldn't fetch the car items! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        List<Favorite> items = new Gson().fromJson(response.toString(), new TypeToken<List<Favorite>>() {
                        }.getType());

                        favoriteList.clear();
                        favoriteList.addAll(items);

                        favoriteAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }

}

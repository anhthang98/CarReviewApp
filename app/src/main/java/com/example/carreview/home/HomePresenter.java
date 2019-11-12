package com.example.carreview.home;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carreview.model.LikePost;
import com.example.carreview.model.Post;
import com.example.carreview.model.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class HomePresenter {
    IHome iHome;
    Context context;

    private static final String URL_GET_POST = "http://10.30.50.33/car_project/getPost.php";
    private static final String URL_ADD_FAVORITE = "http://10.30.50.33/car_project/addFavorite.php";
    private static final String URL_ADD_LIKE = "http://10.30.50.33/car_project/addLike.php";
    private static final String URL_DELETE_LIKEPOST = "http://10.30.50.33/car_project/deleteLike.php";
    private static final String URL_GET_LIKEPOST = "http://10.30.50.33/car_project/getLikePost.php";

    public HomePresenter(IHome iHome, Context context) {
        this.iHome = iHome;
        this.context = context;
    }

    public void getLikePost(final List<LikePost> likePostList) {
        JsonArrayRequest request = new JsonArrayRequest(URL_GET_LIKEPOST,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(context, "Couldn't fetch the car items! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        List<LikePost> items = new Gson().fromJson(response.toString(), new TypeToken<List<LikePost>>() {
                        }.getType());

                        likePostList.clear();
                        likePostList.addAll(items);

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

    public void fetchCarItems(final HomeAdapter homeAdapter, final List<Post> postList) {
        JsonArrayRequest request = new JsonArrayRequest(URL_GET_POST,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(context, "Couldn't fetch the car items! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        List<Post> items = new Gson().fromJson(response.toString(), new TypeToken<List<Post>>() {
                        }.getType());

                        postList.clear();
                        postList.addAll(items);

                        homeAdapter.notifyDataSetChanged();
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

    public void addFavorite(final String userID, final String postID, final String checkFavorite) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD_FAVORITE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i(TAG, "onResponse: " + response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(context, "Successfull!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Invalid!" + response, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("userID", userID);
                params.put("postID", postID);
                params.put("checkFavorite", checkFavorite);

                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

    public void addLike(final String userID, final String postID, final String checkLike) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD_LIKE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i(TAG, "onResponse: " + response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(context, "Successfull!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Invalid!" + response, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("userID", userID);
                params.put("postID", postID);
                params.put("checkLike", checkLike);

                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

    public void deleteLikePost(final String userID, final String postID) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELETE_LIKEPOST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i(TAG, "onResponse: " + response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(context, "Successfull!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Invalid!" + response, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("userID", userID);
                params.put("postID", postID);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}

package com.example.carreview.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carreview.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginPresenter {
    ILogin iLogin;
    Context context;
    String URL = "http://10.30.50.33/car_project/login.php";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public LoginPresenter(ILogin iLogin, Context context) {
        this.iLogin = iLogin;
        this.context = context;
    }

    public void onLogin(String user, String pass) {

//        if (user.equals("thangnd")&&pass.equals("12345")){
//            iLogin.onSuccessFul();
//        }else iLogin.onMessenger("User or pass invalid");
        LoginAccount(user, pass);

    }

    public void LoginAccount(final String user, final String pass) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String userId = "", userRole = "", userName = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject index = jsonArray.getJSONObject(i);
                                userId = index.getString("userid");
                                userName = index.getString("username");
                                userRole = index.getString("user_role");
                            }
                            preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            editor = preferences.edit();
                            editor.putString("userId", userId);
                            editor.putString("userName", userName);
                            editor.putString("userRole", userRole);
                            editor.commit();
                            if (success.equals("1")) {

                                Toast.makeText(context, "Successfull!" + userId + " " + userName + " " + userRole, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            } else {
                                Toast.makeText(context, "Invalid!", Toast.LENGTH_SHORT).show();
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

                params.put("username", user);
                params.put("password", pass);

                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

}

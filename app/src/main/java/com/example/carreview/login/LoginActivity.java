package com.example.carreview.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.carreview.R;
import com.example.carreview.model.Session;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity implements ILogin {
    EditText etUsername, etPassword;
    CardView cardViewLogin;
    LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cardViewLogin = findViewById(R.id.cardViewLogin);

        presenter = new LoginPresenter(this, getBaseContext());
        cardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLogin()) {
                    String name = etUsername.getText().toString().trim();
                    String pass = etPassword.getText().toString().trim();

                    presenter.onLogin(name, pass);
                }
            }
        });

    }

    private boolean validateLogin() {
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(getResources().getString(R.string.user_error));
            etUsername.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(getResources().getString(R.string.password_error));
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void onSuccessFul() {

    }

    @Override
    public void onMessenger(String mes) {

    }
}

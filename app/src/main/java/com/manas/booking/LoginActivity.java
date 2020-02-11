package com.manas.booking;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.manas.booking.Fragments.SearchActivity;
import com.manas.booking.Profile.MyInfo;

import java.util.prefs.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Api gsonApi;
    private EditText mailEdittext, paswordEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://avtobeket.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        gsonApi = retrofit.create(Api.class);

        initial();

    }

    public void userLogin(){
        String mail = mailEdittext.getText().toString().trim();
        String password = paswordEdittext.getText().toString().trim();
        //validateData(mail,password);

        User user  = new User("gulzat","12345678");

        Call<User> call = gsonApi.userLogin(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "codeERROR" + response.code(),Toast.LENGTH_LONG).show();
                    Log.d("LoginActivity", "eeeeeeerror: " + response.code());
                    return;
                }


                System.out.println("kjdsfhdsbfsh");

                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");


                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");
                System.out.println("kjdsfhdsbfsh");

                User userResponse = response.body();
                String token;
                token  = userResponse.getToken() + "\n";
                boolean loginStatus;
                loginStatus = userResponse.getLoginStatus();

                if(!token.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Login succes",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(i);
                    Log.d("LoginActivity", "0000000000token: " + token + "login status:" + loginStatus);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("LoginActivity", "onFailureeeeeeeee: " + t.getMessage());

            }
        });

    }

    public void registerPage(){
        Intent i = new Intent(this, MyInfo.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                userLogin();
                break;
            case R.id.registerTextView:
                registerPage();
                break;
        }

    }

    public void initial(){

        mailEdittext = findViewById(R.id.mailEditText);
        paswordEdittext = findViewById(R.id.passwordEditText);
        findViewById(R.id.registerTextView).setOnClickListener(this);
        findViewById(R.id.loginButton).setOnClickListener(this);

    }

    public void validateData(String mail, String password)
    {

        if(mail.isEmpty()){
            mailEdittext.setError("enter an email");
            mailEdittext.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            mailEdittext.setError("enter a valid email");
            mailEdittext.requestFocus();
            return;
        }

        if(password.isEmpty()){
            paswordEdittext.setError("enter a password");
            mailEdittext.requestFocus();
            return;
        }

        if(password.length() < 8){
            paswordEdittext.setError("password is short");
            mailEdittext.requestFocus();
            return;
        }
    }
}

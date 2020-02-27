package com.manas.booking.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.manas.booking.Api;
import com.manas.booking.Model.User;
import com.manas.booking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyInfo extends AppCompatActivity {

    private Api gsonApi;
    private TextView nameTextView, surnameTextVew, usernameTextView, mailTextView;
    User userResponse;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        Intent getIntent = getIntent();
        token = getIntent.getStringExtra("token");


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://avtobeket.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        gsonApi = retrofit.create(Api.class);

        initial();
    }

    public void changeInfo(View view)
    {
        Log.d("MyInfo", "changeInfo: " + userResponse.getFirstname());

        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra(User.class.getSimpleName(), userResponse);
        intent.putExtra("token", token);
        startActivity(intent);
    }
    private void initial(){

        Call<User> call = gsonApi.userProfile("Token " + token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful()){
                    userResponse = response.body();

                    String name = "asd", surname = "Zheenbek kizi", email = "gulzat972mail.ru", username = "gulzatJ";

                    name = userResponse.getFirstname();
                    surname = userResponse.getLast_name();
                    email = userResponse.getEmail();
                    username = userResponse.getUsername();
                    setValue(name,surname,email,username);

                    Log.d("MyInfo", "name:  " + name + "\nsurname:  " + surname +
                            "\nemail:  " + email + "\nusername:  " + username);
                }
                else Log.d("MyInfo", "eeeeerror:  " + response.code());
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("MyInfoActivity", "onFailureeeeeeeee: " + t.getMessage());
            }
        });
    }

    public void  setValue(String name, String surname, String email, String username){
        nameTextView = findViewById(R.id.nameTextView);
        surnameTextVew = findViewById(R.id.surnameTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        mailTextView = findViewById(R.id.emailTextView);

        nameTextView.setText(name);
        surnameTextVew.setText(surname);
        usernameTextView.setText(username);
        mailTextView.setText(email);
    }




}

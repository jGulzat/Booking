package com.manas.booking.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.manas.booking.Api;
import com.manas.booking.MainActivity;
import com.manas.booking.R;
import com.manas.booking.RegisterActivity;
import com.manas.booking.User;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyInfo extends AppCompatActivity {

    private Api gsonApi;
    private TextView nameTextView, surnameTextVew, usernameTextView, mailTextView;
    User userResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

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
        startActivity(intent);
    }
    private void initial(){

        String token = "6b9b9c17f710630ff74db8c9ad73428bb6ad51b4";

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
                    int s = response.code();
                    setValue(name,surname,email,username);
                    //name = Integer.toString(s);

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

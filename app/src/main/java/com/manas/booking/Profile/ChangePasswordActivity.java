package com.manas.booking.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.manas.booking.Api;
import com.manas.booking.Model.Passwords;
import com.manas.booking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePasswordActivity extends AppCompatActivity {

    private Api gsonApi;
    private EditText oldPass,newPass,validNewPass;
    String oldPassword, newPassword, validNewPasword,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent getIntent = getIntent();
        token = getIntent.getStringExtra("token").trim();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://avtobeket.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        gsonApi = retrofit.create(Api.class);
        initial();

    }

    public void changePassword(View v){
        oldPassword = oldPass.getText().toString();
        newPassword = newPass.getText().toString();
        validNewPasword = validNewPass.getText().toString();

        oldPassword =  "12345678";
        newPassword = "12345678";
        validNewPasword = "12345678";

        if(newPassword == validNewPasword){
            Passwords passwords = new Passwords(oldPassword,newPassword);
           // String token = "6b9b9c17f710630ff74db8c9ad73428bb6ad51b4";
            Call<Passwords> call = gsonApi.changePassword("Token " + token, passwords);
            call.enqueue(new Callback<Passwords>() {
                @Override
                public void onResponse(Call<Passwords> call, Response<Passwords> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(ChangePasswordActivity.this,
                                "Пароль успешно изменен!",Toast.LENGTH_LONG).show();
                        Log.d("ChangePassword", "Пароль успешно изменен!");
                    }
                    else Log.d("ChangePassword", "eeeeerror:  " + response.code());
                }
                @Override
                public void onFailure(Call<Passwords> call, Throwable t) {
                    Log.d("ChangePassword", "onFailureeeeeeeee: " + t.getMessage());
                }
            });
        }
    }

    public void initial(){
        oldPass = findViewById(R.id.oldPassEditText);
        newPass = findViewById(R.id.newPassEditText);
        validNewPass = findViewById(R.id.confirmPassEditText);
    }
}

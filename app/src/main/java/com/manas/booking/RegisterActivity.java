package com.manas.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manas.booking.Model.User;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Api gsonApi;
    private EditText nameEdittext, surnameEdittext, usernameEdittext, mailEdittext, paswordEdittext,confirmPasswordEdittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://avtobeket.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        gsonApi = retrofit.create(Api.class);

        initial();
    }

    private void userSignUp(){

        String name = nameEdittext.getText().toString().trim();
        String surname = surnameEdittext.getText().toString().trim();
        String username = usernameEdittext.getText().toString().trim();
        String mail = mailEdittext.getText().toString().trim();
        String password = paswordEdittext.getText().toString().trim();
        String confirmPassword = confirmPasswordEdittext.getText().toString().trim();

        name = "Gulzat";            surname = "Zheenbekova";
        mail = "gulza97@mail.ru";   username = "gulzat";
        password = "12345678";      confirmPassword = "12345678";

        validateData(name,surname,username,mail,password,confirmPassword);

        User newUser  = new User(name,surname,username,mail,password);
        Call<User> call = gsonApi.userSignUp(newUser);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "codeERROR" + response.code(),Toast.LENGTH_LONG).show();
                    Log.d("RegisterActivity", "eeeeeeerror: " + response.code());
                    return;
                }

                User newUserResponse = response.body();
                String username = newUserResponse.getUsername();
                Log.d("LoginActivity", "onFailureeeeeeeee: " + username);

                if(!username.isEmpty()){
                    Toast.makeText(RegisterActivity.this,
                            "Успешно зарегистрированы!",Toast.LENGTH_LONG).show();
                    Log.d("RegisterActivity", "11111username:  " + username);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(RegisterActivity.this,
                        "Такой пользователь уже существует\n" + t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("RegisterActivity", "onFailureeeeeeeee: " + t.getMessage());

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
                userSignUp();
                break;
        }

    }

    public void initial(){

        nameEdittext = findViewById(R.id.nameEditText);
        surnameEdittext = findViewById(R.id.surnameEditText);
        mailEdittext = findViewById(R.id.mailEditText);
        usernameEdittext = findViewById(R.id.userNameEditText);
        paswordEdittext = findViewById(R.id.passwordEditText);
        confirmPasswordEdittext = findViewById(R.id.confirmPasswordEditText);
        findViewById(R.id.registerButton).setOnClickListener(this);
    }

    public void validateData(String name, String surname, String username, String mail, String password, String confirmPassword){

        if(name.isEmpty()){
            nameEdittext.setError("enter a name");
            nameEdittext.requestFocus();
            return;
        }

        if(surname.isEmpty()){
            surnameEdittext.setError("enter a surname");
            surnameEdittext.requestFocus();
            return;
        }

        if(username.isEmpty()){
            usernameEdittext.setError("enter an username");
            usernameEdittext.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            mailEdittext.setError("enter a valid email");
            mailEdittext.requestFocus();
            return;
        }

        if(password.isEmpty()){
            paswordEdittext.setError("enter a password");
            paswordEdittext.requestFocus();
            return;
        }

        if(password.length() < 8){
            paswordEdittext.setError("password is short");
            mailEdittext.requestFocus();
            return;
        }

        if(confirmPassword.isEmpty()){
            confirmPasswordEdittext.setError("Повторите пароль");
            mailEdittext.requestFocus();
            return;
        }

        if(confirmPassword.length() < 8 || password!=confirmPassword ){
            confirmPasswordEdittext.setError("Не правильно ввели повторный пароль");
            confirmPasswordEdittext.requestFocus();
            return;
        }

    }
}

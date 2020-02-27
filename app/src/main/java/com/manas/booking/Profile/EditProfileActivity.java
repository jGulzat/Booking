package com.manas.booking.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.manas.booking.Api;
import com.manas.booking.Model.User;
import com.manas.booking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity {

    private Api gsonApi;
    User userInfo;
    private EditText nameEdittext, surnameEdittext, usernameEdittext, emailEdittext;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://avtobeket.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        gsonApi = retrofit.create(Api.class);

        Bundle arguments = getIntent().getExtras();
        Intent getIntent = getIntent();
        token = getIntent.getStringExtra("token");

        if (arguments != null)
        {
            userInfo = (User) arguments.getSerializable(User.class.getSimpleName());
            initial(userInfo.getFirstname(),userInfo.getLast_name(),userInfo.getUsername(),userInfo.getEmail());
        }
        else Log.d("error", "onCreate: " + "arguments is null");

    }

    public void initial(String name, String surname, String username, String email){
        nameEdittext = findViewById(R.id.editNameEditText);
        surnameEdittext = findViewById(R.id.editSurnameEdittext);
        usernameEdittext = findViewById(R.id.editUsernameEdittext);
        emailEdittext = findViewById(R.id.editEmailEdittext);

        nameEdittext.setText(name);
        surnameEdittext.setText(surname);
        usernameEdittext.setText(username);
        emailEdittext.setText(email);
    }

    public void saveNewInfo(View v){
        userInfo.setFirst_name(nameEdittext.getText().toString());
        Log.d("info", "saveNewInfo: " + userInfo.getFirstname());
        userInfo.setLast_name(surnameEdittext.getText().toString());
        userInfo.setUsername(usernameEdittext.getText().toString());
        userInfo.setEmail(emailEdittext.getText().toString());

        Call<User> call = gsonApi.saveNewInfo("Token " + token, userInfo);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EditProfileActivity.this,
                            "Успешно изменены ваши личные данные!",Toast.LENGTH_LONG).show();
                    Log.d("RegisterActivity", "Успешно изменены ваши личные данные");
                     }
                else Log.d("MyInfo", "eeeeerror:  " + response.code());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("RegisterActivity", "onFailureeeeeeeee: " + t.getMessage());
            }
        });

    }
}

package com.manas.booking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitClient {
    private static final String BASE_URL = "https://avtobeket.herokuapp.com/";
    private static retrofitClient mInstance;
    private Retrofit retrofit;

    private retrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized retrofitClient getInstance(){
        if( mInstance == null){
            mInstance= new retrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }

    /*
       Call<ResponseBody> call = retrofitClient.getInstance()
                .getApi().userSignUp(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                    String s = response.body().string();
                    Toast.makeText(RegisterActivity.this,s,Toast.LENGTH_LONG).show();
                    Log.d("RegisterActivity", s);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("RegisterActivity", t.getLocalizedMessage());
            }
        });



     */


}

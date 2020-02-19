package com.manas.booking;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    @FormUrlEncoded
    @POST("/api/register/")
    Call<ResponseBody> createUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("password")  String password
    );

    @POST("/api/login/")
    Call<User>userLogin(@Body User user);

    @POST("/api/register/")
    Call<User>userSignUp(@Body User user);

    @GET("/api/profile/")
    Call<User>userProfile(@Header("Authorization") String token);

    @PUT("/api/profile/")
    Call<User>saveNewInfo(@Header("Authorization") String token, @Body User user);

    @PUT("/api/change_pass/")
    Call<Passwords>changePassword(@Header("Authorization") String token, @Body Passwords passwords);

    @GET("/api/stations/")
    Call<List<Stations>> getStations(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("/api/route/")
    Call<List<Route>> getRoute(@Header("Authorization") String token, @Body Station station);


}

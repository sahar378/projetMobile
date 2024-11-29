package com.example.projetmobile.utils;

import com.example.projetmobile.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("users")
    Call<List<User>> getAllUser();

    @POST("users")
    Call<User> addUser(@Body User user);

    @GET("users/{id}")
    Call<User> getUserById(@Path("id") Long id);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") Long id, @Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") Long id);
}

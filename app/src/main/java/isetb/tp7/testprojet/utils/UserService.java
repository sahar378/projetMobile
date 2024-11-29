package isetb.tp7.testprojet.utils;

import java.util.List;

import isetb.tp7.testprojet.model.User;
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
    @POST("users") // Remplacez par le bon endpoint pour ajouter un utilisateur
    Call<User> addUser(@Body User user);
    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") Long id, @Body User user);
    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") Long id);
    @GET("users/{id}")
    Call<User> getUserById(@Path("id") Long id);

}
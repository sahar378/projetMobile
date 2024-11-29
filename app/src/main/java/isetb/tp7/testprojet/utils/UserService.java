package isetb.tp7.testprojet.utils;

import java.util.List;

import isetb.tp7.testprojet.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("users")
    Call<List<User>> getAllUser();
    @POST("users") // Remplacez par le bon endpoint pour ajouter un utilisateur
    Call<User> addUser(@Body User user);

}
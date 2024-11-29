package com.example.projetmobile.utils;

public class Apis {
    public static final String URL = "http://10.0.2.2:8080/";
    public static UserService getService(){
        return Client.getRetrofit(URL).create(UserService.class);
    }
}
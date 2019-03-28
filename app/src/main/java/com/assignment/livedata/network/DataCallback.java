package com.assignment.livedata.network;


import com.assignment.livedata.db.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataCallback {
//    void onSuccess(String response);
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("users")
    Call<List<UserModel>> getUsers();

}
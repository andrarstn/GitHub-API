package com.kelompokempat.githubapi.networking;

import com.kelompokempat.githubapi.model.follow.ModelFollow;
import com.kelompokempat.githubapi.model.repo.ModelRepo;
import com.kelompokempat.githubapi.model.search.ModelSearch;
import com.kelompokempat.githubapi.model.user.ModelUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("users/{username}")
    Call<ModelUser> detailUser(@Path("username") String username);

    @GET("/search/users")
    Call<ModelSearch> searchUser(@Header("Authorization") String authorization,
                                 @Query("q") String username);

    @GET("users/{username}/followers")
    Call<ArrayList<ModelFollow>> followersUser(@Header("Authorization") String authorization,
                                          @Path("username") String username);

    @GET("users/{username}/following")
    Call<ArrayList<ModelFollow>> followingUser(@Header("Authorization") String authorization,
                                               @Path("username") String username);

    @GET("users/{username}/repos")
    Call<ArrayList<ModelRepo>> repoUser(@Header("Authorization") String authorization,
                                             @Path("username") String username);

}

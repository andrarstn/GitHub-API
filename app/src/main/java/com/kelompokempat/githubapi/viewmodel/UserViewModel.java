package com.kelompokempat.githubapi.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kelompokempat.githubapi.model.follow.ModelFollow;
import com.kelompokempat.githubapi.model.repo.ModelRepo;
import com.kelompokempat.githubapi.model.search.ModelSearch;
import com.kelompokempat.githubapi.model.search.ModelSearchData;
import com.kelompokempat.githubapi.model.user.ModelUser;
import com.kelompokempat.githubapi.networking.ApiClient;
import com.kelompokempat.githubapi.networking.ApiInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ModelSearchData>> modelSearchMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFollow>> modelFollowersMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFollow>> modelFollowingMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelRepo>> modelRepoMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ModelUser> modelUserMutableLiveData = new MutableLiveData<>();
    public static String strApiKey = "b650046bf640e7bf7054093854b8d02a";

    //method search user
    public void setSearchUser(String query) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelSearch> call = apiService.searchUser(strApiKey, query);
        call.enqueue(new Callback<ModelSearch>() {
            @Override
            public void onResponse(Call<ModelSearch> call, Response<ModelSearch> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    ArrayList<ModelSearchData> items = new ArrayList<>(response.body().getModelSearchData());
                    modelSearchMutableLiveData.setValue(items);
                }
            }

            @Override
            public void onFailure(Call<ModelSearch> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method view detail user
    public void setUserDetail(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelUser> call = apiService.detailUser(strUsername);
        call.enqueue(new Callback<ModelUser>() {

            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelUserMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method get followers
    public void setFollowersUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelFollow>> call = apiService.followersUser(strApiKey, strUsername);
        call.enqueue(new Callback<ArrayList<ModelFollow>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelFollow>> call, Response<ArrayList<ModelFollow>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowersMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelFollow>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method get following
    public void setFollowingUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelFollow>> call = apiService.followingUser(strApiKey, strUsername);
        call.enqueue(new Callback<ArrayList<ModelFollow>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelFollow>> call, Response<ArrayList<ModelFollow>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowingMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelFollow>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method get repo
    public void setRepoUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelRepo>> call = apiService.repoUser(strApiKey, strUsername);
        call.enqueue(new Callback<ArrayList<ModelRepo>>() {

            @Override
            public void onResponse(Call<ArrayList<ModelRepo>> call, Response<ArrayList<ModelRepo>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelRepoMutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ModelRepo>> call, Throwable t) {
                Log.e("failure", t.toString());
            }

        });
    }

    public LiveData<ArrayList<ModelSearchData>> getResultList() {
        return modelSearchMutableLiveData;
    }

    public LiveData<ModelUser> getUserList() {
        return modelUserMutableLiveData;
    }

    public LiveData<ArrayList<ModelFollow>> getFollowersUser() {
        return modelFollowersMutableLiveData;
    }

    public LiveData<ArrayList<ModelFollow>> getFollowingUser() {
        return modelFollowingMutableLiveData;
    }

    public LiveData<ArrayList<ModelRepo>> getRepoUser() {
        return modelRepoMutableLiveData;
    }

}
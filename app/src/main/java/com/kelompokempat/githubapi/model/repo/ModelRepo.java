package com.kelompokempat.githubapi.model.repo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ModelRepo implements Parcelable {

    @SerializedName("login")
    private String login;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(login);
        parcel.writeString(htmlUrl);
    }

    protected ModelRepo(Parcel in) {
        login = in.readString();
        htmlUrl = in.readString();
    }

    public static final Parcelable.Creator<ModelRepo> CREATOR = new Parcelable.Creator<ModelRepo>() {
        @Override
        public ModelRepo createFromParcel(Parcel in) {
            return new ModelRepo(in);
        }

        @Override
        public ModelRepo[] newArray(int size) {
            return new ModelRepo[size];
        }
    };

}

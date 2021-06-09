package com.kelompokempat.githubapi.model.user;

import com.google.gson.annotations.SerializedName;

public class ModelUser {

    @SerializedName("id")
    private int id;

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("blog")
    private String blog;

    @SerializedName("location")
    private String location;

    @SerializedName("email")
    private String email;

    @SerializedName("bio")
    private String bio;

    @SerializedName("twitter_username")
    private String twitterUsername;

    @SerializedName("public_repos")
    private String publicRepos;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    public ModelUser() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getPublicRepos() {
        return publicRepos;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

}
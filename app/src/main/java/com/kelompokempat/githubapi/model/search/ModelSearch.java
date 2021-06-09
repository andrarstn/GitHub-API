package com.kelompokempat.githubapi.model.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelSearch {

    @SerializedName("items")
    private List<ModelSearchData> modelSearchData;

    public ModelSearch(List<ModelSearchData> modelSearchData) {
        this.modelSearchData = modelSearchData;
    }

    public List<ModelSearchData> getModelSearchData() {
        return modelSearchData;
    }

}
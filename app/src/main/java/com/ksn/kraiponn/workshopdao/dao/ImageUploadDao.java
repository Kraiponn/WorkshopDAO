package com.ksn.kraiponn.workshopdao.dao;

import com.google.gson.annotations.SerializedName;

public class ImageUploadDao {
    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}

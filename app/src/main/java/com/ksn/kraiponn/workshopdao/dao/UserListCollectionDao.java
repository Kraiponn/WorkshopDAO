package com.ksn.kraiponn.workshopdao.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListCollectionDao {

    @SerializedName("error")    private boolean error;
    @SerializedName("err_msg")  private String errMsg;
    @SerializedName("data")     private List<UserListDao> data;

    public UserListCollectionDao(){}

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<UserListDao> getData() {
        return data;
    }

    public void setData(List<UserListDao> data) {
        this.data = data;
    }

}

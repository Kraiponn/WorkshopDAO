package com.ksn.kraiponn.workshopdao.dao;

import com.google.gson.annotations.SerializedName;

public class UserListItemDao {

    @SerializedName("error")        private boolean err;
    @SerializedName("err_msg")      private String errMsg;
    @SerializedName("success_msg")  private String successMsg;

    public UserListItemDao(){}

    public boolean isErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
}

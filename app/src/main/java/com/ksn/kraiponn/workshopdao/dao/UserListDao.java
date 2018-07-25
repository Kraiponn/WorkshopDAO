package com.ksn.kraiponn.workshopdao.dao;

import com.google.gson.annotations.SerializedName;

public class UserListDao {

    @SerializedName("user_id")          private String userId;
    @SerializedName("user_name")        private String userName;
    @SerializedName("password")         private String password;
    @SerializedName("email")            private String email;
    @SerializedName("gender")           private String gender;
    @SerializedName("password_hint")    private String passwordHint;

    public UserListDao() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

}

package com.ksn.kraiponn.workshopdao.manager.http;

import com.ksn.kraiponn.workshopdao.dao.ImageUploadDao;
import com.ksn.kraiponn.workshopdao.dao.PhotoItemCollectionDao;
import com.ksn.kraiponn.workshopdao.dao.UserListCollectionDao;
import com.ksn.kraiponn.workshopdao.dao.UserListItemDao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("list")
    Call<PhotoItemCollectionDao> loadData();

    @POST("log-api.php")
    Call<UserListCollectionDao> feedUserList();

    @FormUrlEncoded
    @POST("login-api.php")
    Call<UserListItemDao> feedLogIn(@Field("user_name") String userName,
                                    @Field("password") String password,
                                    @Field("isLogin") String isLogin);

    @FormUrlEncoded
    @POST("api-uploadfile.php")
    Call<ImageUploadDao> uploadImage(@Field("title") String title,
                                     @Field("image") String image);
}

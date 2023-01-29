package com.kelompok4.wecare.viewmodel.rest;

import com.kelompok4.wecare.model.auth.AuthResponse;
import com.kelompok4.wecare.model.auth.UserSignin;
import com.kelompok4.wecare.model.auth.UserSignup;
import com.kelompok4.wecare.model.healthEducation.GetHealthEducation;
import com.kelompok4.wecare.model.location.AlwaysUpdate;
import com.kelompok4.wecare.model.location.Location;
import com.kelompok4.wecare.model.notification.DangerResponse;
import com.kelompok4.wecare.model.user.ConnectResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("healthEducations")
    Call<GetHealthEducation> getHealthEducation();

    @POST("user/signin")
    Call<AuthResponse> signin(@Body UserSignin userSignin);

    @POST("user/signup")
    Call<AuthResponse> signup(@Body UserSignup userSignup);

    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    @POST("user/getLoggedinUser")
    Call<AuthResponse> getLoggedinUser(@Header("Authorization") String auth, @Body AlwaysUpdate alwaysUpdate);

    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    @POST("user/userDetails/{id}")
    Call<AuthResponse> getUserDetails(@Path("id") String id, @Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    @POST("notification/dangerSignal")
    Call<DangerResponse> sendDangerSignal(@Header("Authorization") String auth, @Body Location location);

    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    @PATCH("user/connect/{id}")
    Call<ConnectResponse> connectAccount(@Path("id") String id, @Header("Authorization") String auth);


}

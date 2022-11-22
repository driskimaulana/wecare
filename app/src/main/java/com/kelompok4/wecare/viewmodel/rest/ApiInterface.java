package com.kelompok4.wecare.viewmodel.rest;

import com.kelompok4.wecare.model.auth.AuthResponse;
import com.kelompok4.wecare.model.auth.UserSignin;
import com.kelompok4.wecare.model.healthEducation.GetHealthEducation;
import com.kelompok4.wecare.model.healthEducation.HealthEducation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @GET("healthEducations")
    Call<GetHealthEducation> getHealthEducation();

    @FormUrlEncoded
    @POST("user/signin")
    Call<AuthResponse> signIn();

}

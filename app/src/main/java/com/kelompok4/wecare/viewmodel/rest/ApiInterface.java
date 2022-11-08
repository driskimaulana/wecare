package com.kelompok4.wecare.viewmodel.rest;

import com.kelompok4.wecare.model.healthEducation.GetHealthEducation;
import com.kelompok4.wecare.model.healthEducation.HealthEducation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("healthEducations")
    Call<GetHealthEducation> getHealthEducation();

}

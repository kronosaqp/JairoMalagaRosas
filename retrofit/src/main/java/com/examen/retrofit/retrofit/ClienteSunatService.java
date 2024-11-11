package com.examen.retrofit.retrofit;

import com.examen.retrofit.aggregates.response.ResponseSunatRucExt;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ClienteSunatService {
    @GET("v2/sunat/ruc/full")
    Call<ResponseSunatRucExt> getInfoSunat(@Header("Authorization") String token,
                                           @Query("numero") String ruc);
}

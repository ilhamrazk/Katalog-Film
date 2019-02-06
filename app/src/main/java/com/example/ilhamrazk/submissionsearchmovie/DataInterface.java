package com.example.ilhamrazk.submissionsearchmovie;

import com.example.ilhamrazk.submissionsearchmovie.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataInterface {

    @GET("movie/popular")
    Call<Response> callMovie(@Query("api_key") String query);

    @GET("search/movie?language=en-US")
    Call<Response> getSearchMovie(@Query("query") String query, @Query(("api_key")) String api_key);

}

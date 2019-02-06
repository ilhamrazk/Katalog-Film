package com.example.ilhamrazk.submissionsearchmovie.networks;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/movie?language=en-US")
    Call<Response> getSearchMovie(@Query("query") String query, @Query(("api_key")) String api_key);
}

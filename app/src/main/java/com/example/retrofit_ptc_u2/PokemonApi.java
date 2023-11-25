package com.example.retrofit_ptc_u2;

import com.example.retrofit_ptc_u2.model.Pokemon;
import com.example.retrofit_ptc_u2.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonApi {

    @GET("pokemon")
    Call<Pokemon> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<Result> getPokemonDetails(@Path("id") int id);
}




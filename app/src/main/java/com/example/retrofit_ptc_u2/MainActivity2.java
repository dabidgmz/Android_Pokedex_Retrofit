package com.example.retrofit_ptc_u2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofit_ptc_u2.adapter.PokemonAdapter;
import com.example.retrofit_ptc_u2.model.Pokemon;
import com.example.retrofit_ptc_u2.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.rcPokemones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear instancia de la interfaz PokemonApi
        PokemonApi pokemonApi = retrofit.create(PokemonApi.class);

        // Realizar llamada a la API para obtener la lista de Pokémon
        Call<Pokemon> call = pokemonApi.getPokemonList(20, 0);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    List<Result> pokemonList = response.body().getResults();
                    pokemonAdapter = new PokemonAdapter(pokemonList);
                    recyclerView.setAdapter(pokemonAdapter);
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                // Manejar el error
            }
        });

    }
}
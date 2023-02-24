package com.example.jokeapp

import retrofit2.Response
import retrofit2.http.GET


interface JokeApi{

    @GET(value = "/random_joke")
    suspend fun getJoke(): Response<JokeItem>
}
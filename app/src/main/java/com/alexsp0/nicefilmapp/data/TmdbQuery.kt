package com.alexsp0.nicefilmapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbQuery {
    @GET("movie?api_key=73df235f5cb8302518d3645a4ba68838&language=ru-RU&sort_by=popularity.desc")
    fun loadFilms (@Query("include_adult") query : String) : Call<TmdbObject>

}
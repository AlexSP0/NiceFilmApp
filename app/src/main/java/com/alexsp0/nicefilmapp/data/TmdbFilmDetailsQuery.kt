package com.alexsp0.nicefilmapp.data

import com.alexsp0.nicefilmapp.data.db.FilmDetailsObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbFilmDetailsQuery {
    @GET("{id}?api_key=73df235f5cb8302518d3645a4ba68838&language=en-US")
    fun loadFilmDetails (@Path("id") id : String) : Call<FilmDetailsObject>
}
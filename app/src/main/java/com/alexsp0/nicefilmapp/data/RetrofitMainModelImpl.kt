package com.alexsp0.nicefilmapp.data

import android.content.Context
import com.alexsp0.nicefilmapp.data.db.FilmDetailsObject
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenter
import com.alexsp0.nicefilmapp.utils.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList
 const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500"

class RetrofitMainModelImpl (private var presenter: MainFilmsPresenter, context: Context) : MainModel(context) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/discover/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var query: TmdbQuery = retrofit.create(TmdbQuery::class.java)
    private var films : ArrayList<Film> = arrayListOf<Film>()

    override fun getFilms() {
        var adultLoad = "false"
        if (super.loadAdultSetting() == true) adultLoad = "true"
        query.loadFilms(adultLoad).enqueue(object : Callback<TmdbObject> {
            override fun onResponse(call: Call<TmdbObject>, response: Response<TmdbObject>) {
                val body = response.body()
                if(response.isSuccessful && body != null) {
                    val tmdb = response.body()
                    films.clear()
                    if(tmdb?.results != null ) {
                        for (f in tmdb.results) {
                            films.add(f)
                        }
                        getCoverUrl()
                        presenter.LoadedFilms(films)
                    }
                } else {
                    //Something wrong
                }

            }
            override fun onFailure(call: Call<TmdbObject>, t: Throwable) {
                //Oops
            }
        })
    }

    override fun getFilmDetails(id: Int) {
        val url = "https://api.themoviedb.org/3/movie/"
        val retrofitDetails = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val idFilm = id.toString()
        val detailsQuery : TmdbFilmDetailsQuery = retrofitDetails.create(TmdbFilmDetailsQuery::class.java)
        detailsQuery.loadFilmDetails(idFilm).enqueue(object : Callback<FilmDetailsObject>{
            override fun onResponse(call: Call<FilmDetailsObject>, response: Response<FilmDetailsObject>
            ) {
                val answer = response.body()
                if(response.isSuccessful && answer != null) {
                    presenter.setFilmCountry(answer.production_countries[0].iso_3166_1)
                }
            }
            override fun onFailure(call: Call<FilmDetailsObject>, t: Throwable) {
                //OOppss
            }

        })

    }

    fun getCoverUrl () {
        for(f in films) {
            f.imagePath = IMAGE_PATH + f.poster_path
        }
    }
}

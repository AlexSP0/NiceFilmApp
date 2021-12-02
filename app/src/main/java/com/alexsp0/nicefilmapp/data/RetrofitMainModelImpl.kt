package com.alexsp0.nicefilmapp.data

import androidx.lifecycle.MutableLiveData
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenter
import com.alexsp0.nicefilmapp.utils.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
 public val IMAGE_PATH = "https://image.tmdb.org/t/p/w500"

class RetrofitMainModelImpl (private var presenter: MainFilmsPresenter) : MainModel {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/discover/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var query: TmdbQuery = retrofit.create(TmdbQuery::class.java)
    private var films : ArrayList<Film> = arrayListOf<Film>()
    init {
        this.presenter = presenter
    }
    override fun getFilms() {
        query.loadFilms().enqueue(object : Callback<TmdbObject> {
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
    fun getCoverUrl () {
        for(f in films) {
            f.imagePath = IMAGE_PATH + f.poster_path
        }
    }
}

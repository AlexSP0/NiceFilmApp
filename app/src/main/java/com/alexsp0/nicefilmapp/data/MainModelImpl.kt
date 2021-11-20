package com.alexsp0.nicefilmapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenter
import com.alexsp0.nicefilmapp.utils.Film
import com.alexsp0.nicefilmapp.utils.Genre
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

//https://api.themoviedb.org/3/discover/movie?api_key=73df235f5cb8302518d3645a4ba68838&language=ru-RU&sort_by=popularity.desc&with_genres=28

@RequiresApi(Build.VERSION_CODES.N)
class MainModelImpl(private var presenter: MainFilmsPresenter) : MainModel {
    init {
        this.presenter = presenter
    }

    private val apiKey = "73df235f5cb8302518d3645a4ba68838"
    private val TmdbUrl = "https://api.themoviedb.org/3/movie/"
    private val language = "ru-RU"
    private var films: ArrayList<Film> = arrayListOf()
    private lateinit var genres: MutableMap<String, Int>
    private val gson by lazy { Gson() }
    override fun getFilms() {
        val myService : ExecutorService = Executors.newFixedThreadPool(2)
        val result = myService.submit(Callable<Array<Film>> {
            var connection: HttpURLConnection? = null;
            var loadedFilms : Array<Film>? = null
            try {
                val url = URL("https://api.themoviedb.org/3/discover/movie?api_key=73df235f5cb8302518d3645a4ba68838&language=ru-RU&sort_by=popularity.desc")
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.readTimeout = 5_000
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                var result = reader.readLines().toString()
                val resJson = gson.fromJson(result, Array<TmdbObject>::class.java)
                val tmdbAnswer = resJson.get(0)
                loadedFilms = tmdbAnswer.results
            } catch (e : Exception) {
                return@Callable null
            }
            return@Callable loadedFilms
        })
        if(result.get()!=null) {
            for (f in result.get()){
                films.add(f)
            }
            presenter.LoadedFilms(films)
        }
//        var f1 = arrayListOf<Film>()
//        val film = Film(
//            false, arrayOf(0), 0, "asdasd", 0.0f, "",
//            "1", false, 0.0f, 0
//        )
//        f1.add(film)
        presenter.LoadedFilms(films)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    public fun loadGenres() {
//        Thread {
//            var connection : HttpsURLConnection? = null
//            try {
//                val uri = URL("https://api.themoviedb.org/3/genre/movie/list?api_key=73df235f5cb8302518d3645a4ba68838&language=ru-RU")
//                connection = uri.openConnection() as HttpsURLConnection
//                connection.requestMethod = "GET"
//                connection.readTimeout = 5000
//                val reader = BufferedReader(InputStreamReader(connection.inputStream))
//                var result = reader.readLines().toString()
//                val resJson = gson.fromJson(result, Array<Genre>::class.java)
//                for (genre in resJson) {
//                    genres.put(genre.name, genre.id.toInt())
//                }
//            } finally {
//                connection?.disconnect()
//            }
//        }
//    }
    }
}
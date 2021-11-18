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
import javax.net.ssl.HttpsURLConnection

//https://api.themoviedb.org/3/discover/movie?api_key=73df235f5cb8302518d3645a4ba68838&language=ru-RU&sort_by=popularity.desc&with_genres=28

@RequiresApi(Build.VERSION_CODES.N)
class MainModelImpl(private var presenter: MainFilmsPresenter) : MainModel {
    init {
        this.presenter = presenter
        getFilms()
        loadGenres()
    }
    private val apiKey = "73df235f5cb8302518d3645a4ba68838"
    private val TmdbUrl = "https://api.themoviedb.org/3/movie/"
    private val language = "ru-RU"
    private lateinit var films : MutableList<Film>
    private lateinit var genres : MutableMap<String, Int>
    private val gson by lazy { Gson() }
    override fun getFilms() {
            Thread { ///!!!! Не работает.
                var connection: HttpURLConnection? = null;
                try {
                    val url = URL("https://api.themoviedb.org/3/discover/movie?api_key=73df235f5cb8302518d3645a4ba68838&language=ru-RU&sort_by=popularity.desc")
                    connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.readTimeout = 5_000
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    var result = reader.readLines().toString()
                    val resJson = gson.fromJson(result, Array<Film>::class.java)
                    for (film in resJson) {
                        films.add(film)
                    }
                    presenter.LoadedFilms(films)
                } catch (e : Exception) {

                }
            }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    public fun loadGenres() {
        Thread {
            var connection : HttpsURLConnection? = null
            try {
                val uri = URL("https://api.themoviedb.org/3/genre/movie/list?api_key=73df235f5cb8302518d3645a4ba68838&language=ru-RU")
                connection = uri.openConnection() as HttpsURLConnection
                connection.requestMethod = "GET"
                connection.readTimeout = 5000
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                var result = reader.readLines().toString()
                val resJson = gson.fromJson(result, Array<Genre>::class.java)
                for (genre in resJson) {
                    genres.put(genre.name, genre.id.toInt())
                }
            } finally {
                connection?.disconnect()
            }
        }
    }
}
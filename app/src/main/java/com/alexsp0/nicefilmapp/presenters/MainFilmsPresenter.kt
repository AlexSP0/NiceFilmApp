package com.alexsp0.nicefilmapp.presenters

import com.alexsp0.nicefilmapp.utils.Film

interface MainFilmsPresenter {
    fun getFilms()
    fun LoadedFilms(films : ArrayList<Film>)
    fun getAdultSettings() : Boolean
    fun setAdultSettings(showAdult : Boolean)
    fun getShowNotification() : Boolean
    fun setShowNotification(showNotification : Boolean)
    fun getFilmNote(id : Int) : String
    fun setFilmNote(id : Int, note : String)
    fun getFilmDetails ( id : Int)
    fun setFilmCountry(country : String)
}
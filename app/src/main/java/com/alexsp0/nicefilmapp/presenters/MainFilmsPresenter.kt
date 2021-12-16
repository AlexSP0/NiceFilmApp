package com.alexsp0.nicefilmapp.presenters

import com.alexsp0.nicefilmapp.utils.Film

interface MainFilmsPresenter {
    fun getFilms()
    fun LoadedFilms(films : ArrayList<Film>)
    fun getAdultSettings() : Boolean
    fun setAdultSettings(showAdult : Boolean)
    fun getFilmNote(id : Int) : String
    fun setFilmNote(id : Int, note : String)
}
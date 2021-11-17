package com.alexsp0.nicefilmapp.presenters

import com.alexsp0.nicefilmapp.utils.Film

interface MainFilmsPresenter {
    fun getFilms() : MutableList<Film>
    fun updateFilms() : MutableList<Film>
}
package com.alexsp0.nicefilmapp.data

import com.alexsp0.nicefilmapp.utils.Film

interface MainModel {
    fun getFilms() : MutableList<Film>
}
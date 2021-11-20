package com.alexsp0.nicefilmapp.ui.main

import com.alexsp0.nicefilmapp.utils.Film

interface MainFilmsFragment {
    fun updateFilms(films : ArrayList<Film>)
    fun showProgressbar()
    fun hideProgressbar()
}
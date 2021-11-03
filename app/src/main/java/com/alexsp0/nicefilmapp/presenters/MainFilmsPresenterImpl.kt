package com.alexsp0.nicefilmapp.presenters

import com.alexsp0.nicefilmapp.ui.main.MainFilmsFragment

class MainFilmsPresenterImpl : MainFilmsPresenter {

    private lateinit var fragment : MainFilmsFragment

    public fun attachView(fragment : MainFilmsFragment) {
        this.fragment = fragment
    }

    override fun getFilms() {
        // get films from model
    }

    override fun updateFilms() {
        //tell MainFragment to update Film on screen
    }
}
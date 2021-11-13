package com.alexsp0.nicefilmapp.presenters

import com.alexsp0.nicefilmapp.data.MainModelImpl
import com.alexsp0.nicefilmapp.ui.main.MainFilmsFragment

class MainFilmsPresenterImpl : MainFilmsPresenter {

    private var fragment : MainFilmsFragment? = null
    private var model : MainModelImpl = MainModelImpl()

    fun attachView(fragment : MainFilmsFragment) {
        this.fragment = fragment
    }
    fun detach() {
        this.fragment = null
    }

    override fun getFilms() {
        // get films from model
    }

    override fun updateFilms() {
        //tell MainFragment to update Film on screen
    }

}
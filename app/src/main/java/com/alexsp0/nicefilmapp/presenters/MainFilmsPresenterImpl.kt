package com.alexsp0.nicefilmapp.presenters

import android.os.Build
import androidx.annotation.RequiresApi
import com.alexsp0.nicefilmapp.data.MainModelImpl
import com.alexsp0.nicefilmapp.ui.main.MainFilmsFragment
import com.alexsp0.nicefilmapp.utils.Film

class MainFilmsPresenterImpl : MainFilmsPresenter {

    private var fragment : MainFilmsFragment? = null
    private lateinit var films : ArrayList<Film>
    @RequiresApi(Build.VERSION_CODES.N)
    private var model : MainModelImpl = MainModelImpl(this)

    fun attachView(fragment : MainFilmsFragment) {
        this.fragment = fragment
    }
    fun detach() {
        this.fragment = null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getFilms() {
        fragment?.showProgressbar()
        model.getFilms()
    }

    override fun LoadedFilms(films: ArrayList<Film>) {
        fragment?.hideProgressbar()
        fragment?.updateFilms(films)
    }


}
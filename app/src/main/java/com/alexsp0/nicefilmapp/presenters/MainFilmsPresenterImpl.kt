package com.alexsp0.nicefilmapp.presenters

import android.os.Build
import androidx.annotation.RequiresApi
import com.alexsp0.nicefilmapp.data.MainModelImpl
import com.alexsp0.nicefilmapp.ui.main.MainFilmsFragment
import com.alexsp0.nicefilmapp.utils.Film

class MainFilmsPresenterImpl : MainFilmsPresenter {

    private var fragment : MainFilmsFragment? = null
    @RequiresApi(Build.VERSION_CODES.N)
    private var model : MainModelImpl = MainModelImpl(this)

    fun attachView(fragment : MainFilmsFragment) {
        this.fragment = fragment
    }
    fun detach() {
        this.fragment = null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getFilms() : MutableList<Film> {
        fragment?.showProgressbar()
        return model.getFilms()
    }

    override fun updateFilms() {
        //tell MainFragment to update Film on screen
    }

}
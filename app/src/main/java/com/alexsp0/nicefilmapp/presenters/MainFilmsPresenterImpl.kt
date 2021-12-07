package com.alexsp0.nicefilmapp.presenters

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.alexsp0.nicefilmapp.data.MainModelImpl
import com.alexsp0.nicefilmapp.data.RetrofitMainModelImpl
import com.alexsp0.nicefilmapp.ui.main.MainFilmsFragment
import com.alexsp0.nicefilmapp.utils.Film

class MainFilmsPresenterImpl(context: Context) : MainFilmsPresenter {
    private var fragment : MainFilmsFragment? = null
    @RequiresApi(Build.VERSION_CODES.N)
    private var model : RetrofitMainModelImpl = RetrofitMainModelImpl(this, context)

    fun attachView(fragment : MainFilmsFragment) {
        this.fragment = fragment
    }
    fun detach() {
        this.fragment = null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getFilms() {
        //fragment?.showProgressbar()
        model.getFilms()
    }

    override fun LoadedFilms(films: ArrayList<Film>) {
        //fragment?.hideProgressbar()
        fragment?.updateFilms(films)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getAdultSettings(): Boolean = model.loadAdultSetting()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun setAdultSettings(showAdult: Boolean) {
        model.saveAdultSetting(showAdult)
        this.getFilms()
    }

}
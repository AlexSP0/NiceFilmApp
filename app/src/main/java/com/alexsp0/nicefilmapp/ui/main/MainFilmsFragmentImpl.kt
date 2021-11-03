package com.alexsp0.nicefilmapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexsp0.nicefilmapp.R
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenterImpl

class MainFilmsFragmentImpl : Fragment(), MainFilmsFragment  {
    private lateinit var presenter : MainFilmsPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainFilmsPresenterImpl()
        presenter.attachView(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_films, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =  MainFilmsFragmentImpl()
    }

    override fun updateFilms() {
        //Update films recyclerview
    }
}
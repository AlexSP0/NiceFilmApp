package com.alexsp0.nicefilmapp.ui.main

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.alexsp0.nicefilmapp.MainActivity
import com.alexsp0.nicefilmapp.R
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenterImpl
import com.alexsp0.nicefilmapp.utils.Film

class MainFilmsFragmentImpl : Fragment(), MainFilmsFragment  {
    private lateinit var presenter : MainFilmsPresenterImpl
    private lateinit var recyclerView1 : RecyclerView
    private lateinit var recyclerView2 : RecyclerView
    private lateinit var adapter1: MainFilmsFragmentAdapter
    private lateinit var adapter2: MainFilmsFragmentAdapter
    private lateinit var films : MutableList<Film>
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainFilmsPresenterImpl()
        presenter.attachView(this)
        initFilms()
    }



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_main_films, container, false)
        initRecyclersView(view)
        presenter.getFilms()
        return view
    }
    private fun initRecyclersView(view: View) {
        progressBar= view.findViewById(R.id.progrees_bar_load_films)
        recyclerView1 = view.findViewById(R.id.recyclerview1)
        recyclerView1.hasFixedSize()
        adapter1 = MainFilmsFragmentAdapter(films)
        adapter1.setItemClickListener(object : MainFilmsFragmentAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                openCurrentFilmFragment(position)
            }
        })
        recyclerView1.adapter = adapter1
        //Second recyclerview
        recyclerView2 = view.findViewById(R.id.recyclerview2)
        recyclerView2.hasFixedSize()
        adapter2 = MainFilmsFragmentAdapter(films)
        adapter2.setItemClickListener(object : MainFilmsFragmentAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                openCurrentFilmFragment(position)
            }
        })
    }
    private fun initFilms() {
        films = mutableListOf<Film>()
        val film = Film(false, arrayOf(0), 0, "", 0.0f, "",
        "", false, 0.0f, 0)
        films.add(film)
    }
    private fun openCurrentFilmFragment(position: Int) {
        val act = activity as MainActivity
        act.loadFragment(CurrentFilmFragment.newInstance(films[position]))
    }

    companion object {
        @JvmStatic
        fun newInstance() =  MainFilmsFragmentImpl()
    }

    override fun updateFilms(films : MutableList<Film>) {
        this.films = films
        adapter1.notifyDataSetChanged()
        adapter2.notifyDataSetChanged()
    }

    override fun showProgressbar() {
        progressBar.visibility=ProgressBar.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility=ProgressBar.INVISIBLE
    }


}
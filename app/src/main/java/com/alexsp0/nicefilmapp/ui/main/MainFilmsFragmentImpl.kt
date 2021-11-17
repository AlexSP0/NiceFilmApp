package com.alexsp0.nicefilmapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.alexsp0.nicefilmapp.MainActivity
import com.alexsp0.nicefilmapp.R
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenterImpl
import com.alexsp0.nicefilmapp.utils.Film

class MainFilmsFragmentImpl : Fragment(), MainFilmsFragment  {
    private lateinit var presenter : MainFilmsPresenterImpl
    private lateinit var recyclerView1 : RecyclerView
    private lateinit var recyclerView2 : RecyclerView
    private lateinit var films : ArrayList<Film>
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainFilmsPresenterImpl()
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_main_films, container, false)
        progressBar= view.findViewById(R.id.progrees_bar_load_films)
        films = presenter.getFilms()
        recyclerView1 = view.findViewById(R.id.recyclerview1)
        recyclerView1.hasFixedSize()
        val adapter1 = MainFilmsFragmentAdapter(films)
        adapter1.setItemClickListener(object : MainFilmsFragmentAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                openCurrentFilmFragment(position)
            }
        })
        recyclerView1.adapter = adapter1
        recyclerView2 = view.findViewById(R.id.recyclerview2)
        recyclerView2.hasFixedSize()
        val adapter2 = MainFilmsFragmentAdapter(films)
        adapter2.setItemClickListener(object : MainFilmsFragmentAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                openCurrentFilmFragment(position)
            }
        })
        recyclerView2.adapter = adapter2
        return view
    }

    private fun openCurrentFilmFragment(position: Int) {
        val act = activity as MainActivity
        act.loadFragment(CurrentFilmFragment.newInstance(films[position]))
    }

    companion object {
        @JvmStatic
        fun newInstance() =  MainFilmsFragmentImpl()
    }

    override fun updateFilms() {
        //Update films recyclerview
    }

    override fun showProgressbar() {
        progressBar.visibility=ProgressBar.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility=ProgressBar.INVISIBLE
    }


}
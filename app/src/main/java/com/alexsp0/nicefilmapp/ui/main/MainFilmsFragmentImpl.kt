package com.alexsp0.nicefilmapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainFilmsPresenterImpl()
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Пока заглушки для инициализации фрагментов и фильмов.
        //При клике на элемент откроется экран фильмов, но пока replace
        val view : View = inflater.inflate(R.layout.fragment_main_films, container, false)
        recyclerView1 = view.findViewById(R.id.recyclerview1)
        recyclerView1.hasFixedSize()
        initFilms() //Заглушка
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

    private fun initFilms() {
        films = ArrayList()
        val film1=Film("name1", "genre", R.drawable.film)
        val film2=Film("name2", "genre", R.drawable.film)
        val film3=Film("name3", "genre", R.drawable.film)
        val film4=Film("name4", "genre", R.drawable.film)
        val film5=Film("name5", "genre", R.drawable.film)
        val film6=Film("name6", "genre", R.drawable.film)
        val film7=Film("name7", "genre", R.drawable.film)
        val film8=Film("name8", "genre", R.drawable.film)
        films.add(film1)
        films.add(film2)
        films.add(film3)
        films.add(film4)
        films.add(film5)
        films.add(film6)
        films.add(film7)
        films.add(film8)
    }

    companion object {
        @JvmStatic
        fun newInstance() =  MainFilmsFragmentImpl()
    }

    override fun updateFilms() {
        //Update films recyclerview
    }

}
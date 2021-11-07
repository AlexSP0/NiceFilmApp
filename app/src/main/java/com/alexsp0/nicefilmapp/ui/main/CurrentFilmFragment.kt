package com.alexsp0.nicefilmapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alexsp0.nicefilmapp.R
import com.alexsp0.nicefilmapp.utils.Film

class CurrentFilmFragment(film: Film) : Fragment() {
    private var film : Film = film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_film, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view : View) {
        var name : TextView = view.findViewById(R.id.fragment_current_film_name)
        var genre : TextView = view.findViewById(R.id.fragment_current_film_genre)
        var image : ImageView = view.findViewById(R.id.fragment_current_film_cover_image)
        name.text = film.name
        genre.text=film.genre
        image.setImageResource(film.cover)
    }

    companion object {
        @JvmStatic
        fun newInstance(film : Film) =  CurrentFilmFragment(film)
    }
}
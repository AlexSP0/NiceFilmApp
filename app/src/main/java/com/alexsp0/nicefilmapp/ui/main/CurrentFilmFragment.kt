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

class CurrentFilmFragment(private var film: Film) : Fragment() {

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
        val name : TextView = view.findViewById(R.id.fragment_current_film_name)
        val genre : TextView = view.findViewById(R.id.fragment_current_film_genre)
        val image : ImageView = view.findViewById(R.id.fragment_current_film_cover_image)
        name.text = film.name
        genre.text=film.genre
        image.setImageResource(film.cover)
    }

    companion object {
        @JvmStatic
        fun newInstance(film : Film) =  CurrentFilmFragment(film)
    }
}
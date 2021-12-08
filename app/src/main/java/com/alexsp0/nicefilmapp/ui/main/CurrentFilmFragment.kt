package com.alexsp0.nicefilmapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alexsp0.nicefilmapp.R
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenter
import com.alexsp0.nicefilmapp.utils.Film

class CurrentFilmFragment(private var film: Film, private var presenter : MainFilmsPresenter) : Fragment() {

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
        val note : EditText = view.findViewById(R.id.fragment_current_film_note)
        val saveButton : Button = view.findViewById(R.id.setting_fragment_ok_button)
        name.text = film.title
        genre.text=film.original_language
        image.setImageResource(R.drawable.film)
        note.setText(presenter.getFilmNote(film.id))
        saveButton.setOnClickListener {
            presenter.setFilmNote(film.id, note.text.toString())
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(film : Film, presenter : MainFilmsPresenter) =
            CurrentFilmFragment(film, presenter)
    }
}
package com.alexsp0.nicefilmapp.ui.main

import android.content.Context
import com.alexsp0.nicefilmapp.utils.Film
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alexsp0.nicefilmapp.R
import android.widget.TextView

class MainFilmsFragmentAdapter(private val films: List<Film>) :
    RecyclerView.Adapter<MainFilmsFragmentAdapter.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = films[position]
        holder.image.setImageResource(film.cover)
        holder.genre.text = film.genre
        holder.name.text = film.name
    }

    override fun getItemCount(): Int {
        return films.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val name: TextView
        val genre: TextView

        init {
            image = view.findViewById(R.id.film_recycler_view_item_cover)
            name = view.findViewById(R.id.film_recycler_view_item_name)
            genre = view.findViewById(R.id.film_recycler_view_item_genre)
            view.setOnClickListener(View.OnClickListener {
                this@MainFilmsFragmentAdapter.itemClickListener.onItemClick(view, layoutPosition)
            })
        }
    }

    fun setItemClickListener(c: OnItemClickListener) {
        itemClickListener = c
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}
package com.alexsp0.nicefilmapp.ui.main

import com.alexsp0.nicefilmapp.utils.Film
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alexsp0.nicefilmapp.R
import android.widget.TextView

class MainFilmsFragmentAdapter(var films : ArrayList<Film>) :
    RecyclerView.Adapter<MainFilmsFragmentAdapter.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = films[position]
        holder.image.setImageResource(R.drawable.film)
        holder.genre.text = film.original_language
        holder.name.text = film.title
    }

    override fun getItemCount(): Int {
        return films.size
    }
    public fun update(array :ArrayList<Film>) {
        this.films = array
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.film_recycler_view_item_cover)
        val name: TextView = view.findViewById(R.id.film_recycler_view_item_name)
        val genre: TextView = view.findViewById(R.id.film_recycler_view_item_genre)

        init {
            view.setOnClickListener {
                this@MainFilmsFragmentAdapter.itemClickListener.onItemClick(view, layoutPosition)
            }
        }
    }

    fun setItemClickListener(c: OnItemClickListener) {
        itemClickListener = c
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}
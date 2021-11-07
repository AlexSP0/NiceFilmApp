package com.alexsp0.nicefilmapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexsp0.nicefilmapp.R;
import com.alexsp0.nicefilmapp.utils.Film;

import java.util.List;

public class MainFilmsFragmentAdapter extends RecyclerView.Adapter<MainFilmsFragmentAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Film> films;
    public  MainFilmsFragmentAdapter.OnItemClickListener itemClickListener;

    public MainFilmsFragmentAdapter (Context context, List<Film> films) {
        this.films = films;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.film_recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Film film =  films.get(position);
        holder.image.setImageResource(film.getCover());
        holder.genre.setText(film.getGenre());
        holder.name.setText(film.getName());
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView name, genre;
        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.film_recycler_view_item_cover);
            name = view.findViewById(R.id.film_recycler_view_item_name);
            genre = view.findViewById(R.id.film_recycler_view_item_genre);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainFilmsFragmentAdapter.this.itemClickListener.onItemClick(view, getLayoutPosition());
                }
            });
        }

    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    interface OnItemClickListener {
        void  onItemClick(View view, int position);
    }
}

package com.alexsp0.nicefilmapp.utils;

public class Film {
    private String name;
    private String genre;
    private int cover;

    public Film(String name, String genre, int imageResourceId) {
        this.name = name;
        this.genre = genre;
        this.cover = imageResourceId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }
}

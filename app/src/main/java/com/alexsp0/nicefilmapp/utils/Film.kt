package com.alexsp0.nicefilmapp.utils

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdrop_path : String,
    @SerializedName("genre_ids")
    var genre_ids : Array<Int>,
    @SerializedName("id")
    var id : Int,
    @SerializedName("original_language")
    var original_language : String,
    @SerializedName("popularity")
    var popularity : Float,
    @SerializedName("poster_path")
    var poster_path : String,
    @SerializedName("title")
    var title : String,
    @SerializedName("video")
    var video : Boolean = false,
    @SerializedName("vote_average")
    var vote_average : Float,
    @SerializedName("vote_count")
    var vote_count : Int,
    var imagePath : String )

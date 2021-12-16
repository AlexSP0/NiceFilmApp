package com.alexsp0.nicefilmapp.data

import com.alexsp0.nicefilmapp.utils.Film
import com.google.gson.annotations.SerializedName

data class TmdbObject (
    @SerializedName("page")
    var page : Int,
    @SerializedName("results")
    var results : Array<Film>,
    @SerializedName("total_pages")
    var total_pages : Int,
    @SerializedName("total_results")
    var total_result : Int)

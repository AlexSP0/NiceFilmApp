package com.alexsp0.nicefilmapp.data

import com.alexsp0.nicefilmapp.utils.Film

data class TmdbObject (var page : Int, var results : Array<Film>, var total_pages : Int,
    var total_result : Int)

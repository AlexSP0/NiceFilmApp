package com.alexsp0.nicefilmapp.utils

data class Film(var adult: Boolean, var genre_ids : Array<Int>,
                var id : Int, var original_language : String,
                var popularity : Float, var poster_path : String,
                var title : String, var video : Boolean = false,
                var vote_average : Float, var vote_count : Int)

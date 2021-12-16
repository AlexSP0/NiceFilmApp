package com.alexsp0.nicefilmapp.data.db

import com.google.gson.annotations.SerializedName

data class FilmDetailsObject(
    @SerializedName("production_countries")
    val production_countries : Array<ProductionsCountriesObject>
)

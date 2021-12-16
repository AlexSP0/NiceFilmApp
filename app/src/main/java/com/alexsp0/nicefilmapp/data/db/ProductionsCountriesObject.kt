package com.alexsp0.nicefilmapp.data.db

import com.google.gson.annotations.SerializedName

data class ProductionsCountriesObject(
    @SerializedName("iso_3166_1")
    val iso_3166_1 : String,
    @SerializedName("name")
    val name : String
)

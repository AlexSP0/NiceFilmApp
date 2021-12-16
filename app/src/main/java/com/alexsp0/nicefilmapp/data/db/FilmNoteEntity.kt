package com.alexsp0.nicefilmapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmNoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val filmId : Int,
    val changeTime : Long,
    val note : String
)
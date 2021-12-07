package com.alexsp0.nicefilmapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FilmNoteEntity::class), version = 1, exportSchema = false)
abstract class FilmNotesDb : RoomDatabase() {
    abstract fun getNotesDao() : NotesDao
}
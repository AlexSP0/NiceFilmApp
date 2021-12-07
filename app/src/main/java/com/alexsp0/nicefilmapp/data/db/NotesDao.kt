package com.alexsp0.nicefilmapp.data.db

import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM FilmNoteEntity WHERE filmId LIKE :id")
    fun getFilmById(id : Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (entity : FilmNoteEntity)

    @Update
    fun update(entity : FilmNoteEntity)
}
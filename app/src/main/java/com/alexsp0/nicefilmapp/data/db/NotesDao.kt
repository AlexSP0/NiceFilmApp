package com.alexsp0.nicefilmapp.data.db

import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM FilmNoteEntity")
    fun getFilmById(id : Long) : List<FilmNoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (entity : FilmNoteEntity)

    @Update
    fun update(entity : FilmNoteEntity)
}
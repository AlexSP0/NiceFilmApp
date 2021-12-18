package com.alexsp0.nicefilmapp.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.alexsp0.nicefilmapp.App
import com.alexsp0.nicefilmapp.data.db.FilmNoteEntity

private const val SHARED_PREFERENCE_FILE = "file"
private const val IS_ADULT_KEY = "adult"
private const val IS_SHOW_NOTIFICATION = "show_notification"


abstract class MainModel(context: Context) {
    private var context = context
    private var dbFilmNotesDao = App.getNotesDao()
    abstract fun getFilms()
    open fun loadAdultSetting() : Boolean = context
        .getSharedPreferences(SHARED_PREFERENCE_FILE, MODE_PRIVATE )
            .getBoolean(IS_ADULT_KEY, false)

    open fun saveAdultSetting(showAdult : Boolean) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_ADULT_KEY, showAdult)
        editor.apply()
    }
    open fun getFilmNote(id: Int) : String {
        val film = dbFilmNotesDao.getFilmById(id)
        if(film.isEmpty()) {
            return " "
        } else return film[0].note
    }
    open fun setFilmNote(id: Int, note : String) {
        val time = java.util.Calendar.getInstance()
        val film = FilmNoteEntity(0, id, time.timeInMillis, note)
        dbFilmNotesDao.insert(film)
    }
    open fun loadShowNotification() : Boolean = context
        .getSharedPreferences(SHARED_PREFERENCE_FILE, MODE_PRIVATE)
        .getBoolean(IS_SHOW_NOTIFICATION, false)
    open fun saveShowNotification(showNotification : Boolean) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_SHOW_NOTIFICATION, showNotification)
        editor.apply()
    }
    abstract fun getFilmDetails(id:Int)
}
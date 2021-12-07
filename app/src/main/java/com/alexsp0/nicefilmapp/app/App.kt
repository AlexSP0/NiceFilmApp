package com.alexsp0.nicefilmapp.app

import android.app.Application
import androidx.room.Room
import com.alexsp0.nicefilmapp.data.db.FilmNotesDb
import com.alexsp0.nicefilmapp.data.db.NotesDao

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val appInstance = this
    }
    companion object {
        private var appInstance : App? = null
        private var db : FilmNotesDb? = null
        private const val DB_NAME = "FilmsNotes.db"

        fun getNotesDao() : NotesDao {
            if(db == null) {
                synchronized(FilmNotesDb::class.java) {
                    if(db == null) {
                        if(appInstance == null) throw IllegalStateException("App is not exits")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            FilmNotesDb::class.java,
                            DB_NAME).build()
                    }
                }
            }
            return db!!.getNotesDao()
        }
    }
}
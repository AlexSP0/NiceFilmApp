package com.alexsp0.nicefilmapp.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

private const val SHARED_PREFERENCE_FILE = "file"
private const val IS_ADULT_KEY = "adult"


abstract class MainModel(context: Context) {
    var context = context
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
}
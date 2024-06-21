package com.example.noteapp

import android.app.Application
import androidx.room.Room
import com.example.noteapp.data.db.AppDatabase
import com.example.noteapp.utils.SharedUtil

class App : Application() {

    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = SharedUtil()
        sharedPreferences.unit(this)
        getInstance()
    }

    fun getInstance(): AppDatabase? {       //  функция для инициализации базы данных
        if (appDatabase == null) {
            appDatabase = applicationContext.let {
                Room.databaseBuilder(it, AppDatabase::class.java, "note.database")
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build()
            }
        }
        return appDatabase
    }
}
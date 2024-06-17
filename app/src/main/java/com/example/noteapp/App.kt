package com.example.noteapp

import android.app.Application
import com.example.noteapp.utils.SharedUtil

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = SharedUtil()
        sharedPreferences.init(this)
    }
}
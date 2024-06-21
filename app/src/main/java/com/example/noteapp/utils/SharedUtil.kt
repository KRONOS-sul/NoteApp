package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedUtil() {

    private lateinit var sharedPreferences: SharedPreferences

    fun unit(context: Context) {    //  передача в контекст экземпляр для инициализации SharedPreferences
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var title: String?      //  свойство для доступа к title
        get() = sharedPreferences.getString("title", "")
        set(value) = sharedPreferences.edit().putString("title", value).apply()

    var isBoardDone: Boolean
        get() = sharedPreferences.getBoolean("board", false)
        set(value) = sharedPreferences.edit().putBoolean("board", value).apply()

//    fun save(key: String, value: String) {
//        sharedPreferences.edit().putString(key, value).apply()        это всё Gemini придумал
//    }
//
//    fun get(key: String): String? {
//        return sharedPreferences.getString(key, null)
//    }
//
//    fun remove(key: String) {
//        sharedPreferences.edit().remove(key).apply()
//    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
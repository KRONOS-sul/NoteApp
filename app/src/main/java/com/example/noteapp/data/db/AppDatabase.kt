package com.example.noteapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.data.db.daos.NoteDao
import com.example.noteapp.data.models.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 2
)      // Массив из моделек(можно добавлять много моделей), версия базы данных
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}
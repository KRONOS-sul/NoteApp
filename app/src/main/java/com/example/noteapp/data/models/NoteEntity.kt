package com.example.noteapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteEntity")       //Указываем Room что это его сущность
data class NoteEntity(
    val title: String,
    val content: String,
    val date: String,
    val backgroundColor: Int,
    val textColor: Int
) {
    @PrimaryKey(autoGenerate = true)        //Указываем Room что это первичный ключ, автогенерация id, проверяет id на уникальность
    var id: Int = 0         //Обязательно var
}
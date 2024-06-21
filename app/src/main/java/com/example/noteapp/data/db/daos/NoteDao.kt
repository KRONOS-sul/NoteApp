package com.example.noteapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapp.data.models.NoteEntity

@Dao                    //Room понимает что это его интерфейс
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)    //решение при получении двух одинаковых id
    fun insert(noteEntity: NoteEntity)

    @Query("SELECT * FROM noteEntity")      //запрос на получение данных из базы данных
    fun getAll(): LiveData<List<NoteEntity>>       //возвращает список данных в реальном времени

}
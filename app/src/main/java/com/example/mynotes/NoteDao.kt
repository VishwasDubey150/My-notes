package com.example.mynotes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes():LiveData<List<Note>>

}
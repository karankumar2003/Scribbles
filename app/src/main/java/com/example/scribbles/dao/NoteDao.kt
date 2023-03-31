package com.example.scribbles.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.scribbles.models.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    suspend fun getAllNotes() : LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Update
    suspend fun updateNote(note : Note)

}
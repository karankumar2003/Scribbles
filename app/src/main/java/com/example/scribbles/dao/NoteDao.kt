package com.example.scribbles.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.scribbles.models.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY time DESC")
    fun getAllNotes() : LiveData<List<Note>>
    // Do not use suspend with liveData

    @Query("select * from notes_table where priority=:priority order by time DESC")
    fun getPriorityNotes(priority:Int) : LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    @Query("delete from notes_table where id=:id")
    suspend fun delete(id:Int)

    @Update
    suspend fun updateNote(note : Note)

}
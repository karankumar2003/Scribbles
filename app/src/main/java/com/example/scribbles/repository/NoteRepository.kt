package com.example.scribbles.repository

import androidx.lifecycle.LiveData
import com.example.scribbles.dao.NoteDao
import com.example.scribbles.models.Note

class NoteRepository(private val noteDao: NoteDao) {

    fun getAllNotes(): LiveData<List<Note>> = noteDao.getAllNotes()
    suspend fun delete(id:Int) = noteDao.delete(id)
    suspend fun insert(note: Note) = noteDao.insert(note)
    suspend fun update(note:Note) = noteDao.updateNote(note)



}
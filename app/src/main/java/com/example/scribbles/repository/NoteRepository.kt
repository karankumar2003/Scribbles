package com.example.scribbles.repository

import androidx.lifecycle.LiveData
import com.example.scribbles.dao.NoteDao
import com.example.scribbles.models.Note

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun getAllNotes(): LiveData<List<Note>> = noteDao.getAllNotes()
    suspend fun delete(note:Note) = noteDao.delete(note)
    suspend fun insert(note: Note) = noteDao.insert(note)
    suspend fun update(note:Note) = noteDao.updateNote(note)



}
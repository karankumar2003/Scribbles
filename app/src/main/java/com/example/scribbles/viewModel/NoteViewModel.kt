package com.example.scribbles.viewModel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.scribbles.database.NotesDatabase
import com.example.scribbles.models.Note
import com.example.scribbles.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application:Application) : AndroidViewModel(application ) {

    private val noteDao = NotesDatabase.getDatabase(application).getNoteDao()
    private val repository = NoteRepository(noteDao)

    fun insert(note: Note)= viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun delete(id:Int)= viewModelScope.launch(Dispatchers.IO) {
        repository.delete(id)
    }
    fun update(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
    fun getAllNotes() = repository.getAllNotes()
    fun getPriorityNotes(priority:Int) = repository.getPriorityNotes(priority)
}
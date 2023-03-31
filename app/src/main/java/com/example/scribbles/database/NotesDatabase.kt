package com.example.scribbles.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.scribbles.dao.NoteDao
import com.example.scribbles.models.Note

@Database([Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        private var Instance: NotesDatabase? = null

        fun getDatabase(context : Context) : NotesDatabase{
            return Instance ?: synchronized(this){
                Instance ?: Room.databaseBuilder(context.applicationContext,NotesDatabase::class.java,"notes_database")
                    .build()
                    .also {
                        Instance = it
                    }



            }
        }

    }
}
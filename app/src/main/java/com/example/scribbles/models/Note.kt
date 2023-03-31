package com.example.scribbles.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title:String,
    val subtitle: String,
    val text: String,
    val priority: Int,
    val date:String
)
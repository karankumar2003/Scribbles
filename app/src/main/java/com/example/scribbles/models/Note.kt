package com.example.scribbles.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title:String,
    val text: String,
    val priority: Int,
    val date:String
) : Parcelable
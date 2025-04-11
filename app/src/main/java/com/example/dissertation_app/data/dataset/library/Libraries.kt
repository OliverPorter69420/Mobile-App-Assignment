package com.example.dissertation_app.data.dataset.library

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libraries")
data class Libraries (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val libraryName: String
)
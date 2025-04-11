package com.example.dissertation_app.data.dataset.savedLibraries

import androidx.room.Entity

@Entity(tableName = "saved_libraries")
data class SavedLibraries (
    val libraryId: Int,
    val bookID: Int
)
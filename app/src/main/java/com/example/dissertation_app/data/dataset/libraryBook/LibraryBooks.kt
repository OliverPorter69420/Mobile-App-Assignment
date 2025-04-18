package com.example.dissertation_app.data.dataset.libraryBook

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "library_books")
data class LibraryBooks (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bookId: String,
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String
)
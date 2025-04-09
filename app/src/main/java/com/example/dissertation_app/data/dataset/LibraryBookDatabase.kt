package com.example.dissertation_app.data.dataset

import androidx.room.Database

@Database(entities = [LibraryBooks::class], version = 1, exportSchema = false)
abstract class LibraryBookDatabase {
    abstract fun libraryBooksDao(): LibraryBooksDao
}
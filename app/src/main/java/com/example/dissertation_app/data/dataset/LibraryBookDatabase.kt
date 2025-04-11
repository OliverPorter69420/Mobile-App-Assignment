package com.example.dissertation_app.data.dataset

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.data.dataset.library.LibrariesDao
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooksDao
import com.example.dissertation_app.data.dataset.savedLibraries.SavedLibraries
import com.example.dissertation_app.data.dataset.savedLibraries.SavedLibrariesDao

@Database(entities = [LibraryBooks::class, Libraries::class, SavedLibraries::class], version = 2, exportSchema = false)
abstract class LibraryBookDatabase : RoomDatabase() {
    abstract fun libraryBooksDao(): LibraryBooksDao
    abstract fun savedLibrariesDao(): SavedLibrariesDao
    abstract fun librariesDao(): LibrariesDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryBookDatabase? = null

        fun getDatabase(context: Context): LibraryBookDatabase {
            return INSTANCE ?: synchronized(this) {
                 Room.databaseBuilder(
                    context,
                    LibraryBookDatabase::class.java,
                    "library_book_database"
                 )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
package com.example.dissertation_app.data.dataset

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooksDao

@Database(entities = [LibraryBooks::class], version = 1, exportSchema = false)
abstract class LibraryBookDatabase : RoomDatabase() {
    abstract fun libraryBooksDao(): LibraryBooksDao

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
package com.example.dissertation_app.data.dataset.libraryBook

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LibraryBooksDao {

    @Query("SELECT * FROM library_books WHERE bookId = :id")
    fun getLibraryBookById(id: String): LibraryBooks?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLibraryBook(libraryBook: LibraryBooks)

    @Update
    fun updateLibraryBook(libraryBook: LibraryBooks)

    @Delete
    fun deleteLibraryBook(libraryBook: LibraryBooks)
}
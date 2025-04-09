package com.example.dissertation_app.data.dataset

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryBooksDao {
    @Query("SELECT * FROM library_books")
    fun getAllLibraryBooks(): Flow<List<LibraryBooks>>

    @Query("SELECT * FROM library_books WHERE id = :id")
    fun getLibraryBookById(id: Int): Flow<LibraryBooks?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLibraryBook(libraryBook: LibraryBooks)

    @Update
    fun updateLibraryBook(libraryBook: LibraryBooks)

    @Delete
    fun deleteLibraryBook(libraryBook: LibraryBooks)
}
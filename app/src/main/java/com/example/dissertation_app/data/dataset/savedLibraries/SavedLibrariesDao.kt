package com.example.dissertation_app.data.dataset.savedLibraries

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks

@Dao
interface SavedLibrariesDao {

    @Query("SELECT * FROM libraries INNER JOIN saved_libraries ON libraries.id = saved_libraries.libraryId WHERE bookID = :bookId")
    fun getBooksLibrary(bookId: Int): List<Libraries>

    @Query("SELECT * FROM library_books INNER JOIN saved_libraries ON library_books.id = saved_libraries.bookID WHERE libraryId = :libraryId")
    fun getBooksInLibrary(libraryId: Int): List<LibraryBooks>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveBookInLibrary(savedLibrary: SavedLibraries)

    @Query("DELETE FROM saved_libraries WHERE libraryId = :libraryId AND bookID = :bookID")
    fun removeBookFromLibrary(libraryId: Int, bookID: Int)
}
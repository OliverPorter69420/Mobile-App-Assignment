package com.example.dissertation_app.data.dataset.savedLibraries

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedLibrariesDao {
    @Query("SELECT * FROM saved_libraries WHERE libraryId = :libraryId")
    fun getBooksInLibrary(libraryId: Int): List<SavedLibraries>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveBookInLibrary(savedLibrary: SavedLibraries)

    @Delete
    fun removeBookFromLibrary(savedLibrary: SavedLibraries)
}
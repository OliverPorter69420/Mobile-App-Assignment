package com.example.dissertation_app.data.dataset.library

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LibrariesDao {
    @Query("SELECT * FROM libraries")
    fun getAllLibraries(): List<Libraries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLibrary(library: Libraries)

    @Update
    fun updateLibrary(library: Libraries)

    @Query("DELETE FROM libraries WHERE id = :id")
    fun deleteLibrary(id: Int)
}
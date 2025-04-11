package com.example.dissertation_app.data.dataset.savedLibraries

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks

@Entity(tableName = "saved_libraries",
    primaryKeys = ["libraryId", "bookID"],
    foreignKeys = (
            [
                ForeignKey(
                    entity = Libraries::class,
                    parentColumns = ["id"],
                    childColumns = ["libraryId"],
                    onDelete = ForeignKey.CASCADE
                ),
                ForeignKey(
                    entity = LibraryBooks::class,
                    parentColumns = ["id"],
                    childColumns = ["bookID"],
                    onDelete = ForeignKey.CASCADE
                )
            ]
    ),
    indices = [Index(value = ["libraryId", "bookID"])]
)
data class SavedLibraries (
    val libraryId: Int,
    val bookID: Int
)
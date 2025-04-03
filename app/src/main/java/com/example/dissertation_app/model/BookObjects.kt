package com.example.dissertation_app.model

import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val kind: String?,
    val totalItems: Int?,
    val items: List<BookObjects>?
)

@Serializable
data class BookObjects(
    val kind: String?,          // e.g., "books#volume"
    val id: String?,
    val etag: String?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo? // Nested object for book details
)

@Serializable
data class VolumeInfo(
    val title: String? = "currently unavailable",
    val subtitle: String? = "currently unavailable",
    val authors: List<String>? = null,
    val publisher: String? = "currently unavailable",
    val publishedDate: String? = "currently unavailable",
    val description: String? = "currently unavailable",
    val industryIdentifiers: List<IndustryIdentifier>? = null,
    val readingModes: ReadingModes? = null,
    val previewLink: String? = "currently unavailable",
    val pageCount: Int? = null,
    val printType: String? = "currently unavailable",
    val categories: List<String>? = null,
    val averageRating: Double? = 0.0,
    val ratingsCount: Int? = 0,
    val maturityRating: String? = "currently unavailable",
    val imageLinks: ImageLinks? = null,
    val language: String? = "currently unavailable",
    val infoLink: String? = "currently unavailable",
    val canonicalVolumeLink: String? = "currently unavailable"
)

@Serializable
data class IndustryIdentifier(
    val type: String? = null,
    val identifier: String? = null
)

@Serializable
data class ReadingModes(
    val text: Boolean? = null,
    val image: Boolean? = null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null,
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null
)
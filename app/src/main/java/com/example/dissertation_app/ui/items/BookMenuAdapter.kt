package com.example.dissertation_app.ui.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dissertation_app.R
import com.example.dissertation_app.model.BookObjects
import com.example.dissertation_app.ui.screen.BookDescriptionLocation

class BookGridAdapter(private val bookUiState: BookUiState) :
    ListAdapter<String, BookGridAdapter.BookViewHolder>(DiffCallback) {

    class BookViewHolder(itemView: View, private val bookUiState: BookUiState) :
        RecyclerView.ViewHolder(itemView) {
        private val bookImageView: ImageView = itemView.findViewById(R.id.bookImageView)
        private val textTitleView: TextView = itemView.findViewById(R.id.bookTitleTextView)

        fun bind(bookPosition: Int) {
            val bookDetails = extractBook(bookPosition)
            val thumbnailUrl = extractThumbnailUrl(bookPosition)
            itemView.setOnClickListener { BookDescriptionLocation.bookInformation(bookDetails) }

            bookImageView.load(thumbnailUrl) {
                when (bookUiState) {
                    is BookUiState.Success -> {
                        crossfade(true)
                    }

                    is BookUiState.Start -> {
                        placeholder(R.drawable.loading_img)
                    }

                    is BookUiState.Empty -> {
                        placeholder(R.drawable.loading_img)
                    }

                    is BookUiState.Loading -> {
                        placeholder(R.drawable.loading_img)
                    }

                    is BookUiState.Error -> {
                        error(R.drawable.ic_broken_image)
                    }
                }
            }

            textTitleView.text = bookDetails?.volumeInfo?.title.toString()
        }

        private fun extractThumbnailUrl(bookPosition: Int) : String{
            return if (bookUiState is BookUiState.Success) {
                bookUiState.thumbnails[bookPosition]
            } else {
                ""
            }
        }

        private fun extractBook(bookPosition: Int): BookObjects? {
            return if (bookUiState is BookUiState.Success) {
                bookUiState.bookSearch?.get(bookPosition)
            } else {
                null
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return BookViewHolder(itemView, bookUiState)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(position)
    }

    companion object {
        private val DiffCallback = object : ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}
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
import com.example.dissertation_app.ui.items.BookGridAdapter.BookViewHolder
import com.example.dissertation_app.ui.screen.BookDescriptionLocation

class BookGridAdapter(
    private val bookUiState: BookUiState,
    private var books: List<BookObjects>,
    private var thumbnailUrls: List<String>
) : ListAdapter<String, BookViewHolder>(DiffCallback) {

    inner class BookViewHolder(itemView: View, private val bookUiState: BookUiState) :
        RecyclerView.ViewHolder(itemView) {
        private val bookImageView: ImageView = itemView.findViewById(R.id.bookImageView)
        private val textTitleView: TextView = itemView.findViewById(R.id.bookTitleTextView)

        fun bind(bookPosition: Int) {
            when (bookUiState) {
                is BookUiState.Success -> {
                    itemView.setOnClickListener { BookDescriptionLocation.bookInformation(books[bookPosition]) }

                    bookImageView.load(thumbnailUrls[bookPosition]) {
                        crossfade(true)
                        placeholder(R.drawable.loading_img)
                        error(R.drawable.ic_broken_image)
                    }

                    textTitleView.text = books[bookPosition].volumeInfo?.title.toString()
                }

                BookUiState.Empty -> {
                    textTitleView.text = "No books found"
                }
                BookUiState.Error -> {
                    textTitleView.text = "Error loading books"
                }

                BookUiState.Loading -> {
                    textTitleView.text = "Images are loading"
                }
                BookUiState.Start -> {
                    textTitleView.text = ""
                }
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

    override fun getItemCount(): Int {
        return this.books.size
    }

    fun setLibraryBooks(books : List<BookObjects>) {
        this.books = books
    }

    fun setThumbnailUrls(thumbnailUrls : List<String>) {
        this.thumbnailUrls = thumbnailUrls
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
package com.example.dissertation_app.ui.items

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.example.dissertation_app.R
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.ui.items.LibraryBookAdapter.LibraryViewHolder
import com.example.dissertation_app.ui.screen.BookDescriptionLocation

class LibraryBookAdapter (
    private var uiState: LibraryBookUiState,
    private var libraryBooks: List<LibraryBooks>
) : ListAdapter<String, LibraryViewHolder>(DiffCallback){

    inner class LibraryViewHolder(itemView: View, private val uiState: LibraryBookUiState) :
        RecyclerView.ViewHolder(itemView) {

        private val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        private val authorTextView = itemView.findViewById<TextView>(R.id.authorTextView)
        private val descriptionTextView = itemView.findViewById<TextView>(R.id.editTextText)
        private val thumbnailImage = itemView.findViewById<ImageView>(R.id.bookImageView)

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            itemView.setOnClickListener {
                /*todo add the on click listener*/
            }

            when(uiState) {
                is LibraryBookUiState.Success -> {
                    titleTextView.text = libraryBooks[position].title
                    authorTextView.text = libraryBooks[position].author
                    descriptionTextView.text = libraryBooks[position].description
                    thumbnailImage.load(libraryBooks[position].imageUrl) {
                        crossfade(true)
                        placeholder(R.drawable.loading_img)
                        error(R.drawable.ic_broken_image)
                    }
                }

                LibraryBookUiState.Empty -> titleTextView.text = "No books in library"
                LibraryBookUiState.Error -> titleTextView.text = "Error loading books"
                LibraryBookUiState.FunctionSuccess -> titleTextView.text = "Function Success"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_library_book, parent, false)
        return LibraryViewHolder(view, uiState)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return this.libraryBooks.size
    }

    fun setLibraryBooks(books : List<LibraryBooks>) {
        this.libraryBooks = books
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
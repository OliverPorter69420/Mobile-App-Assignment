package com.example.dissertation_app.ui.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dissertation_app.R
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks

class LibraryBookAdapter (
    private val context: Context,
    private var libraryBooks: List<LibraryBooks>?
) : RecyclerView.Adapter<LibraryBookAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_library_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val libraryBook = libraryBooks?.get(position)
        holder.bind(libraryBook)
    }

    override fun getItemCount(): Int {
        return libraryBooks?.size ?: 0
    }

    fun setLibraryBooks(libraryBooks: List<LibraryBooks>?) {
        this.libraryBooks = libraryBooks
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(libraryBooks: LibraryBooks?) {
            itemView.findViewById<TextView>(R.id.titleTextView).text = libraryBooks?.title
            itemView.findViewById<TextView>(R.id.authorTextView).text = libraryBooks?.author
            itemView.findViewById<TextView>(R.id.editTextText).text = libraryBooks?.description
        }
    }
}
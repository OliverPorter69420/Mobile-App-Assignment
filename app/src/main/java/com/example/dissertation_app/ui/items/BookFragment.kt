package com.example.dissertation_app.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dissertation_app.R

class BookFragment(
    private var viewModel: LibraryBookViewModel
) : Fragment(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LibraryBookAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var uiState: LibraryBookUiState = viewModel.libraryBookUiState

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_book, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = when (uiState) {

            is LibraryBookUiState.Success -> {
                LibraryBookAdapter(requireContext(), (uiState as LibraryBookUiState.Success).libraryBooks)
            }

            else -> {
                LibraryBookAdapter(requireContext(), null)
            }
        }

        recyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (uiState) {
            is LibraryBookUiState.Success -> viewModel.libraryBooks.observe(viewLifecycleOwner, Observer { books ->
                adapter.setLibraryBooks(books)
            })

            LibraryBookUiState.Empty -> TODO()
            LibraryBookUiState.Error -> TODO()
        }
    }
}



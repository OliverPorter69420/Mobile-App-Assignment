package com.example.dissertation_app.ui.items
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dissertation_app.R

class BookGridActivity (
    private val viewModel: BookViewModel
) : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookGridAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var uiState: BookUiState = viewModel.bookUiState

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_item, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
        adapter = BookGridAdapter(uiState)

        return null
    }
}
package com.example.dissertation_app.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.dissertation_app.R
import com.example.dissertation_app.databinding.FragmentMainContentLayoutBinding
import com.example.dissertation_app.ui.items.BookGridScreen

class MainContentFragment : Fragment() {

    private lateinit var binding: FragmentMainContentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val composeView = ComposeView(requireContext())
        composeView.setContent {
            val viewModel = SearchLocation.getBookViewModel()
            BookGridScreen(
                viewModel = viewModel,
                fragmentManager = childFragmentManager,
                onFragmentCreated = {
                    /*todo add a back function*/
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        return composeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun showChildFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.child_fragment_container, fragment)
            .commit()
    }
}
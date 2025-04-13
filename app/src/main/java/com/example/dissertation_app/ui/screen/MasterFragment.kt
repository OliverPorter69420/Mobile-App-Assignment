package com.example.dissertation_app.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dissertation_app.R
import com.example.dissertation_app.databinding.FragmentMainContentBinding

class MasterFragment : Fragment() {

    private lateinit var binding: FragmentMainContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initially display one of the child Fragments
        showChildFragment(FragmentA()) // Replace with your initial fragment
    }

    // Function to display a child Fragment
    fun showChildFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.child_fragment_container, fragment) // Replace with the ID of the container in fragment_main_content.xml
            .commit() // For simplicity, we don't add to the back stack here, but you might want to.
    }
}
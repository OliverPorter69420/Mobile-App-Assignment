package com.example.dissertation_app.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.dissertation_app.R
import com.example.dissertation_app.databinding.FragmentMainContentLayoutBinding

object FragmentActivity : AppCompatActivity() {
    val mainContentFragment = supportFragmentManager.findFragmentById(R.id.main_content_container) as? MainContentFragment

    fun showChildFragment(fragment: Fragment) {
        mainContentFragment?.showChildFragment(fragment)
    }

    fun getFunctionManager(): FragmentManager {
        return supportFragmentManager
    }
}

class MainContentFragment : Fragment() {

    private lateinit var binding: FragmentMainContentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainContentLayoutBinding.inflate(inflater, container, false)

        binding.navigationArrow.setOnClickListener {
            /*todo*/
        }

        return binding.root
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
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ui.navigateUp
import com.example.dissertation_app.R
import com.example.dissertation_app.databinding.FragmentMainContentBinding

class MainContentFragment : Fragment() {

    private lateinit var binding: FragmentMainContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainContentBinding.inflate(inflater, container, false)

        binding.navigationArrow.setOnClickListener {

            findNavController().navigateUp()
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
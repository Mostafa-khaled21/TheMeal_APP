package  com.example.themeal_app.Data.UI
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.themeal_app.R

class SignUpFragment : Fragment() {

    lateinit var signUpButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        signUpButton = view.findViewById(R.id.button)

        signUpButton.setOnClickListener {

         //   findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        return view
    }
}

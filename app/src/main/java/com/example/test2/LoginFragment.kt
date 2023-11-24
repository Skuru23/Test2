package com.example.test2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.test2.controller.HomePageController
import com.example.test2.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val controller = HomePageController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logoImage.setImageResource(R.drawable.kafka)

        binding.loginButton.setOnClickListener{
            val username:String = binding.usenameEditText.text.toString()
            val password:String = binding.passwordEditText.text.toString()
            if(controller.loginHandler(username, password)){
                val action = LoginFragmentDirections.actionLoginFragmentToProfile()
                view.findNavController().navigate(action)
            }
        }
    }
}
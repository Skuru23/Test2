package com.example.test2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.test2.controller.HomePageController
import com.example.test2.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginF : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val controller = HomePageController()
    private lateinit var auth: FirebaseAuth
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
                val action = LoginFDirections.actionLoginFragmentToFragment3()
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
    }
}
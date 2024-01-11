package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test2.ViewModel.CourseProgressViewModel
import com.example.test2.controller.CheckSpeechListener
import com.example.test2.databinding.FragmentCheckBinding

private const val ARG_PARAM1 = "something_to_check"

class Check : Fragment() {
    private lateinit var something: String
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var speechRecognizerIntent: Intent
    private var _binding: FragmentCheckBinding? = null

    private lateinit var sharedViewModel: CourseProgressViewModel
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            something = it.getString(ARG_PARAM1).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val somethingV: TextView = binding.checkInfo
        val checkBtn: Button = binding.checkBtn
        val resText: TextView = binding.textRecog
        val ipaText: TextView = binding.textResult

        somethingV.text = something

        sharedViewModel = ViewModelProvider(requireActivity()).get(CourseProgressViewModel::class.java)

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(view.context)
        speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        val speechListener =
            CheckSpeechListener(
                errText = resText, resultText = resText,
                ipaText = ipaText, lifecycleScope = lifecycleScope,
                rightText = something, viewModel = sharedViewModel
            )
        speechRecognizer.setRecognitionListener(speechListener)

        checkBtn.setOnClickListener {
            ipaText.text = ""
            speechRecognizer.startListening(speechRecognizerIntent)
            resText.text = "Listening..."
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::speechRecognizer.isInitialized) {
            speechRecognizer.destroy()
        }
    }
}
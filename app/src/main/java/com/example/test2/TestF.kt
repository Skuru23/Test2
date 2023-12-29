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
import androidx.lifecycle.lifecycleScope
import com.example.test2.controller.SpeechListener
import com.example.test2.databinding.FragmentTestBinding

class TestF : Fragment() {

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var speechRecognizerIntent: Intent
    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkBtn: Button = binding.checkBtn
        val resText: TextView = binding.textRecog
        val ipaText: TextView = binding.textIPAResult
        val rightText = binding.testTextEditText


        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(view.context)
        speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        val speechListener =
            SpeechListener(
                errTextView = resText, resultTextView = resText,
                ipaText = ipaText, lifecycleScope = lifecycleScope,
                rightText = rightText
            )
        speechRecognizer.setRecognitionListener(speechListener)

        checkBtn.setOnClickListener {
            if (rightText.text.toString().isEmpty()) {
                resText.text = "No text to check"
            } else {
                ipaText.text = ""
                speechRecognizer.startListening(speechRecognizerIntent)
                resText.text = "Listening..."
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (::speechRecognizer.isInitialized) {
            speechRecognizer.destroy()
        }
    }
}


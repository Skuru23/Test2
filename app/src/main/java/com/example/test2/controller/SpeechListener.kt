package com.example.test2.controller

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope

class SpeechListener(
    errTextView: TextView? = null,
    resultTextView: TextView,
    private val rightText: EditText,
    private val ipaText: TextView,
    private val lifecycleScope: LifecycleCoroutineScope
) : RecognitionListener {
    private var errText: TextView? = errTextView
    private var resText: TextView = resultTextView
    override fun onReadyForSpeech(params: Bundle?) {}

    override fun onBeginningOfSpeech() {}

    override fun onRmsChanged(rmsdB: Float) {}

    override fun onBufferReceived(buffer: ByteArray?) {}

    override fun onEndOfSpeech() {

    }

    override fun onError(error: Int) {
        if (error == 7) {
            errText?.text = "Not regconize speech"
        } else
            errText?.text = "Error: $error"
    }

    override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (!matches.isNullOrEmpty()) {
            val result = matches[0]
            if (result.equals(rightText.text.toString(), ignoreCase = true)) {
                resText.text = "Correct"
            } else {
                resText.text = "Wrong, you got some mistakes"
                ipaText.text = "Pronunciation: "
                IPAChecker.checkTwoSentence(
                    rightText.text.toString(),
                    result,
                    ipaText,
                    lifecycleScope
                )
            }
        }
    }


    override fun onPartialResults(partialResults: Bundle?) {}

    override fun onEvent(eventType: Int, params: Bundle?) {}
}
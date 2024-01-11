package com.example.test2.controller

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.test2.ViewModel.CourseProgressViewModel

class CheckSpeechListener(
    var errText: TextView?=null,
    var resultText: TextView,
    var ipaText: TextView,
    var lifecycleScope: LifecycleCoroutineScope,
    var rightText: String,
    var viewModel: CourseProgressViewModel
) : RecognitionListener {
    override fun onReadyForSpeech(params: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
    }

    override fun onRmsChanged(rmsdB: Float) {
    }

    override fun onBufferReceived(buffer: ByteArray?) {
    }

    override fun onEndOfSpeech() {
    }

    override fun onError(error: Int) {
        if(error == 7){
            errText?.text = "Not regconize speech"
        }else
            errText?.text = "Error: $error"
    }

    override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if(!matches.isNullOrEmpty()){
            val textRecognized = matches[0]
            if(textRecognized.equals(rightText, ignoreCase = true)){
                resultText.text = "Correct"
                viewModel.increaseProgress();
            }else {
                resultText.text = "Wrong, you got some mistakes"
                ipaText.text = "Pronunciation: "
                IPAChecker.checkTwoSentence(
                    rightText,
                    textRecognized,
                    ipaText,
                    lifecycleScope
                )

            }
        }
    }

    override fun onPartialResults(partialResults: Bundle?) {
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
    }
}
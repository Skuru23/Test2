package com.example.test2.controller

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.test2.model.IPAResponse
import com.example.test2.network.IPAapi
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.Locale

object IPAChecker {

    private val gson = Gson()
    fun checkTwoSentence(
        rightSentence: String,
        speechSentence: String,
        resultText: TextView,
        lifecycleScope: LifecycleCoroutineScope
    ) {
        resultText.setTextColor(Color.parseColor("red"))
        val rightSenTokenList =
            rightSentence.lowercase(Locale.getDefault()).split(" ").filter { it.isNotBlank() }
        val speechSenTokenList =
            speechSentence.lowercase(Locale.getDefault()).split(" ").filter { it.isNotBlank() }

        val minSize = minOf(rightSenTokenList.size, speechSenTokenList.size)

        lifecycleScope.launch {
            try {
                for (i in 0 until minSize) {
                    val rightIPA =
                        getIPAFromResponse(IPAapi.retrofitService.getIPA(rightSenTokenList[i]))
                    if (rightIPA.isEmpty()) {
                        resultText.append("//")
                        continue
                    } else {
                        val speechIPA =
                            getIPAFromResponse(IPAapi.retrofitService.getIPA(speechSenTokenList[i]))
                        if (rightIPA == speechIPA) {
                            resultText.append(rightIPA)
                            resultText.append(" ")
                            continue
                        } else {
                            val mismatchPoint = ipaCheck(speechIPA, rightIPA)
                            println("$i : ok: $rightIPA notok: $speechIPA")
                            showResult(resultText, rightIPA, mismatchPoint)
                        }
                    }
                }

                for (i in minSize until rightSenTokenList.size) {
                    val rightIPA =
                        getIPAFromResponse(IPAapi.retrofitService.getIPA(rightSenTokenList[i]))
                    showResult(resultText, rightIPA, listOf(-1))
                }

            } catch (e: Exception) {
                resultText.text = "Error : ${e.message}"
            }
        }


    }

    private fun getIPAFromResponse(response: String): String {
        val resObj = gson.fromJson(response, IPAResponse::class.java)

        return if (resObj.status) {
            resObj.response
        } else ""
    }

    private fun showResult(ipaText: TextView, ipa: String, mismatchPoint: List<Int>) {
        val showIPA = SpannableString(ipa)
        if (mismatchPoint[0] == -1) {
            showIPA.setSpan(UnderlineSpan(), 0, ipa.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            ipaText.append(showIPA)
            ipaText.append(" ")
        } else {
            for (position in mismatchPoint) {
                if (position >= 0 && position < ipa.length) {
                    showIPA.setSpan(
                        UnderlineSpan(),
                        position,
                        position + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
            ipaText.append(showIPA)
        }
        ipaText.append(" ")
    }

    private fun ipaCheck(rightIPA: String, wordIPA: String): List<Int> {
        val mismatchPoint: MutableList<Int> = mutableListOf()

        val minLength = minOf(rightIPA.length, wordIPA.length)

        for (i in 0 until minLength) {
            if (wordIPA[i] != rightIPA[i]) {
                mismatchPoint.add(i)
            }
        }
        for (i in minLength until wordIPA.length) {
            mismatchPoint.add(i)
        }
        return mismatchPoint
    }
}
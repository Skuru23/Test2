package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.speech.RecognizerIntent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale
import java.util.Objects

class MainActivity : AppCompatActivity() {

//    private lateinit var bottomNav: BottomNavigationView
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottomNav.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.home_nav -> {
//                    loadFragment(HomePage())
//                    true
//                }
//
//                R.id.talk_nav -> {
//                    loadFragment(TestFragment())
//                    true
//                }
//
//                R.id.profile_nav -> {
//                    loadFragment(Profile())
//                    true
//                }
//
//                else -> {false}
//            }
//        }
//    }
//
//    private fun loadFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, fragment)
//        transaction.commit()
//    }

    lateinit var outputTV: TextView
    lateinit var micIV: ImageView

    // on below line we are creating a constant value
    private val REQUEST_CODE_SPEECH_INPUT = 1
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        outputTV = findViewById(R.id.idTVOutput)
        micIV = findViewById(R.id.idIVMic)

        // on below line we are adding on click
        // listener for mic image view.
        micIV.setOnClickListener {
            // on below line we are calling speech recognizer intent.
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            // on below line we are passing language model
            // and model free form in our intent
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )

            // on below line we are passing our
            // language as a default language.
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )

            // on below line we are specifying a prompt
            // message as speak to text on below line.
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

            // on below line we are specifying a try catch block.
            // in this block we are calling a start activity
            // for result method and passing our result code.
            try {
                @Suppress("DEPRECATION")
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                // on below line we are displaying error message in toast
                Toast
                    .makeText(
                        this@MainActivity, " " + e.message,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // in this method we are checking request
        // code with our result code.
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            // on below line we are checking if result code is ok
            if (resultCode == RESULT_OK && data != null) {

                // in that case we are extracting the
                // data from our array list
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                // on below line we are setting data
                // to our output text view.
                outputTV.setText(
                    Objects.requireNonNull(res)[0]
                )
            }
        }
    }

}
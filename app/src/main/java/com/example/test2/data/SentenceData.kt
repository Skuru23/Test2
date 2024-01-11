package com.example.test2.data

import com.example.test2.model.Sentence

object SentenceData {
    private val _sentenceList: List<Sentence> = listOf(
        Sentence("Good morning", "Hello"),
        Sentence("Good afternoon", "Hello"),
        Sentence("Good evening", "Hello"),
        Sentence("How are you", "Hello"),
        Sentence("What's up", "Hello"),
        Sentence("How's it going", "Hello"),
        Sentence("Nice to meet you", "Hello"),
        Sentence("Welcome", "Hello"),
        Sentence("Hey there", "Hello"),
        Sentence("Long time no see", "Hello"),
        Sentence("What's new", "Hello"),
        Sentence("How have you been", "Hello"),
        Sentence("Greetings", "Hello"),
        Sentence("Password security is paramount online", "Computer"),
        Sentence("Efficient file organization aids productivity", "Computer"),
        Sentence("Choose reliable antivirus for protection", "Computer"),
        Sentence("Regular updates ensure optimal performance", "Computer"),
        Sentence("Upgrade your graphics card for gaming", "Computer"),
        Sentence("Backup files to prevent data loss", "Computer"),
        Sentence("Explore new software for efficiency", "Computer"),
        Sentence("Adopt a loving pet today", "Pet"),
        Sentence("Train your pet with care", "Pet"),
        Sentence("Healthy food for happy pets", "Pet"),
        Sentence("Visit the vet regularly", "Pet"),
        Sentence("Make a comfy spot for your pet", "Pet"),
        Sentence("Keep your pet clean and content", "Pet")
    )

    fun getSentenceByCourse(course: String): List<Sentence> {
        return _sentenceList.filter { it.course == course }
    }
}
package com.example.test2.data

import com.example.test2.model.WordItem

object WordData {
    private val _wordList: List<WordItem> = listOf(
        WordItem("Hello", "Hello"),
        WordItem("Hi", "Hello"),
        WordItem("Morning", "Hello"),
        WordItem("Afternoon", "Hello"),
        WordItem("Evening", "Hello"),
        WordItem("Welcome", "Hello"),
        WordItem("Greetings", "Hello"),
        WordItem("Computer", "Computer"),
        WordItem("Laptop", "Computer"),
        WordItem("Desktop", "Computer"),
        WordItem("Monitor", "Computer"),
        WordItem("Keyboard", "Computer"),
        WordItem("Mouse", "Computer"),
        WordItem("Motherboard", "Computer"),
        WordItem("Hardware", "Computer"),
        WordItem("Backup", "Computer"),
        WordItem("Update", "Computer"),
        WordItem("Download", "Computer"),
        WordItem("Upload", "Computer"),
        WordItem("File", "Computer"),
        WordItem("Folder", "Computer"),
        WordItem("Password", "Computer"),
        WordItem("Pet", "Pet"),
        WordItem("Adoption", "Pet"),
        WordItem("Companionship", "Pet"),
        WordItem("Training", "Pet"),
        WordItem("Nutrition", "Pet"),
        WordItem("Healthcare", "Pet"),
        WordItem("Vet", "Pet"),
        WordItem("Grooming", "Pet"),
        WordItem("Playtime", "Pet")
    )

    fun getWordForCourse(course:String): List<WordItem>{
        return _wordList.filter{it.course == course}
    }
}
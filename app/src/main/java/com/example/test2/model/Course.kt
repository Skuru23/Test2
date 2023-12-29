package com.example.test2.model

//data class Course(
//    val name:String,
//)

class Course(val name: String) {

    companion object {
        private var num = 0
    }
    val id: Int = ++num
}

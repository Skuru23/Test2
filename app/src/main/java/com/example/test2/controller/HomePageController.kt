package com.example.test2.controller

class HomePageController {
    fun loginHandler(usename:String, password:String):Boolean{
        if(usename == "123" && password == "111")
            return true
        return false
    }

}
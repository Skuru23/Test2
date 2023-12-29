package com.example.test2.data

import org.checkerframework.common.value.qual.StringVal

data class Word(
    val word:String,
    val meaning:String,
    val ipa:String = "")

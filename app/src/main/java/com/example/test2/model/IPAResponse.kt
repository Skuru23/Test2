package com.example.test2.model

data class IPAResponse(
    val response: String,
    val englishName: String,
    val status: Boolean,
    val error: Boolean
)
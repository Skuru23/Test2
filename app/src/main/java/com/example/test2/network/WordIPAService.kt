package com.example.test2.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://en-word-ipa.onrender.com/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WordIPAService {
    @GET("translateToIPA/{word}")
    suspend fun getIPA(@Path("word") word: String): String
}

object IPAapi {
    val retrofitService: WordIPAService by lazy {
        retrofit.create(WordIPAService::class.java)
    }

}
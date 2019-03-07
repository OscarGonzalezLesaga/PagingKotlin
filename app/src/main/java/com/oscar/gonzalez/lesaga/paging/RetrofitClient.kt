package com.oscar.gonzalez.lesaga.paging

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private val BASE_URL = "https://api.stackexchange.com/2.2/"
        private var mInstance: RetrofitClient? = null
        private lateinit var retrofit: Retrofit

        fun getInstance(): RetrofitClient {
            if (mInstance == null) {
                mInstance = RetrofitClient()
            }
            return mInstance as RetrofitClient
        }
    }

    init {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getApi(): Api = retrofit.create(Api::class.java)
}
package ru.arcanite.friendsposts.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper {
    private var mUserApi: UserApi

    companion object {
        private const val URL: String = "https://jsonplaceholder.typicode.com"
    }

    init {
        val gson: Gson = GsonBuilder().create()
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        mUserApi = retrofit.create(UserApi::class.java)
    }

    fun getUserApi() = mUserApi
}
package ru.arcanite.friendsposts.network

import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    class UserPlain {
        private val name: String? = null
        private val email: String? = null
        private val website: String? = null
        override fun toString(): String {
            return "UserPlain(name=$name, email=$email, website=$website)"
        }

    }

    @GET("users")
    fun getAll(): Call<List<UserPlain>>
}
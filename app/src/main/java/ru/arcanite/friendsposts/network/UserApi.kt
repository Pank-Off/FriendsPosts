package ru.arcanite.friendsposts.network

import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    class UserPlain {
        private val id: String? = null
        private val name: String? = null
        private val email: String? = null
        private val website: String? = null

        fun getName(): String? = name

        fun getEmail(): String? = email

        fun getWebsite(): String? = website

        fun getId(): String? = id

        override fun toString(): String {
            return "UserPlain(id=$id, name=$name, email=$email, website=$website)"
        }
    }

    class UserPosts {
        private val title: String? = null
        private val body: String? = null
        private val userId: Int = 0

        fun getUserId(): Int = userId

        fun getTitle(): String? = title

        fun getBody(): String? = body

        override fun toString(): String {
            return "UserPosts(title=$title, body=$body, userId=$userId)"
        }
    }

    @GET("users")
    fun getAll(): Call<List<UserPlain>>

    @GET("posts")
    fun getPosts(): Call<List<UserPosts>>
}
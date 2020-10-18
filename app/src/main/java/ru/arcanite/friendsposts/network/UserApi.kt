package ru.arcanite.friendsposts.network

import retrofit2.Call
import retrofit2.http.GET
import java.io.Serializable

interface UserApi {

    class UserPlain : Serializable {

        private val id: String? = null
        private val name: String? = null
        private val email: String? = null
        private val website: String? = null
        private var isExpanded: Boolean = false

        fun getName(): String? {
            return name
        }

        fun getEmail(): String? {
            return email
        }

        fun getWebsite(): String? {
            return website
        }

        fun getId(): String? {
            return id
        }

        override fun toString(): String {
            return "UserPlain(id=$id, name=$name, email=$email, website=$website)"
        }
    }

    class UserPosts : Serializable {
        private val title: String? = null
        private val body: String? = null
        private val userId: Int = 0

        fun getUserId(): Int {
            return userId
        }

        fun getTitle(): String? {
            return title
        }

        fun getBody(): String? {
            return body
        }

        override fun toString(): String {
            return "UserPosts(title=$title, body=$body, userId=$userId)"
        }
    }

    @GET("users")
    fun getAll(): Call<List<UserPlain>>

    @GET("posts")
    fun getPosts(): Call<List<UserPosts>>
}
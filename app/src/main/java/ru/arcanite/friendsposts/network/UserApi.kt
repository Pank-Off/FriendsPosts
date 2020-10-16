package ru.arcanite.friendsposts.network

import retrofit2.Call
import retrofit2.http.GET
import java.io.Serializable

interface UserApi {

    class UserPlain : Serializable {
        private val name: String? = null
        private val email: String? = null
        private val website: String? = null
        private var isExpanded: Boolean = false
        override fun toString(): String {
            return "UserPlain(name=$name, email=$email, website=$website)"
        }

        fun getName(): String? {
            return name
        }

        fun getEmail(): String? {
            return email
        }

        fun getWebsite(): String? {
            return website
        }

        fun setExpanded(expanded: Boolean) {
            isExpanded = expanded
        }

        fun isExpanded(): Boolean {
            return isExpanded
        }
    }

    class UserPosts : Serializable {
        private val title: String? = null
        private val body: String? = null
        private val userId: Int? = null

        fun getUserId(): Int? {
            return userId
        }

        fun getName(): String? {
            return title
        }

        fun getEmail(): String? {
            return body
        }

        override fun toString(): String {
            return "UserPosts(title=$title, body=$body)"
        }
    }

    @GET("users")
    fun getAll(): Call<List<UserPlain>>

    @GET("posts")
    fun getPosts(): Call<List<UserPosts>>
}
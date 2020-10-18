package ru.arcanite.friendsposts

import ru.arcanite.friendsposts.network.UserApi

class UserRepo {
    private var mUsersPlain: List<UserApi.UserPlain>? = ArrayList()
    private var mUsersPosts: List<UserApi.UserPosts>? = ArrayList()
    private val mUsers: List<User> = ArrayList()
    fun setUsers(users: List<UserApi.UserPlain>?) {
        mUsersPlain = users
    }

    fun setPosts(usersPosts: List<UserApi.UserPosts>?) {
        mUsersPosts = usersPosts
    }

    fun getAllData() {

    }
}
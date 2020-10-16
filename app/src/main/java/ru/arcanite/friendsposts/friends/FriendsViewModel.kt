package ru.arcanite.friendsposts.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FriendsViewModel : ViewModel() {

    private val mData: LiveData<List<User>> = UserRepo().getUser()

    fun getUsers() = mData
}
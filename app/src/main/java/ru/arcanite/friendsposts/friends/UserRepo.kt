package ru.arcanite.friendsposts.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class UserRepo {
    fun getUser(): LiveData<List<User>> {
        val users: MutableLiveData<List<User>> = MutableLiveData()

        users.value = Arrays.asList(
            User("Kirill", "yandex.ru", "site1"),
            User("Irina", "mail.ru", "site2"),
        )
        users.postValue(emptyList())
        return users
    }
}
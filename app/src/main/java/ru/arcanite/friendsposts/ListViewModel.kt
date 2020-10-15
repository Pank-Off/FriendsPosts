package ru.arcanite.friendsposts

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {

    private val mLoginState: MutableLiveData<LoginState> = MutableLiveData()

    init {
        mLoginState.value = LoginState.NONE
    }

    fun getProgress(): LiveData<LoginState> = mLoginState
    enum class LoginState {
        NONE, ERROR, IN_PROGRESS, SUCCESS, FAILED
    }
}
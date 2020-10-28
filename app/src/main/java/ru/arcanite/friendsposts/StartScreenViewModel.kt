package ru.arcanite.friendsposts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StartScreenViewModel : ViewModel() {

    private val mUserRepo: UserRepo = UserRepo()
    private val mUsers: LiveData<List<User>> = mUserRepo.getUsers()
    private val mRequestState: MediatorLiveData<RequestState> = MediatorLiveData()

    init {
        mRequestState.value = RequestState.NONE
    }

    fun getProgress(): LiveData<RequestState> = mRequestState

    fun setNoneProgress() {
        mRequestState.postValue(RequestState.NONE)
    }

    enum class RequestState {
        NONE, IN_PROGRESS, SUCCESS, FAILED
    }

    fun getUser() = mUsers
    fun getRequest() = runBlocking {
        mRequestState.postValue(RequestState.IN_PROGRESS)
        GlobalScope.launch {
            val job = GlobalScope.launch {
                mUserRepo.getRequest()
            }
            job.join()
            GlobalScope.launch(Dispatchers.Main) {
                val progressLiveData: LiveData<UserRepo.RequestState> = UserRepo.getProgress()
                mRequestState.addSource(progressLiveData) { requestState ->
                    if (requestState == UserRepo.RequestState.SUCCESS) {
                        mRequestState.postValue(RequestState.SUCCESS)
                        mRequestState.removeSource(progressLiveData)
                    } else if (requestState == UserRepo.RequestState.FAILED) {
                        mRequestState.postValue(RequestState.FAILED)
                        mRequestState.removeSource(progressLiveData)
                    }
                }
            }
        }
    }
}
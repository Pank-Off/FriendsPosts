package ru.arcanite.friendsposts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.arcanite.friendsposts.network.ApiHelper
import ru.arcanite.friendsposts.network.UserApi
import java.lang.Exception

class UserRepo {

    private val mApiHelper: ApiHelper = ApiHelper()

    private val mUsers: MutableLiveData<List<User>> = MutableLiveData()

    private val users: ArrayList<User> = ArrayList()

    companion object {

        private val mRequestState: MutableLiveData<RequestState> = MutableLiveData()
        fun getProgress() = mRequestState
    }

    fun getUsers() = mUsers
    suspend fun getRequest() {
        val apiHelper = mApiHelper.getUserApi()
        val job1 = GlobalScope.launch {
            getUserListRequest(apiHelper)
        }
        job1.join()
        val job2 = GlobalScope.launch {
            getUserPostsListRequest(apiHelper)
        }
        job2.join()
        mUsers.postValue(users)
    }

    private fun getUserPostsListRequest(apiHelper: UserApi) {
        try {
            val response: Response<List<UserApi.UserPosts>> = apiHelper.getPosts().execute()
            if (response.isSuccessful && response.body() != null) run {
                val json: List<UserApi.UserPosts>? = response.body()
                if (json != null) {
                    for (u in json) {
                        Log.d("UserInfoPost: ", u.toString())
                        users[u.getUserId() - 1].setPosts(u.getTitle(), u.getBody())
                    }
                }
            }
            mRequestState.postValue(RequestState.SUCCESS)
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, e.printStackTrace().toString())
            mRequestState.postValue(RequestState.FAILED)
        }
    }

    private fun getUserListRequest(apiHelper: UserApi) {
        try {
            val response: Response<List<UserApi.UserPlain>> = apiHelper.getAll().execute()
            if (response.isSuccessful && response.body() != null) run {
                val json: List<UserApi.UserPlain>? = response.body()
                if (json != null) {
                    for (u in json) {
                        Log.d("UserInfo: ", u.toString())
                        users.add(User(u.getId(), u.getName(), u.getEmail(), u.getWebsite()))
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, e.printStackTrace().toString())
            mRequestState.postValue(RequestState.FAILED)
        }
    }

    enum class RequestState {
        SUCCESS, FAILED
    }

}
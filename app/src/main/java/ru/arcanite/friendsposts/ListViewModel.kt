package ru.arcanite.friendsposts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.arcanite.friendsposts.network.ApiHelper
import ru.arcanite.friendsposts.network.UserApi


class ListViewModel : ViewModel() {

    private val mApiHelper: ApiHelper = ApiHelper()
    private val mRequestState: MutableLiveData<RequestState> = MutableLiveData()

    init {
        mRequestState.value = RequestState.NONE
    }

    fun getProgress(): LiveData<RequestState> = mRequestState
    enum class RequestState {
        NONE, ERROR, IN_PROGRESS, SUCCESS, FAILED
    }

    fun getRequest() {
        mRequestState.postValue(RequestState.IN_PROGRESS)
        val apiHelper = mApiHelper.getUserApi()
        apiHelper.getAll().enqueue(object : Callback<List<UserApi.UserPlain>> {
            override fun onResponse(
                call: Call<List<UserApi.UserPlain>>,
                response: Response<List<UserApi.UserPlain>>
            ) {
                if (response.isSuccessful && response.body() != null) run {
                    val users: List<UserApi.UserPlain>? = response.body()
                    if (users != null) {
                        for (u in users) {
                            Log.d("UserInfo: ", u.toString())
                        }
                    }
                    mRequestState.postValue(RequestState.SUCCESS)
                    return
                }
                mRequestState.postValue(RequestState.FAILED)
            }

            override fun onFailure(call: Call<List<UserApi.UserPlain>>, t: Throwable) {
                mRequestState.postValue(RequestState.FAILED)
                Log.e(javaClass.simpleName, t.toString())
            }
        })
    }
}
package com.duybui.basemvvmkotlin.ui.users

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.duybui.basemvvmkotlin.data.model.User
import com.duybui.basemvvmkotlin.data.network.ApiInterface
import com.duybui.basemvvmkotlin.ui.base.BaseViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class UserViewModel(application: Application) : BaseViewModel(application) {
    private val _userList = MutableLiveData<List<User>>()

    val users: MutableLiveData<List<User>>
        get() = _userList

    @Inject
    lateinit var apiInterface: ApiInterface

    init {
        presentationComponent.inject(this)
    }


    fun getRandomUser(number: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var userResponse = apiInterface.getRandomUser(number)
                withContext(Dispatchers.Main) {
                    delay(5000)
                    userResponse = filterResponse(userResponse)
                    _userList.value = userResponse?.data.filter { user -> user.gender.equals("male") }
                }
            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }

    fun getRandomUserUsingAsync() {
        CoroutineScope(Dispatchers.IO).launch {
            val userResponse = async(Dispatchers.IO) { apiInterface.getRandomUser(10) }
            withContext(Dispatchers.Main) {
                _userList.value = userResponse.await().data
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}

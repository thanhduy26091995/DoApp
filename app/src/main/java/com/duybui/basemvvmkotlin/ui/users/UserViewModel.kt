package com.duybui.basemvvmkotlin.ui.users

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.duybui.basemvvmkotlin.data.model.User
import com.duybui.basemvvmkotlin.data.network.ApiInterface
import com.duybui.basemvvmkotlin.ui.base.BaseViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class UserViewModel(application: Application) : BaseViewModel(application) {
    val userList = MutableLiveData<List<User>>()

    @Inject
    lateinit var apiInterface: ApiInterface

    init {
        presentationComponent.inject(this)
    }


    fun getRandomUser(number: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userResponse = apiInterface.getRandomUser(number)
                withContext(Dispatchers.Main) {
                    userList.value = userResponse.data
                }
            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }

    fun getRandomUserUsingAsync() {
        CoroutineScope(Dispatchers.IO).launch {
            val userResponse = async(Dispatchers.IO) { apiInterface.getRandomUser(10) }
            withContext(Dispatchers.Main){
                userList.value = userResponse.await().data
            }
        }
    }
}

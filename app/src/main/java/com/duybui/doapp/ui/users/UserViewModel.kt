package com.duybui.doapp.ui.users

import android.app.Application
import android.util.TimingLogger
import androidx.lifecycle.MutableLiveData
import com.duybui.doapp.data.model.User
import com.duybui.doapp.data.network.ApiInterface
import com.duybui.doapp.ui.base.BaseViewModel
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
                    _userList.value = userResponse?.data
                    val timingLogger = TimingLogger("TimingLogger", "")

                    var numberCount = 0L
                    for (x in 0..10_000_000) {
                        numberCount += x
                    }
                    timingLogger.addSplit("(1)")

                    timingLogger.addSplit("(2)")
                    timingLogger.dumpToLog()
                    //_userList.value = userResponse?.data.filter { user -> user.gender.equals("male") }
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

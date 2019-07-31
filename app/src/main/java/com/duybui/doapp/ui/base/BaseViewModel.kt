package com.duybui.doapp.ui.base

import android.app.Application
import androidx.annotation.UiThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duybui.doapp.MyApplication
import com.duybui.doapp.data.response.BaseResponseObject
import com.duybui.doapp.di.presentation.PresentationComponent
import com.duybui.doapp.di.presentation.PresentationModule

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private var _error = MutableLiveData<String>()
        private set
    private var mIsInjectorUsed: Boolean = false


    protected val presentationComponent: PresentationComponent
        @UiThread
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return mvvmApplication.applicationComponent!!
                .newPresentationComponent(PresentationModule())

        }


    protected val mvvmApplication: MyApplication
        get() = getApplication<Application>() as MyApplication

    fun setError(errorStr: String) {
        _error.value = errorStr
    }

    val error: LiveData<String>
        get() = _error

    fun <T> filterResponse(response: BaseResponseObject<T>): BaseResponseObject<T> {
        when (response.code) {
            0, 200 -> {
                return response
            }
            401 -> {
                println("401")
            }
            else -> {
                setError("Lỗi rồi hihi")
            }
        }
        return response
    }
}

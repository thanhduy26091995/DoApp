package com.duybui.basemvvmkotlin.ui.base

import android.app.Application
import androidx.annotation.UiThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.duybui.basemvvmkotlin.MyApplication
import com.duybui.basemvvmkotlin.di.presentation.PresentationComponent
import com.duybui.basemvvmkotlin.di.presentation.PresentationModule

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var error = MutableLiveData<String>()
        protected set
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
        error.setValue(errorStr)
    }
}

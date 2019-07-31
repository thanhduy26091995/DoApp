package com.duybui.doapp.ui.base

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.duybui.doapp.MyApplication
import com.duybui.doapp.di.application.ApplicationComponent
import com.duybui.doapp.di.presentation.PresentationComponent
import com.duybui.doapp.di.presentation.PresentationModule


abstract class BaseFragment : Fragment() {

    private var mIsInjectorUsed: Boolean = false

    protected val presentationComponent: PresentationComponent
        @UiThread
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent!!
                .newPresentationComponent(PresentationModule(activity!!))

        }

    private val applicationComponent: ApplicationComponent?
        get() = if (activity != null) {
            (activity!!.application as MyApplication).applicationComponent!!
        } else null
}

package com.duybui.doapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import javax.inject.Provider


class ViewModelFactory(private val mProviderMap: Map<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mProviderMap[modelClass]!!.get() as T
    }
}

package com.duybui.doapp.ui.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duybui.doapp.R
import com.duybui.doapp.ui.base.BaseFragment

class ListsFragment : BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }
}
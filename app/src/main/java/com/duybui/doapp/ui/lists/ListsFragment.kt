package com.duybui.doapp.ui.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.duybui.doapp.R
import com.duybui.doapp.ui.base.BaseFragment
import com.duybui.doapp.ui.base.ViewModelFactory
import com.duybui.doapp.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class ListsFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var listAdapter: ListAdapter

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presentationComponent.inject(this)
        setupRecyclerView()

        homeViewModel.events.observe(this, Observer { value ->
            listAdapter.setEventList(value)
        })
    }

    private fun setupRecyclerView() {
        rvEvents.layoutManager = LinearLayoutManager(activity)
        rvEvents.adapter = listAdapter
    }
}
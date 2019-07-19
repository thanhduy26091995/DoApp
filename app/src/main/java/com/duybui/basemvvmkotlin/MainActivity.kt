package com.duybui.basemvvmkotlin

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.duybui.basemvvmkotlin.ui.base.BaseActivity
import com.duybui.basemvvmkotlin.ui.base.ViewModelFactory
import com.duybui.basemvvmkotlin.ui.users.UserAdapter
import com.duybui.basemvvmkotlin.ui.users.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    override val layoutRes: Int
        get() = R.layout.activity_main

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var userAdapter: UserAdapter

    private val userViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentationComponent.inject(this)

        setupRecyclerView()
        //userViewModel.getRandomUser(10)
        userViewModel.getRandomUserUsingAsync()

        userViewModel.userList.observe(this, Observer { value ->
            userAdapter.setData(value)
            userAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.adapter = userAdapter
    }
}

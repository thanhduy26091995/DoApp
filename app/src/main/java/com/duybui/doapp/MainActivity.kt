package com.duybui.doapp

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.duybui.doapp.ui.base.BaseActivity
import com.duybui.doapp.ui.base.ServerErrorDialogFragment
import com.duybui.doapp.ui.base.ViewModelFactory
import com.duybui.doapp.ui.users.UserAdapter
import com.duybui.doapp.ui.users.UserViewModel
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
        userViewModel.getRandomUser(10)
        // userViewModel.getRandomUserUsingAsync()

        userViewModel.users.observe(this, Observer { value ->
            userAdapter.setData(value)
            userAdapter.notifyDataSetChanged()
        })

        //listen error
        userViewModel.error.observe(this, Observer { error ->
            error?.let {
                ServerErrorDialogFragment.newInstance(null, error).show(supportFragmentManager, "A")
            }
        })
    }

    private fun setupRecyclerView() {
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.adapter = userAdapter
    }
}

package com.duybui.doapp.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.duybui.doapp.R
import com.duybui.doapp.data.model.Introduction
import com.duybui.doapp.ui.base.BaseActivity
import com.duybui.doapp.ui.home.MainActivity
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import kotlinx.android.synthetic.main.activity_introduction_view.*
import kotlinx.android.synthetic.main.include_toolbar.*


class IntroductionActivity : BaseActivity() {
    override val layoutRes: Int
        get() = com.duybui.doapp.R.layout.activity_introduction_view

    private var introductionList = arrayListOf<Introduction>()
    private lateinit var swipeStackAdapter: SwipeStackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()
        initStackData()
        swipeStackAdapter = SwipeStackAdapter(introductionList)
        swipeStack.adapter = swipeStackAdapter
        swipeStack.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!")

            }

            override fun onLeftCardExit(dataObject: Any) {
//                Collections.swap(introductionList, 0, introductionList.size - 1)
//                swipeStackAdapter.notifyDataSetChanged()
                handleSwipe()
            }

            override fun onRightCardExit(dataObject: Any) {
//                Collections.swap(introductionList, 0, introductionList.size - 1)
//                swipeStackAdapter.notifyDataSetChanged()
                handleSwipe()
            }

            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
                // Ask for more data here
            }

            override fun onScroll(scrollProgressPercent: Float) {
                val view = swipeStack.selectedView
                val background = view.findViewById(R.id.background) as FrameLayout
                background.alpha = 0f
            }
        })
    }

    private fun handleSwipe() {
        introductionList.removeAt(0)
        swipeStackAdapter.notifyDataSetChanged()

        if (introductionList.isEmpty()) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    or Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
            startActivity(intent)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = ""
        }
        tvHeader.text = getString(R.string.sign_up)
    }

    private fun initStackData() {
        val introduction = Introduction(com.duybui.doapp.R.drawable.ic_walk_through, "Manage your tasks from anywhere you want")
        val introduction1 = Introduction(com.duybui.doapp.R.drawable.ic_walk_through, "Manage your tasks from anywhere you want")
        val introduction2 = Introduction(com.duybui.doapp.R.drawable.ic_walk_through, "Manage your tasks from anywhere you want")

        introductionList.add(introduction)
        introductionList.add(introduction1)
        introductionList.add(introduction2)
    }


    inner class SwipeStackAdapter(private val mData: List<Introduction>) : BaseAdapter() {

        override fun getCount(): Int {
            return mData.size
        }

        override fun getItem(position: Int): Introduction {
            return mData[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            if (convertView == null) {
                convertView = layoutInflater.inflate(com.duybui.doapp.R.layout.item_stack, parent, false)
            }

            val textViewCard = convertView!!.findViewById(com.duybui.doapp.R.id.tvText) as TextView
            textViewCard.text = mData[position].text

            val ivWalkThrough = convertView.findViewById(com.duybui.doapp.R.id.ivWalkThrough) as ImageView
            ivWalkThrough.setImageResource(mData[position].image)

            return convertView
        }
    }

}
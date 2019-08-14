package com.duybui.doapp.ui.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.duybui.doapp.MyApplication
import com.duybui.doapp.R
import com.duybui.doapp.di.application.ApplicationComponent
import com.duybui.doapp.di.presentation.PresentationComponent
import com.duybui.doapp.di.presentation.PresentationModule
import com.duybui.doapp.utils.AppConstants
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.custom_appbar.view.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    private var mIsInjectorUsed: Boolean = false
    private lateinit var toolbarView: View
    private lateinit var toolbarHome: View
    private lateinit var toolbarCalendar: View
    private lateinit var toolbarRemain: View
    private lateinit var ivNextMonth: View
    private lateinit var ivPreviousMonth: View
    private lateinit var tvCurrentDate: TextView

    var toolbarClickListener: ToolbarClickListener? = null

    protected val presentationComponent: PresentationComponent
        @UiThread
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent
                .newPresentationComponent(PresentationModule(this))

        }

    @get:LayoutRes
    abstract val layoutRes: Int

    private val applicationComponent: ApplicationComponent
        get() = (application as MyApplication).applicationComponent!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        setFullScreenView()
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    protected fun setFullScreenView() {
        val w = window
        w.run {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = Color.TRANSPARENT
        }
        // w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun setupCustomToolbar() {
        toolbarView = LayoutInflater.from(this).inflate(R.layout.custom_appbar, toolbar, false)
        toolbarHome = toolbarView.findViewById(R.id.toolbar_home) as RelativeLayout
        //init toolbar calendar
        toolbarCalendar = toolbarView.findViewById(R.id.toolbar_calendar) as RelativeLayout
        ivNextMonth = toolbarCalendar.ivRight
        ivPreviousMonth = toolbarCalendar.ivLeft
        tvCurrentDate = toolbarCalendar.tvDay
        //setup event click
        ivNextMonth.setOnClickListener(this)
        ivPreviousMonth.setOnClickListener(this)

        toolbarRemain = toolbarView.findViewById(R.id.toolbar_remain) as RelativeLayout
        toolbar.addView(toolbarView)
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = ""
            elevation = 0f
        }
    }

    fun updateCurrentDay(header: String) {
        tvCurrentDate.text = header
    }

    fun updateToolbar(tag: String) {
        when (tag) {
            AppConstants.FRAGMENT_TAG.HOME_FRAGMENT -> {
                toolbarHome.visibility = View.VISIBLE
                toolbarCalendar.visibility = View.GONE
                toolbarRemain.visibility = View.GONE
            }
            AppConstants.FRAGMENT_TAG.CALENDAR_FRAGMENT -> {
                toolbarHome.visibility = View.GONE
                toolbarCalendar.visibility = View.VISIBLE
                toolbarRemain.visibility = View.GONE
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivRight -> {
                toolbarClickListener?.onNextMonthClick()
            }

            R.id.ivLeft -> {
                toolbarClickListener?.onPreviousMonthClick()
            }
        }
    }

    interface ToolbarClickListener {
        fun onNextMonthClick()

        fun onPreviousMonthClick()
    }
}

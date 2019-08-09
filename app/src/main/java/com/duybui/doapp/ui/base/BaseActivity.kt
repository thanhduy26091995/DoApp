package com.duybui.doapp.ui.base

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
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
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


abstract class BaseActivity : AppCompatActivity() {

    private var mIsInjectorUsed: Boolean = false

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


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    protected fun setFullScreenView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.run {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                setStatusBarColor(Color.TRANSPARENT)
            }
            // w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun setupToolbar(tag: String) {
        val toolbarView = LayoutInflater.from(this).inflate(R.layout.custom_appbar, toolbar, false)
        val toolbarHome = toolbarView.findViewById(R.id.toolbar_home) as RelativeLayout
        val toolbarCalendar = toolbarView.findViewById(R.id.toolbar_calendar) as RelativeLayout
        val toolbarRemain = toolbarView.findViewById(R.id.toolbar_remain) as RelativeLayout
        toolbar.addView(toolbarView)
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = ""
        }

        when (tag){
            AppConstants.FRAGMENT_TAG.HOME_FRAGMENT -> {
                toolbarHome.visibility = View.VISIBLE
                toolbarCalendar.visibility = View.GONE
                toolbarRemain.visibility = View.GONE
            }
        }
    }
}

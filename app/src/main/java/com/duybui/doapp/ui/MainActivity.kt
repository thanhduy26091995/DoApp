package com.duybui.doapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.duybui.doapp.R
import com.duybui.doapp.ui.base.BaseActivity
import com.duybui.doapp.ui.calendar.CalendarFragment
import com.duybui.doapp.ui.home.HomeFragment
import com.duybui.doapp.ui.lists.ListsFragment
import com.duybui.doapp.ui.overview.OverviewFragment
import com.duybui.doapp.utils.AppConstants
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override val layoutRes: Int
        get() = R.layout.activity_main


    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        //init home fragment
        setupCustomToolbar()
        initFragment(HomeFragment(), AppConstants.FRAGMENT_TAG.HOME_FRAGMENT)
        updateToolbar(AppConstants.FRAGMENT_TAG.HOME_FRAGMENT)
        navView.setCheckedItem(R.id.nav_home)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (toggle.onOptionsItemSelected(item)) {
            return true
        } else if (!toggle.isDrawerIndicatorEnabled) {
            finish()
            return false
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var fragment: Fragment? = null
        var tag: String = AppConstants.FRAGMENT_TAG.HOME_FRAGMENT
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
                fragment = HomeFragment()
                tag = AppConstants.FRAGMENT_TAG.HOME_FRAGMENT
            }

            R.id.nav_calendar -> {
                fragment = CalendarFragment()
                tag = AppConstants.FRAGMENT_TAG.CALENDAR_FRAGMENT
            }

            R.id.nav_overview -> {
                fragment = OverviewFragment()
                tag = AppConstants.FRAGMENT_TAG.OVERVIEW_FRAGMENT
            }

            R.id.nav_lists -> {
                fragment = ListsFragment()
                tag = AppConstants.FRAGMENT_TAG.LIST_FRAGMENT
            }

        }
        //set new fragment
        fragment?.let {
            initFragment(it, tag)
            updateToolbar(tag)
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initFragment(fragment: Fragment, fragmentTag: String) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment, fragmentTag).commitAllowingStateLoss()
    }
}

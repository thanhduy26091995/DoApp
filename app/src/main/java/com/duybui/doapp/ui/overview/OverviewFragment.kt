package com.duybui.doapp.ui.overview

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import com.duybui.doapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_overview.*


class OverviewFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.duybui.doapp.R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationForProgress(progressBarCompleted, 100)
        animationForProgress(progressBarSnoozed, 48)
        animationForProgress(progressBarOverdue, 24)
    }

    private fun animationForProgress(view: ProgressBar, value: Int) {
        val animation = ObjectAnimator.ofInt(view, "secondaryProgress", 0, value) // see this max value coming back here, we animate towards that value
        animation.duration = 1000 // in milliseconds
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }
}